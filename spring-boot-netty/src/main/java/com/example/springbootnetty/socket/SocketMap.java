package com.example.springbootnetty.socket;

import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @ClassName SocketMap
 * @Description TODO
 * @Author liangkanglin
 * @Date 2019/5/16 10:29
 * Version 1.0
 **/
public class SocketMap {
    //用Set来放请求的数据
    private static Set<ChannelId> set=new TreeSet<>();

    private static ChannelGroup GlobalGroup=new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    //String  标识字符
    private static ConcurrentMap<String, ChannelId> ChannelMap=new ConcurrentHashMap();
    public static void addChannel(Channel channel,String msg){
        GlobalGroup.add(channel);
        ChannelMap.put(msg,channel.id());
    }
    public static void removeChannel(Channel channel){
        GlobalGroup.remove(channel);
        ChannelMap.remove(channel.id().asShortText());
    }
    //根据查找的字符来找相关的通道
    public static  Channel findChannel(String msg){
        return GlobalGroup.find(ChannelMap.get(msg));
    }


    public static void addSet(Channel channel){
        set.add(channel.id());
    }
    public static void removeSet(Channel channel){
        set.remove(channel.id());
    }

    public static  boolean findChannel(Channel channel) {
        return set.contains(channel.id());
    }

 /*   //用Set来放请求的数据
    private static Set<String> sets=new TreeSet<>();

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            String aa = "11111";
            System.out.println(find(aa));
//            if(find(aa)){
                sets.add(aa);
//            }
            sets.add(aa);

        }
    }


    public static void addSet(String channel){
        sets.add(channel);
    }
    public static void removeSet(String channel){
        sets.remove(channel);
    }

    public static  boolean find(String channel){
        return sets.contains(channel);
    }
*/


}
