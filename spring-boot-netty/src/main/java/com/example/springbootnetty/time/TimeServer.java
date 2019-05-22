package com.example.springbootnetty.time;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @ClassName TimeServer
 * @Description TODO
 * @Author liangkanglin
 * @Date 2019/5/15 15:37
 * Version 1.0
 **/
public class TimeServer {




    public static void bind(Integer port) throws Exception{

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup,workGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,1024)
                    .childHandler(new TimeServer.ChildChannelHandler());
                //绑定端口,同步等待成功
            ChannelFuture future=bootstrap.bind(port).sync();
            future.channel().closeFuture().sync();
            System.out.println(port+"端口的服务端开启了");
        }finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
    public static class ChildChannelHandler extends ChannelInitializer<SocketChannel> {

        @Override
        protected void initChannel(SocketChannel socketChannel) throws Exception {
            //对TCP数据流的拆包
            socketChannel.pipeline().addLast(new StringDecoder());
            socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024));
            //
            socketChannel.pipeline().addLast(new TimeServerHandler());
        }
    }

    public static void main(String[] args) throws Exception {
         TimeServer.bind(8080);
    }


}
