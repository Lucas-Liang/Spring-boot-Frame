package com.example.springbootnetty.socket;

import com.example.springbootnetty.runSever;
import com.example.springbootnetty.websocket.WebSocketMap;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @ClassName SocketServerHandler
 * @Description TODO
 * @Author liangkanglin
 * @Date 2019/5/16 10:22
 * Version 1.0
 **/
public class SocketServerHandler extends SimpleChannelInboundHandler<String> { // (1)

    /**
     * A thread-safe Set  Using ChannelGroup, you can categorize Channels into a meaningful group.
     * A closed Channel is automatically removed from the collection,
     */
    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {  // (2)
        Channel incoming = ctx.channel();
        // Broadcast a message to multiple Channels
//        channels.writeAndFlush("[SERVER] - " + incoming.remoteAddress() + " 加入\n");
        channels.add(ctx.channel());
//        SocketMap.addChannel(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {  // (3)
        Channel incoming = ctx.channel();

        // Broadcast a message to multiple Channels
//        channels.writeAndFlush("[SERVER] - " + incoming.remoteAddress() + " 离开\n");
        // A closed Channel is automatically removed from ChannelGroup,
        // so there is no need to do "channels.remove(ctx.channel());"
        runSever.removeSocketChannel(incoming);
        runSever.removeSocketSet(incoming);
        runSever.removeStringSocketChannel(incoming);
    }
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String s) throws Exception { // (4)
        Channel incoming = ctx.channel();

        System.out.println("Socket服务端接收到的消息为: ["+s+"]");
        System.out.println("[" + incoming.remoteAddress() + "]" + s + "\n");
        if(!runSever.findSocketChannel(incoming)){
            //第一次链接到服务端
            runSever.addSocketChannel(incoming,s);
            runSever.addSocketSet(incoming);
            incoming.writeAndFlush("加入成功");
        }else {
            //判断是不是特殊的字符
            if(s.equals("aaa")){
                Channel channel =  runSever.findWebsocketChannel("aaa");
                channel.writeAndFlush(new TextWebSocketFrame("Socket向WebSocket发送了一个请求"))
                       .addListener((ChannelFuture writeFuture) -> {
                           //消息发送成功
                           if (writeFuture.isSuccess()) {
                               if(writeFuture.isSuccess()){
                                   incoming.writeAndFlush("Socket向WebSocket发送的消息："+s+" 成功");
                               }
                           }
                       });



            }
        }
        System.out.println(runSever.getWebsocketChannelGroup());

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception { // (5)
        Channel incoming = ctx.channel();
        System.out.println("Socket:"+incoming.remoteAddress()+"在线");

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception { // (6)
        Channel incoming = ctx.channel();
        System.out.println("Socket:"+incoming.remoteAddress()+"掉线");
        runSever.removeSocketChannel(incoming);
        runSever.removeSocketSet(incoming);
        runSever.removeStringSocketChannel(incoming);
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        Channel incoming = ctx.channel();
        System.out.println("Socket:"+incoming.remoteAddress()+"异常");
        // 当出现异常就关闭连接
        runSever.removeSocketChannel(incoming);
        runSever.removeSocketSet(incoming);
        runSever.removeStringSocketChannel(incoming);
        cause.printStackTrace();
        ctx.close();

    }
}
