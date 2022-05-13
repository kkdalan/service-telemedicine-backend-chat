package com.fet.telemedicine.backend.chat.xmpp;

import static com.fet.telemedicine.backend.chat.model.MessageType.NEW_MESSAGE;

import javax.websocket.Session;

import org.jivesoftware.smack.packet.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fet.telemedicine.backend.chat.model.WebSocketMessage;
import com.fet.telemedicine.backend.chat.websocket.support.WebSocketTextMessageHelper;

@Component
public class XMPPMessageTransmitter {

    public static final Logger log = LoggerFactory.getLogger(XMPPMessageTransmitter.class);

    private final WebSocketTextMessageHelper webSocketTextMessageHelper;

    public XMPPMessageTransmitter(WebSocketTextMessageHelper webSocketTextMessageHelper) {
	this.webSocketTextMessageHelper = webSocketTextMessageHelper;
    }

    public void sendResponse(Message message, Session session) {
	log.info("New message from '{}' to '{}': {}", message.getFrom(), message.getTo(), message.getBody());
	String messageFrom = message.getFrom().getLocalpartOrNull().toString();
	String to = message.getTo().getLocalpartOrNull().toString();
	String content = message.getBody();
	webSocketTextMessageHelper.send(session,
		WebSocketMessage.builder().from(messageFrom).to(to).content(content).messageType(NEW_MESSAGE).build());
    }
}
