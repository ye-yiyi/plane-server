package com.yeyiyi.plane.socket.io;

import com.corundumstudio.socketio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author YeYiYi
 * @date 2024/3/11 15:23
 * @description
 */
//@Component
public class SocketServer {
    @Autowired
    private SocketServerConfig config;

    @Autowired
    private SocketEventsHandler eventsHandler;

    @PostConstruct
    public void start() {
        new Thread(() -> {
            Configuration socketConfig = config.getSocketConfig();
            final SocketIOServer server = new SocketIOServer(socketConfig);

            // 注册所有事件
            eventsHandler.registerAllEvents(server);

            server.start();
            System.out.println("Socket.IO server started on port " + socketConfig.getPort());

            try {
                Thread.sleep(Integer.MAX_VALUE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                server.stop();
                System.out.println("Socket.IO server stopped");
            }

        }, "socket.io-server").start();
    }
}
