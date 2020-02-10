package org.test;

import com.hazelcast.config.*;
import com.hazelcast.core.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class HazelcastGetStartServerSlave2 {

    public static void main(String[] args) {

        Config config = new Config();
        GroupConfig gc = new GroupConfig("hazelcast-group");//解决同网段下，不同库项目

        NetworkConfig networkConfig = new NetworkConfig();
        networkConfig.setPublicAddress("127.0.0.1:5702");
        JoinConfig joinConfig = new JoinConfig();
        MulticastConfig multicastConfig = new MulticastConfig();
        multicastConfig.setEnabled(false);
        joinConfig.setMulticastConfig(multicastConfig);
        TcpIpConfig tcpIpConfig = new TcpIpConfig();
        tcpIpConfig.setEnabled(true);
        List<String> members = new ArrayList<String>();
        members.add("127.0.0.1:5701");
        members.add("127.0.0.1:5702");
        tcpIpConfig.setMembers(members);
        joinConfig.setTcpIpConfig(tcpIpConfig);
        networkConfig.setJoin(joinConfig);

        config.setInstanceName("hazelcast-instance")
                .addMapConfig(new MapConfig().setName("configuration")
                .setMaxSizeConfig(new MaxSizeConfig(2000, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE))
                .setEvictionPolicy(EvictionPolicy.LRU).setTimeToLiveSeconds(-1))
                .setNetworkConfig(networkConfig)
                .setGroupConfig(gc);

        // 创建一个 hazelcastInstance实例
        HazelcastInstance instance = Hazelcast.newHazelcastInstance(config);

        IMap<Object, Object> clusterMap = instance.getMap("myMap");
        System.out.println("Map Value:" + clusterMap.get("key1"));

        Queue<String> clusterQueue = instance.getQueue("MyQueue");
        System.out.println("Queue Size :" + clusterQueue.size());
        System.out.println("Queue Value 1:" + clusterQueue.poll());
        System.out.println("Queue Value 2:" + clusterQueue.poll());
        System.out.println("Queue Size :" + clusterQueue.size());

        ITopic<String> topic = instance.getTopic("myTopic");
        topic.publish("123");
    }
}
