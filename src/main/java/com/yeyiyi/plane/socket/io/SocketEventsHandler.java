package com.yeyiyi.plane.socket.io;

import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.SocketIOServer;
import com.yeyiyi.plane.entity.Server;
import com.yeyiyi.plane.service.CommonService;
import com.yeyiyi.plane.socket.io.handler.ServerHandler;
import com.yeyiyi.plane.utils.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author YeYiYi
 * @date 2024/3/11 16:46
 * @description
 */
@Component
public class SocketEventsHandler  {

    @Autowired
    private CommonService commonService;

    @Autowired
    private ServerHandler serverHandler;

    // 一个注册所有事件的方法
    public void registerAllEvents(SocketIOServer server) {
        onConnection(server);
        onDisconnection(server);
        // ...注册其他事件
        onChatMessage(server);

        //注册区服命名空间
        List<Server> serverList = commonService.getServerList();
        if(serverList != null){
            for(Server gameServer:serverList){
                String serverCode = gameServer.getServerCode();
                SocketIONamespace namespace = server.addNamespace("/"+serverCode);
                serverHandler.init(namespace);

            }
        }
    }


    public void onChatMessage(SocketIOServer server) {
        server.addEventListener(Code.推送消息, String.class, (client, data, ackRequest) -> {
            // 处理聊天消息
            server.getBroadcastOperations().sendEvent("chat message", data);

        });
    }



    public void onConnection(SocketIOServer server) {
        server.addConnectListener(client -> {
            // 获取客户端的握手数据，进而获取连接地址
            String clientAddress = client.getHandshakeData().getAddress().toString();

            // 输出客户端的连接地址和会话ID
            System.out.println("客户端连接，地址：" + clientAddress + "，会话ID：" + client.getSessionId());
        });
    }

    public void onDisconnection(SocketIOServer server) {
        server.addDisconnectListener(client -> {
            // 获取客户端的握手数据，进而获取连接地址
            String clientAddress = client.getHandshakeData().getAddress().toString();

            // 输出客户端的连接地址和会话ID
            System.out.println("客户端断开，地址：" + clientAddress + "，会话ID：" + client.getSessionId());
        });
    }




}
