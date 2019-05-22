package com.example.springbootnetty.websocket;

import com.example.springbootnetty.runSever;
import com.example.springbootnetty.socket.SocketMap;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @ClassName WebSocketHandler  处理WebSocket请求
 * @Description TODO
 * @Author liangkanglin
 * @Date 2019/5/16 10:26
 * Version 1.0
 **/
public class WebSocketHandler extends
        SimpleChannelInboundHandler<TextWebSocketFrame> {

    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx,
                                TextWebSocketFrame msg) throws Exception { // (1)
        Channel incoming = ctx.channel();
        incoming.writeAndFlush(new TextWebSocketFrame("WebSocket服务端接收的消息为 ["+msg.text()+"]"));
        System.out.println("WebSocket服务端接收的消息为 ["+msg.text()+"]");
        if(!runSever.findWebsocketChannel(incoming)){
            //第一次链接到服务端
            runSever.addWebsocketChannel(incoming,msg.text());
            runSever.addWebsocketSet(incoming);
            System.out.println(incoming.remoteAddress()+"加入成功");
            incoming.writeAndFlush("加入成功");
        }else {
            //判断是不是特殊的字符
            if(msg.text().equals("aaa")){
                Channel channel =  runSever.findSocketChannel("aaa");
                channel.writeAndFlush("WebSocket向Socket发送了一个请求")
                        .addListener((ChannelFuture writeFuture) -> {
                            //消息发送成功
                            if (writeFuture.isSuccess()) {
                                if(writeFuture.isSuccess()){
                                    writeFuture.channel().writeAndFlush(
                                            new TextWebSocketFrame("WebSocket向Socket发送的消息："+msg.text()+" 成功"));
                                }
                            }
                        });

            }
        }

        System.out.println(runSever.getWebsocketChannelGroup());


    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {  // (2)
        Channel incoming = ctx.channel();

       /* // Broadcast a message to multiple Channels
        channels.writeAndFlush(new TextWebSocketFrame("[SERVER] - " + incoming.remoteAddress() + " 加入"));
        channels.add(incoming);*/
        System.out.println("Client:" + incoming.remoteAddress() + "加入");
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {  // (3)
        Channel incoming = ctx.channel();

        // Broadcast a message to multiple Channels
/*        channels.writeAndFlush(new TextWebSocketFrame("[SERVER] - " + incoming.remoteAddress() + " 离开"));
        System.out.println("Client:" + incoming.remoteAddress() + "离开");*/
        // A closed Channel is automatically removed from ChannelGroup,
        // so there is no need to do "channels.remove(ctx.channel());"
        runSever.removeWebsocketChannel(incoming);
        runSever.removeWebsocketSet(incoming);
        runSever.removeStringWebsocketChannel(incoming);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception { // (5)
        Channel incoming = ctx.channel();
        System.out.println("Client:" + incoming.remoteAddress() + "在线");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception { // (6)
        Channel incoming = ctx.channel();
        System.out.println("Client:" + incoming.remoteAddress() + "掉线");
        runSever.removeWebsocketChannel(incoming);
        runSever.removeWebsocketSet(incoming);
        runSever.removeStringWebsocketChannel(incoming);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)    // (7)
            throws Exception {
        Channel incoming = ctx.channel();
                runSever.removeWebsocketChannel(incoming);
                runSever.removeWebsocketSet(incoming);
                runSever.removeStringWebsocketChannel(incoming);
        System.out.println("Client:" + incoming.remoteAddress() + "异常");
        // 当出现异常就关闭连接
        cause.printStackTrace();
        ctx.close();
    }
}
