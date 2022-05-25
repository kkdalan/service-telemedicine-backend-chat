package com.fet.telemedicine.backend.chat.protocol.xmpp.listener;

import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.roster.PresenceEventListener;
import org.jxmpp.jid.BareJid;
import org.jxmpp.jid.FullJid;
import org.jxmpp.jid.Jid;

public class DefaultPresenceEventListener implements PresenceEventListener{

    @Override
    public void presenceAvailable(FullJid address, Presence availablePresence) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void presenceUnavailable(FullJid address, Presence presence) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void presenceError(Jid address, Presence errorPresence) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void presenceSubscribed(BareJid address, Presence subscribedPresence) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void presenceUnsubscribed(BareJid address, Presence unsubscribedPresence) {
	// TODO Auto-generated method stub
	
    }

}
