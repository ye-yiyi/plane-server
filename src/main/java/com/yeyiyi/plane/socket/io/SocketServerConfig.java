package com.yeyiyi.plane.socket.io;

import com.corundumstudio.socketio.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author YeYiYi
 * @date 2024/3/11 16:44
 * @description
 */
@Component
public class SocketServerConfig {
    private final int port = 9000; // 指定服务端口
    private final String hostname = "127.0.0.1"; // 指定服务主机名

    public Configuration getSocketConfig() {
        Configuration config = new Configuration();
        config.setHostname(hostname);
        config.setPort(port);
        return config;
    }
}
