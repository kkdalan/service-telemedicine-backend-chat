package com.fet.telemedicine.backend.chat.utils;

import org.jxmpp.jid.EntityBareJid;
import org.jxmpp.jid.impl.JidCreate;
import org.jxmpp.stringprep.XmppStringprepException;

public class XMPPUtils {

    public static EntityBareJid createJidForUser(String username, String xmppDomain) throws XmppStringprepException {
	return JidCreate.entityBareFrom(username + "@" + xmppDomain);
    }

    public static EntityBareJid createJidForRoom(String roomName, String xmppDomain) throws XmppStringprepException {
	return JidCreate.entityBareFrom(roomName + "@" + xmppDomain);
    }

}
