package com.fet.telemedicine.backend.chat.message.listener;

import java.util.Collection;

import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.roster.RosterListener;
import org.jxmpp.jid.Jid;

public class DefaultRosterListener implements RosterListener{

    @Override
    public void entriesAdded(Collection<Jid> addresses) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void entriesUpdated(Collection<Jid> addresses) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void entriesDeleted(Collection<Jid> addresses) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void presenceChanged(Presence presence) {
	// TODO Auto-generated method stub
	
    }

}
