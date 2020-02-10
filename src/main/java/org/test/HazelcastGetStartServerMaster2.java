package org.test;

import com.hazelcast.config.*;
import com.hazelcast.core.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class HazelcastGetStartServerMaster2 {

    public static void main(String[] args) {

        Config config = new Config();
        GroupConfig gc = new GroupConfig("hazelcast-group");//解决同网段下，不同库项目

        NetworkConfig networkConfig = new NetworkConfig();
        networkConfig.setPublicAddress("127.0.0.1:5701");
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

        // 创建集群Map
        IMap<Object, Object> clusterMap = instance.getMap("myMap");
        clusterMap.put("key1", "map1");
        clusterMap.put("key2", "map2");

        //分布式map监听
        clusterMap.addLocalEntryListener(new IMapListener());

        //拦截器（没写内容）
        clusterMap.addInterceptor(new IMapInterceptor());

        //发布/订阅模式
        ITopic<String> topic = instance.getTopic("myTopic");
        topic.addMessageListener(new TopicListener());

        // 创建集群Queue
        Queue<String> clusterQueue = instance.getQueue("MyQueue");
        clusterQueue.offer("Hello hazelcast!");
        clusterQueue.offer("Hello hazelcast queue!");
    }
}
