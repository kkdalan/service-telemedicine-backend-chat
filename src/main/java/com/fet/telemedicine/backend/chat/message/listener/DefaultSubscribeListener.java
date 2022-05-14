package com.fet.telemedicine.backend.chat.message.listener;

import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.roster.SubscribeListener;
import org.jxmpp.jid.Jid;

public class DefaultSubscribeListener implements SubscribeListener{

    @Override
    public SubscribeAnswer processSubscribe(Jid from, Presence subscribeRequest) {
	// TODO Auto-generated method stub
	return null;
    }

}
