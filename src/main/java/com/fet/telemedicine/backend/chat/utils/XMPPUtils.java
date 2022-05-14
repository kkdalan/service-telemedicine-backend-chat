package com.fet.telemedicine.backend.chat.utils;

import org.jivesoftware.smack.util.StringUtils;
import org.jxmpp.jid.EntityBareJid;
import org.jxmpp.jid.impl.JidCreate;
import org.jxmpp.jid.parts.Localpart;
import org.jxmpp.stringprep.XmppStringprepException;
import org.jxmpp.util.XmppStringUtils;

public class XMPPUtils {

    public static EntityBareJid createJidForUser(String username, String xmppDomain) throws XmppStringprepException {
	return JidCreate.entityBareFrom(username + "@" + xmppDomain);
    }

    public static EntityBareJid createJidForRoom(String roomName, String xmppDomain) throws XmppStringprepException {
	return JidCreate.entityBareFrom(roomName + "@" + xmppDomain);
    }
    
    public static boolean isJidEmpty(String jid) {
	return StringUtils.isEmpty(XmppStringUtils.parseLocalpart(jid));
    }
    
}
