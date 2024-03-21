package com.yeyiyi.plane.websocket;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;

/**
 * @author YeYiYi
 * @date 2024/3/11 17:28
 * @description
 */
@Slf4j
public class BinaryFrameHandler extends SimpleChannelInboundHandler<BinaryWebSocketFrame> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, BinaryWebSocketFrame frame) throws Exception {
        // 获取二进制数据内容
        ByteBuf content = frame.content();
        // 处理内容...
        log.info("接收到二进制数据：" + content.toString());

        // 响应客户端（可选）
        ctx.channel().writeAndFlush(new TextWebSocketFrame("已接收到二进制数据"));
    }
}
