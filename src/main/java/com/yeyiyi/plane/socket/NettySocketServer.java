package com.yeyiyi.plane.socket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.util.CharsetUtil;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author YeYiYi
 * @date 2024/3/11 17:02
 * @description
 */
@Component
public class NettySocketServer {
    private final int port = 9000; // 指定服务端口

    @PostConstruct
    public void start() throws Exception {
        new Thread(() -> {
            EventLoopGroup bossGroup = new NioEventLoopGroup();
            EventLoopGroup workerGroup = new NioEventLoopGroup();
            try {
                ServerBootstrap b = new ServerBootstrap();
                b.group(bossGroup, workerGroup)
                        .channel(NioServerSocketChannel.class)
                        .childHandler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            protected void initChannel(SocketChannel ch) {
                                ChannelPipeline pipeline = ch.pipeline();

                                // 添加处理HTTP请求的编解码器
                                pipeline.addLast(new HttpServerCodec());
                                // 添加支持大数据流的处理器
                                pipeline.addLast(new HttpObjectAggregator(65536));
                                // 添加WebSocket支持。
                                // "/websocket"是你的WebSocket的URL路径
                                pipeline.addLast(new WebSocketServerProtocolHandler("/websocket"));
                                // 添加文本帧处理器
                                pipeline.addLast(new TextWebSocketFrameHandler());
                                // 添加二进制处理器
                                pipeline.addLast(new BinaryFrameHandler());


                            }
                        });

                // 绑定端口并启动服务器接受连接
                ChannelFuture f = b.bind(port).sync();
                System.out.println("Socket server started on port " + port);
                // 对关闭的channel进行监听
                f.channel().closeFuture().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                try {
                    System.out.println("Socket server stopped");
                    workerGroup.shutdownGracefully().sync();
                    bossGroup.shutdownGracefully().sync();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }, "netty-server").start();
    }
}


