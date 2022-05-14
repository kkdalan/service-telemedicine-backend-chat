package com.fet.telemedicine.backend.chat.message;

import static com.fet.telemedicine.backend.chat.message.websocket.dto.MessageType.ERROR;
import static com.fet.telemedicine.backend.chat.message.websocket.dto.MessageType.FORBIDDEN;
import static com.fet.telemedicine.backend.chat.message.websocket.dto.MessageType.JOIN_SUCCESS;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willThrow;

import java.util.Optional;

import javax.websocket.Session;

import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.jxmpp.stringprep.XmppStringprepException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCrypt;

import com.fet.telemedicine.backend.chat.auth.repository.entity.Account;
import com.fet.telemedicine.backend.chat.auth.service.AccountService;
import com.fet.telemedicine.backend.chat.exception.MessengerException;
import com.fet.telemedicine.backend.chat.message.impl.XMPPWebSocketMessenger;
import com.fet.telemedicine.backend.chat.message.websocket.dto.MessageType;
import com.fet.telemedicine.backend.chat.message.websocket.dto.WebSocketMessage;
import com.fet.telemedicine.backend.chat.message.websocket.support.WebSocketMessageHelper;
import com.fet.telemedicine.backend.chat.protocol.xmpp.XMPPClient;

@ExtendWith(MockitoExtension.class)
class WebSocketMessagerTest {

//    private static final String USERNAME = "alan";
//    private static final String PASSWORD = "1234";
//    private static final String MESSAGE = "hello world";
//    private static final String TO = "other-user";
//
//    @Mock
//    private Session session;
//    @Mock
//    private AccountService accountService;
//    @Mock
//    private WebSocketMessageHelper webSocketMessageHelper;
//    @Mock
//    private XMPPClient xmppClient;
//
//    @InjectMocks
//    private XMPPWebSocketMessenger webSocketMessenger;
//
//
//    @Test
//    void startSessionShouldStartSessionWithoutCreatingAccountWhenAccountExistAndCorrectPassword() throws XmppStringprepException {
//        // GIVEN
//        XMPPTCPConnectionConfiguration configuration = XMPPTCPConnectionConfiguration.builder()
//                .setXmppDomain("domain")
//                .build();
//        XMPPTCPConnection connection = new XMPPTCPConnection(configuration);
//        String hashedPassword = BCrypt.hashpw(PASSWORD, BCrypt.gensalt());
//        given(accountService.getAccount(USERNAME)).willReturn(Optional.of(new Account(USERNAME, hashedPassword)));
//        given(xmppClient.connect(USERNAME, PASSWORD)).willReturn(Optional.of(connection));
//
//        // WHEN
//        webSocketMessenger.startSession(session, USERNAME, PASSWORD);
//
//        // THEN
//        then(xmppClient).should().login(connection);
//        then(xmppClient).should().addIncomingMessageListener(connection, session);
//        thenWebSocketMessageHelperShouldSend(session, createTextMessage(JOIN_SUCCESS, USERNAME));
//        then(xmppClient).shouldHaveNoMoreInteractions();
//    }
//    
//    @Test
//    void startSessionShouldStartSessionAndCreateAccountWhenAccountDoesNotExist() throws XmppStringprepException {
//        // GIVEN
//        XMPPTCPConnectionConfiguration configuration = XMPPTCPConnectionConfiguration.builder()
//                .setXmppDomain("domain")
//                .build();
//        XMPPTCPConnection connection = new XMPPTCPConnection(configuration);
//        given(accountService.getAccount(USERNAME)).willReturn(Optional.empty());
//        given(xmppClient.connect(USERNAME, PASSWORD)).willReturn(Optional.of(connection));
//
//        // WHEN
//        webSocketMessenger.startSession(session, USERNAME, PASSWORD);
//
//        // THEN
//        then(xmppClient).should().login(connection);
//        then(xmppClient).should().addIncomingMessageListener(connection, session);
//        thenWebSocketMessageHelperShouldSend(session, createTextMessage(JOIN_SUCCESS, USERNAME));
//        then(xmppClient).should().createAccount(connection, USERNAME, PASSWORD);
//    }
//
//    @Test
//    void startSessionShouldSendForbiddenMessageWhenWrongPassword() {
//        // GIVEN
//        String hashedPassword = BCrypt.hashpw("WRONG", BCrypt.gensalt());
//        given(accountService.getAccount(USERNAME)).willReturn(Optional.of(new Account(USERNAME, hashedPassword)));
//
//        // WHEN
//        webSocketMessenger.startSession(session, USERNAME, PASSWORD);
//
//        // THEN
//        then(xmppClient).shouldHaveNoInteractions();
//        thenWebSocketMessageHelperShouldSend(session, createTextMessage(FORBIDDEN, null));
//    }
//
//    @Test
//    void startSessionShouldSendErrorMessageWhenConnectionIsNotPresent() {
//        // GIVEN
//        String hashedPassword = BCrypt.hashpw(PASSWORD, BCrypt.gensalt());
//        given(accountService.getAccount(USERNAME)).willReturn(Optional.of(new Account(USERNAME, hashedPassword)));
//        given(xmppClient.connect(USERNAME, PASSWORD)).willReturn(Optional.empty());
//
//        // WHEN
//        webSocketMessenger.startSession(session, USERNAME, PASSWORD);
//
//        // THEN
//        then(xmppClient).shouldHaveNoMoreInteractions();
//        thenWebSocketMessageHelperShouldSend(session, createTextMessage(ERROR, null));
//    }
//
//    @Test
//    void startSessionShouldSendErrorMessageWhenLoginThrowsMessageException() throws XmppStringprepException {
//        // GIVEN
//        XMPPTCPConnectionConfiguration configuration = XMPPTCPConnectionConfiguration.builder()
//                .setXmppDomain("domain")
//                .build();
//        XMPPTCPConnection connection = new XMPPTCPConnection(configuration);
//        String hashedPassword = BCrypt.hashpw(PASSWORD, BCrypt.gensalt());
//        given(accountService.getAccount(USERNAME)).willReturn(Optional.of(new Account(USERNAME, hashedPassword)));
//        given(xmppClient.connect(USERNAME, PASSWORD)).willReturn(Optional.of(connection));
//        willThrow(MessengerException.class).given(xmppClient).login(connection);
//
//        // WHEN
//        webSocketMessenger.startSession(session, USERNAME, PASSWORD);
//
//        // THEN
//        then(xmppClient).should().disconnect(connection);
//        thenWebSocketMessageHelperShouldSend(session, createTextMessage(ERROR, null));
//        then(xmppClient).shouldHaveNoMoreInteractions();
//    }
//
//    @Test
//    void sendMessageShouldSendMessage() throws XmppStringprepException {
//        // GIVEN
//        WebSocketMessage message = WebSocketMessage.builder()
//                .content(MESSAGE)
//                .to(TO)
//                .messageType(MessageType.NEW_MESSAGE)
//                .build();
//        XMPPTCPConnectionConfiguration configuration = XMPPTCPConnectionConfiguration.builder()
//                .setXmppDomain("domain")
//                .build();
//        XMPPTCPConnection connection = new XMPPTCPConnection(configuration);
//        String hashedPassword = BCrypt.hashpw(PASSWORD, BCrypt.gensalt());
//        given(accountService.getAccount(USERNAME)).willReturn(Optional.of(new Account(USERNAME, hashedPassword)));
//        given(xmppClient.connect(USERNAME, PASSWORD)).willReturn(Optional.of(connection));
//        webSocketMessenger.startSession(session, USERNAME, PASSWORD);
//
//        // WHEN
//        webSocketMessenger.sendMessage(message, session);
//
//        // THEN
//        then(xmppClient).should().sendMessage(connection, MESSAGE, TO);
//    }
//
//    @Test
//    void sendMessageShouldSendErrorMessageWhenMessageException() throws XmppStringprepException {
//        // GIVEN
//        WebSocketMessage message = WebSocketMessage.builder()
//                .content(MESSAGE)
//                .to(TO)
//                .messageType(MessageType.NEW_MESSAGE)
//                .build();
//        XMPPTCPConnectionConfiguration configuration = XMPPTCPConnectionConfiguration.builder()
//                .setXmppDomain("domain")
//                .build();
//        XMPPTCPConnection connection = new XMPPTCPConnection(configuration);
//        String hashedPassword = BCrypt.hashpw(PASSWORD, BCrypt.gensalt());
//        given(accountService.getAccount(USERNAME)).willReturn(Optional.of(new Account(USERNAME, hashedPassword)));
//        given(xmppClient.connect(USERNAME, PASSWORD)).willReturn(Optional.of(connection));
//        webSocketMessenger.startSession(session, USERNAME, PASSWORD);
//        willThrow(MessengerException.class).given(xmppClient).sendMessage(connection, MESSAGE, TO);
//
//        // WHEN
//        webSocketMessenger.sendMessage(message, session);
//
//        // THEN
//        thenWebSocketMessageHelperShouldSend(session, createTextMessage(ERROR, null));
//    }
//
//    @Test
//    void sendMessageShouldDoNothingWhenNotFoundConnection() {
//        // GIVEN
//        WebSocketMessage message = WebSocketMessage.builder()
//                .content(MESSAGE)
//                .to(TO)
//                .messageType(MessageType.NEW_MESSAGE)
//                .build();
//
//        // WHEN
//        webSocketMessenger.sendMessage(message, session);
//
//        // THEN
//        then(xmppClient).shouldHaveNoInteractions();
//    }
//
//    @Test
//    void disconnectShouldSendStanzaAndDisconnect() throws XmppStringprepException {
//        // GIVEN
//        XMPPTCPConnectionConfiguration configuration = XMPPTCPConnectionConfiguration.builder()
//                .setXmppDomain("domain")
//                .build();
//        XMPPTCPConnection connection = new XMPPTCPConnection(configuration);
//        String hashedPassword = BCrypt.hashpw(PASSWORD, BCrypt.gensalt());
//        given(accountService.getAccount(USERNAME)).willReturn(Optional.of(new Account(USERNAME, hashedPassword)));
//        given(xmppClient.connect(USERNAME, PASSWORD)).willReturn(Optional.of(connection));
//        webSocketMessenger.startSession(session, USERNAME, PASSWORD);
//
//        // WHEN
//        webSocketMessenger.disconnect(session);
//
//        // THEN
//        then(xmppClient).should().sendStanza(connection, Presence.Type.unavailable);
//        then(xmppClient).should().disconnect(connection);
//    }
//
//    @Test
//    void disconnectShouldSendErrorMessageWhenMessageException() throws XmppStringprepException {
//        // GIVEN
//        XMPPTCPConnectionConfiguration configuration = XMPPTCPConnectionConfiguration.builder()
//                .setXmppDomain("domain")
//                .build();
//        XMPPTCPConnection connection = new XMPPTCPConnection(configuration);
//        String hashedPassword = BCrypt.hashpw(PASSWORD, BCrypt.gensalt());
//        given(accountService.getAccount(USERNAME)).willReturn(Optional.of(new Account(USERNAME, hashedPassword)));
//        given(xmppClient.connect(USERNAME, PASSWORD)).willReturn(Optional.of(connection));
//        webSocketMessenger.startSession(session, USERNAME, PASSWORD);
//        willThrow(MessengerException.class).given(xmppClient).sendStanza(connection, Presence.Type.unavailable);
//
//
//        // WHEN
//        webSocketMessenger.disconnect(session);
//
//        // THEN
//        thenWebSocketMessageHelperShouldSend(session, createTextMessage(ERROR, null));
//    }
//
//    @Test
//    void disconnectShouldDoNothingWhenNotFoundConnection() {
//        // WHEN
//        webSocketMessenger.disconnect(session);
//
//        // THEN
//        then(xmppClient).shouldHaveNoInteractions();
//    }
//    
//    //TODO
//    private void thenWebSocketMessageHelperShouldSend(Session session, WebSocketMessage webSocketMessage) {
////	then(webSocketMessageHelper).should().send(session, webSocketMessage);
//    }
//    
//    private WebSocketMessage createTextMessage(MessageType type, String to) {
//        return WebSocketMessage.builder()
//                .messageType(type)
//                .to(to)
//                .build();
//    }
}

