package com.fet.telemedicine.backend.chat.message.impl;

import static com.fet.telemedicine.backend.chat.message.websocket.dto.MessageType.ERROR;
import static com.fet.telemedicine.backend.chat.message.websocket.dto.MessageType.FORBIDDEN;
import static com.fet.telemedicine.backend.chat.message.websocket.dto.MessageType.GET_CONTACTS;
import static com.fet.telemedicine.backend.chat.message.websocket.dto.MessageType.JOIN_SUCCESS;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.websocket.Session;

import org.apache.commons.lang3.StringUtils;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.roster.RosterEntry;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.stereotype.Component;

import com.fet.telemedicine.backend.chat.auth.repository.po.AccountPo;
import com.fet.telemedicine.backend.chat.auth.service.AccountService;
import com.fet.telemedicine.backend.chat.exception.MessengerException;
import com.fet.telemedicine.backend.chat.message.WebSocketMessenger;
import com.fet.telemedicine.backend.chat.message.repository.po.MessagePo;
import com.fet.telemedicine.backend.chat.message.service.ChatMessageService;
import com.fet.telemedicine.backend.chat.message.websocket.dto.WebSocketMessage;
import com.fet.telemedicine.backend.chat.message.websocket.support.WebSocketMessageHelper;
import com.fet.telemedicine.backend.chat.protocol.xmpp.XMPPClient;
import com.fet.telemedicine.backend.chat.protocol.xmpp.XMPPConnectionPool;
import com.fet.telemedicine.backend.chat.utils.BCryptUtils;

@Component(WebSocketMessenger.XMPP_WEB_SOCKET_MESSENGER)
public class XMPPWebSocketMessenger implements WebSocketMessenger {

    public static final Logger log = LoggerFactory.getLogger(XMPPWebSocketMessenger.class);

    private static final XMPPConnectionPool CONNECTIONS = XMPPConnectionPool.create();

    private final AccountService accountService;
    private final WebSocketMessageHelper webSocketMessageHelper;
    private final XMPPClient xmppClient;
    private final ChatMessageService chatMessageService;

    public XMPPWebSocketMessenger(
	    AccountService accountService, 
	    WebSocketMessageHelper webSocketMessageHelper,
	    XMPPClient xmppClient, 
	    ChatMessageService chatMessageService) {

	this.accountService = accountService;
	this.webSocketMessageHelper = webSocketMessageHelper;
	this.xmppClient = xmppClient;
	this.chatMessageService = chatMessageService;
    }

    @Override
    public void startSession(Session session, String username, String password) {
	// TODO: Save user session to avoid having to login again when the websocket
	// connection is closed
	// 1. Generate token
	// 2. Save username and token in Redis
	// 3. Return token to client and store it in a cookie or local storage
	// 4. When starting a websocket session check if the token is still valid and
	// bypass XMPP authentication
	Optional<AccountPo> account = accountService.getAccount(username);

	if (account.isPresent() && !BCryptUtils.isMatch(password, account.get().getPassword())) {
	    log.warn("Invalid password for user {}.", username);
	    webSocketMessageHelper.send(session, WebSocketMessage.builder().messageType(FORBIDDEN).build());
	    return;
	}

	Optional<XMPPTCPConnection> connection = xmppClient.connect(username, password);

	if (!connection.isPresent()) {
	    webSocketMessageHelper.send(session, WebSocketMessage.builder().messageType(ERROR).build());
	    return;
	}

	try {
	    if (!account.isPresent()) {
		xmppClient.createAccount(connection.get(), username, password);
		accountService.saveAccount(new AccountPo(username, BCryptUtils.hash(password)));
	    }
	    xmppClient.login(connection.get());
	} catch (MessengerException e) {
	    handleMessageException(session, connection.get(), e);
	    return;
	}

	CONNECTIONS.put(session, connection.get());
	
	log.info("Session was stored.");

	xmppClient.addIncomingMessageListener(connection.get(), session);
	webSocketMessageHelper.send(session, 
		WebSocketMessage.builder().to(username).messageType(JOIN_SUCCESS).build());
    }

    @Override
    public void sendMessage(WebSocketMessage message, Session session) {
	XMPPTCPConnection connection = CONNECTIONS.get(session);

	if (connection == null) {
	    return;
	}

	switch (message.getMessageType()) {
	case NEW_MESSAGE:
	    try {
		xmppClient.sendMessage(connection, message.getContent(), message.getTo());
		
		// TODO: save message for both users in DB(mongo)
		MessagePo messagePo = new MessagePo();
		
		Optional<AccountPo> fromAccount = accountService.getAccount(message.getFrom());
		Optional<AccountPo> toAccount = accountService.getAccount(message.getTo());
		messagePo.setMessageFrom(fromAccount.get().getId());
		messagePo.setMessageTo(toAccount.get().getId());
		
		messagePo.setContent(message.getContent());
		messagePo.setCreateAt(new Date());
		
		chatMessageService.saveMessageToHistory(messagePo);
		
	    } catch (MessengerException e) {
		handleMessageException(session, connection, e);
	    }
	    break;

	case ADD_CONTACT:
	    try {
		xmppClient.addContact(connection, message.getTo());
	    } catch (MessengerException e) {
		handleMessageException(session, connection, e);
	    }
	    break;
	case GET_CONTACTS:
	    Set<RosterEntry> contacts = new HashSet<>();
	    try {
		contacts = xmppClient.getContacts(connection);
	    } catch (MessengerException e) {
		handleMessageException(session, connection, e);
	    }

	    JSONArray jsonArray = new JSONArray();
	    for (RosterEntry entry : contacts) {
		jsonArray.put(entry.getName());
	    }
	    WebSocketMessage responseMessage = WebSocketMessage.builder().content(jsonArray.toString())
		    .messageType(GET_CONTACTS).build();
	    log.info("Returning list of contacts {} for user {}.", jsonArray, connection.getUser());
	    webSocketMessageHelper.send(session, responseMessage);
	    break;
	    
	case NEW_INSTANCE_ROOM:
	    try {
		xmppClient.createInstantRoom(connection, message.getTo());
	    } catch (MessengerException e) {
		handleMessageException(session, connection, e);
	    }
	    break;
	    
	case NEW_RESERVED_ROOM:
	    try {
		String[] owners = StringUtils.split(message.getContent(), ",");
		xmppClient.createReservedRoom(connection, message.getTo(), owners);
	    } catch (MessengerException e) {
		handleMessageException(session, connection, e);
	    }
	    break;
	    
	default:
	    log.warn("Message type not implemented.");
	}
    }

    @Override
    public void disconnect(Session session) {
	XMPPTCPConnection connection = CONNECTIONS.get(session);

	if (connection == null) {
	    return;
	}

	try {
	    xmppClient.sendStanza(connection, Presence.Type.unavailable);
	} catch (MessengerException e) {
	    log.error("XMPP error.", e);
	    webSocketMessageHelper.send(session, WebSocketMessage.builder().messageType(ERROR).build());
	}

	xmppClient.disconnect(connection);
	CONNECTIONS.remove(session);
    }

    private void handleMessageException(Session session, XMPPTCPConnection connection, Exception e) {
	log.error("XMPP error. Disconnecting and removing session...", e);
	xmppClient.disconnect(connection);
	webSocketMessageHelper.send(session, WebSocketMessage.builder().messageType(ERROR).build());
	CONNECTIONS.remove(session);
    }
}
