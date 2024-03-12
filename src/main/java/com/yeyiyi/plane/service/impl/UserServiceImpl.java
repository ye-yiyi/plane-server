package com.yeyiyi.plane.service.impl;

import com.yeyiyi.plane.entity.User;
import com.yeyiyi.plane.mapper.UserMapper;
import com.yeyiyi.plane.service.UserService;
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
