package com.yeyiyi.plane.socket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;

/**
 * @author YeYiYi
 * @date 2024/3/11 17:11
 * @description
 */
@Slf4j
public class TextWebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    // 重写文本帧处理方法
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame frame) throws Exception {
        // 处理WebSocket收到的文本帧
        String request = frame.text();
        log.info("服务器收到：" + request);
        ctx.channel().writeAndFlush(new TextWebSocketFrame("回复：" + request));
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 当有客户端连接到服务器时会触发这个方法
        log.info("客户端连接：" + ctx.channel().remoteAddress());

        // 可以在这里进行一些必要的操作，比如添加到channel组，跟踪客户端连接等
        // Channels.add(ctx.channel());

        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        // 当客户端断开连接时会触发这个方法
        log.info("客户端断开：" + ctx.channel().remoteAddress());

        // 在这里执行清理工作，比如从channel组中移除，跟踪客户端断开连接等
        // Channels.remove(ctx.channel());

        super.channelInactive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // 处理异常
        cause.printStackTrace();
        ctx.close();
    }
}
