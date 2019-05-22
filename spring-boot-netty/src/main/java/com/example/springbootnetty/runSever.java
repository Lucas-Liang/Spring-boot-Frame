package com.example.springbootnetty;

import com.example.springbootnetty.socket.SocketServer;
import com.example.springbootnetty.websocket.WSServer;
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
 * @ClassName runSever
 * @Description TODO
 * @Author liangkanglin
 * @Date 2019/5/16 15:43
 * Version 1.0
 **/
public class runSever {


    public static void main(String[] args) throws Exception {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    WSServer.bind(8081);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    SocketServer.bind(8080);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();



    }





    //用Set来放请求的数据
    private static Set<ChannelId> Websocketset=new TreeSet<>();
    private static ChannelGroup WebsocketGlobalGroup=new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private static ConcurrentMap<String, ChannelId> WebsocketChannelMap=new ConcurrentHashMap();
    //Map对象记录通道（channel的id值）和 设备的id 用来逆向推导设备的ID值
    private static ConcurrentMap<ChannelId, String> WebsocketStringChannelMap=new ConcurrentHashMap();
    public static void addWebsocketChannel(Channel channel, String msg){
        WebsocketGlobalGroup.add(channel);
        WebsocketChannelMap.put(msg,channel.id());
        WebsocketStringChannelMap.put(channel.id(),msg);
    }
    public static void removeWebsocketChannel(Channel channel){
        WebsocketChannelMap.remove(WebsocketStringChannelMap.get(channel.id()));
        WebsocketGlobalGroup.remove(channel);

    }
    public static void removeStringWebsocketChannel(Channel channel){
        WebsocketStringChannelMap.remove(channel.id());
    }
    public static int getWebsocketChannelGroup(){
        if(WebsocketChannelMap!=null){ return WebsocketChannelMap.size(); }
        return 0;
    }
    //根据查找的字符来找相关的通道
    public static  Channel findWebsocketChannel(String msg){
        return WebsocketGlobalGroup.find(WebsocketChannelMap.get(msg));
    }
    public static void addWebsocketSet(Channel channel){
        Websocketset.add(channel.id());
    }
    public static void removeWebsocketSet(Channel channel){
        Websocketset.remove(channel.id());
    }
    public static  boolean findWebsocketChannel(Channel channel){
        return Websocketset.contains(channel.id());
    }


    //用Set来放请求的数据
    private static Set<ChannelId> Socketset=new TreeSet<>();
    private static ChannelGroup SocketGlobalGroup=new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    //Map对象记录设备的id 和通道（channel的id值）
    private static ConcurrentMap<String, ChannelId> SocketChannelMap=new ConcurrentHashMap();
    //Map对象记录通道（channel的id值）和 设备的id 用来逆向推导设备的ID值
    private static ConcurrentMap<ChannelId, String> SocketStringChannelMap=new ConcurrentHashMap();
    public static void addSocketChannel(Channel channel, String msg){
        SocketGlobalGroup.add(channel);
        SocketChannelMap.put(msg,channel.id());
        SocketStringChannelMap.put(channel.id(),msg);
    }
    public static void removeSocketChannel(Channel channel){
        SocketGlobalGroup.remove(channel);
        SocketChannelMap.remove(SocketStringChannelMap.get(channel.id()));
        SocketStringChannelMap.remove(channel.id());
    }
    public static void removeStringSocketChannel(Channel channel){
        SocketStringChannelMap.remove(channel.id());
    }
    public static int getSocketChannelGroup(){
        if(SocketChannelMap!=null){ return SocketChannelMap.size(); }
        return 0;
    }
    //根据查找的字符来找相关的通道
    public static  Channel findSocketChannel(String msg){
        return SocketGlobalGroup.find(SocketChannelMap.get(msg));
    }
    public static void addSocketSet(Channel channel){
        Socketset.add(channel.id());
    }
    public static void removeSocketSet(Channel channel){
        Socketset.remove(channel.id());
    }
    public static  boolean findSocketChannel(Channel channel){
        return Socketset.contains(channel.id());
    }





}
