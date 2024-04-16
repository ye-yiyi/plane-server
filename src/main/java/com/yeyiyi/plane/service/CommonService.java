package com.yeyiyi.plane.service;

import com.yeyiyi.plane.entity.Server;
import com.yeyiyi.plane.entity.User;

import java.util.List;

public interface CommonService {

    List<Server> getServerList();

    void linkGame(String serverId, String userId, String gameName);

    void closeGame(String userId);
}
