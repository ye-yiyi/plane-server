package com.yeyiyi.plane.service.impl;

import com.yeyiyi.plane.entity.Server;
import com.yeyiyi.plane.entity.User;
import com.yeyiyi.plane.mapper.CommonMapper;
import com.yeyiyi.plane.mapper.UserMapper;
import com.yeyiyi.plane.service.CommonService;
import com.yeyiyi.plane.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    private CommonMapper mapper;


    @Override
    public List<Server> getServerList() {
        return mapper.getServerList();
    }

    @Override
    public void linkGame(String serverId, String userId, String gameName) {
        mapper.linkGame(serverId,userId,gameName);
    }
}
