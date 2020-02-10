package org.test;

import com.hazelcast.config.Config;
import com.hazelcast.config.GroupConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

public class IMapTest {

    public static void main(String[] args) {

        Config config = new Config();
        GroupConfig gc = new GroupConfig("hazelcast-group");
        config.setGroupConfig(gc);
        HazelcastInstance hzInstance = Hazelcast.newHazelcastInstance(config);

        IMap<String, String> imap = hzInstance.getMap("Const.MAP_NAME");
        imap.put("myKey", "myObject");
    }
}
