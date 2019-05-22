package com.example.springbootnetty.websocket;

import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @ClassName WebSocketMap
 * @Description TODO
 * @Author liangkanglin
 * @Date 2019/5/16 10:30
 * Version 1.0
 **/
public class WebSocketMap {


    //用Set来放请求的数据
    private static Set<ChannelId> set=new TreeSet<>();

    private static ChannelGroup GlobalGroup = null;
    //String  标识字符
    private static ConcurrentMap<String, ChannelId> ChannelMap=null;
    public static void addChannel(Channel channel,String msg){
        if(GlobalGroup==null){
            GlobalGroup=new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
        }
        if(ChannelMap==null){
            ChannelMap=new ConcurrentHashMap();
        }
        GlobalGroup.add(channel);
        ChannelMap.put(msg,channel.id());
    }

    public static void removeChannel(Channel channel){
        GlobalGroup.remove(channel);
        ChannelMap.remove(channel.id().asShortText());
    }
    public static int getChannelGroup(){
        if(ChannelMap!=null){
            return ChannelMap.size();
        }
       return 0;
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

    public static  boolean findChannel(Channel channel){
        return set.contains(channel.id());
    }
}
