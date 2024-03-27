package com.yeyiyi.plane.socket.io.handler;

import com.alibaba.fastjson.JSONObject;
import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.SocketIOServer;
import com.yeyiyi.plane.entity.Server;
import com.yeyiyi.plane.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author YeYiYi
 * @date 2024/3/11 16:46
 * @description
 */
@Component
public class ServerHandler {

    @Autowired
    private CommonService commonService;

    // 一个注册所有事件的方法
    public void init(SocketIONamespace server) {
        onConnection(server);
        onDisconnection(server);
        // ...注册其他事件
        onChatMessage(server);
        linkGame(server);
        
    }


    public void linkGame(SocketIONamespace server) {
        server.addEventListener("link game", JSONObject.class, (client, data, ackRequest) -> {
            String userId = data.getString("userId");
            String gameName = data.getString("gameName");
            System.out.println(gameName+"加入了这个房间");
            server.getBroadcastOperations().sendEvent("chat message", gameName+"加入了这个房间");

        });
    }


    public void onChatMessage(SocketIONamespace server) {
        server.addEventListener("chat message", String.class, (client, data, ackRequest) -> {
            // 处理聊天消息
            server.getBroadcastOperations().sendEvent("chat message", data);

        });
    }



    public void onConnection(SocketIONamespace server) {
        server.addConnectListener(client -> {
            // 获取客户端的握手数据，进而获取连接地址
            String clientAddress = client.getHandshakeData().getAddress().toString();

            // 输出客户端的连接地址和会话ID
            System.out.println(server.getName()+"客户端连接，地址：" + clientAddress + "，会话ID：" + client.getSessionId());
        });
    }

    public void onDisconnection(SocketIONamespace server) {
        server.addDisconnectListener(client -> {
            // 获取客户端的握手数据，进而获取连接地址
            String clientAddress = client.getHandshakeData().getAddress().toString();

            // 输出客户端的连接地址和会话ID
            System.out.println(server.getName()+"客户端断开，地址：" + clientAddress + "，会话ID：" + client.getSessionId());
        });
    }




}
