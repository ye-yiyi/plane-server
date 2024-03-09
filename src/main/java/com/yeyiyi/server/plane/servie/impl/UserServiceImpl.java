package com.yeyiyi.server.plane.servie.impl;

import com.yeyiyi.server.plane.entity.User;
import com.yeyiyi.server.plane.mapper.UserMapper;
import com.yeyiyi.server.plane.servie.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public User gerUser(String userName, String passWord) {
        return userMapper.getUser(userName,passWord);
    }
}
