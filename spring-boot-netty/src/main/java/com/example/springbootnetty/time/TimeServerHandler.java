package com.example.springbootnetty.time;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;

import java.util.Date;

/**
 * @ClassName TimeServerHandler
 * @Description TODO
 * @Author liangkanglin
 * @Date 2019/5/15 15:49
 * Version 1.0
 **/
public class TimeServerHandler extends ChannelInboundHandlerAdapter {



    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
//        ByteBuf buf = (ByteBuf) msg;
//        byte[] req = new byte[buf.readableBytes()];
//        buf.readBytes(req);
        String body = (String)msg;
//        String body = new String(req, "UTF-8");
        System.out.println("The time server receive order:" + body);
        String currentTime="Q". equalsIgnoreCase(body)? new Date(System.currentTimeMillis()).toString() : "BAD ORDER";
        ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());

        final ChannelFuture f =  ctx.writeAndFlush(resp);// (3)
        f.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                if(channelFuture.isSuccess()){
                    System.out.println("消息發送成功");
                }else {
                    System.out.println("消息發送失败");

                }
            }
        }); // (4)
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
