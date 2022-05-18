package com.fet.telemedicine.backend.chat;

import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class ZooKeeperDemo {

    private static final String CONNECTION_STRING = "127.0.0.1:2181";

    private static final int SESSION_TIMEOUT = 5000;

    private static CountDownLatch latch = new CountDownLatch(1);

    public static void main(String[] args) throws Exception {
	// connect ZooKeeper
	ZooKeeper zk = new ZooKeeper(CONNECTION_STRING, SESSION_TIMEOUT, new Watcher() {
	    @Override
	    public void process(WatchedEvent event) {
		if (event.getState() == Event.KeeperState.SyncConnected) {
		    latch.countDown();
		}
	    }
	});

	latch.await();

	// Get ZooKeeper insntance
	System.out.println(zk);
	
    }
}
