package com.example.springbootnetty.websocket;

import com.example.springbootnetty.socket.SocketInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @ClassName WSServer
 * @Description TODO
 * @Author liangkanglin
 * @Date 2019/5/16 12:53
 * Version 1.0
 **/
public class WSServer {
    public static void bind(Integer port) throws Exception{

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup,workGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,1024)
                    .childOption(ChannelOption.SO_KEEPALIVE,true)//(6)
                    .childHandler(new WebSocketInitializer());
            //绑定端口,同步等待成功
            ChannelFuture future=bootstrap.bind(port).sync();
            future.channel().closeFuture().sync();
            System.out.println(port+"端口的服务开启了");
        }finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        bind(8081);
    }
}
