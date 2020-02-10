package org.test;

import com.hazelcast.core.Message;
import com.hazelcast.core.MessageListener;

public class TopicListener implements MessageListener<String> {

    public void onMessage(Message<String> message) {
        String msg = message.getMessageObject();
        System.out.println("收到Topic消息：" + msg);
    }
}
