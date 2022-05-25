package com.fet.telemedicine.backend.chat.protocol.xmpp.listener;

import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.SmackException.NotLoggedInException;
import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.packet.Stanza;

public class DefaultStanzaListener implements StanzaListener{

    @Override
    public void processStanza(Stanza packet) throws NotConnectedException, InterruptedException, NotLoggedInException {
	// TODO save message to mongo
	
    }

}
