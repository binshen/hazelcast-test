package org.test;

import com.hazelcast.core.EntryEvent;
import com.hazelcast.map.listener.EntryAddedListener;

public class IMapListener implements EntryAddedListener<String, String> {

    public void entryAdded(EntryEvent<String, String> entryEvent) {
        System.out.println("MAP分布式监听：" + entryEvent.getValue());
    }
}
