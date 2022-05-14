package com.fet.telemedicine.backend.chat.protocol.xmpp;

import java.util.HashMap;
import java.util.Map;

import javax.websocket.Session;

import org.jivesoftware.smack.tcp.XMPPTCPConnection;

public class XMPPConnectionPool {

    private static final Map<Session, XMPPTCPConnection> CONNECTIONS = new HashMap<>();

    private XMPPConnectionPool() {
    }

    public static XMPPConnectionPool create() {
	return new XMPPConnectionPool();
    }

    public void put(Session session, XMPPTCPConnection connection) {
	CONNECTIONS.put(session, connection);
    }

    public XMPPTCPConnection get(Session session) {
	return CONNECTIONS.get(session);
    }

    public void remove(Session session) {
	CONNECTIONS.remove(session);
    }

}
