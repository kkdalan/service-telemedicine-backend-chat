package com.fet.telemedicine.backend.chat.xmpp;

import static com.fet.telemedicine.backend.chat.message.model.MessageType.NEW_MESSAGE;

import javax.websocket.Session;

import org.jivesoftware.smack.packet.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fet.telemedicine.backend.chat.message.model.InstantMessage;
import com.fet.telemedicine.backend.chat.websocket.support.WebSocketMessageHelper;

@Component
public class XMPPMessageTransmitter {

    public static final Logger log = LoggerFactory.getLogger(XMPPMessageTransmitter.class);

    private final WebSocketMessageHelper webSocketMessageHelper;

    public XMPPMessageTransmitter(WebSocketMessageHelper webSocketMessageHelper) {
	this.webSocketMessageHelper = webSocketMessageHelper;
    }

    public void sendResponse(Message message, Session session) {
	log.info("New message from '{}' to '{}': {}", message.getFrom(), message.getTo(), message.getBody());
	String messageFrom = message.getFrom().getLocalpartOrNull().toString();
	String to = message.getTo().getLocalpartOrNull().toString();
	String content = message.getBody();
	webSocketMessageHelper.send(session,
		InstantMessage.builder().from(messageFrom).to(to).content(content).messageType(NEW_MESSAGE).build());
    }
}
