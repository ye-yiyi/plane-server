package com.yeyiyi.server.plane.servie;

import com.yeyiyi.server.plane.entity.User;

public interface UserService {

    User gerUser(String userName, String passWord);

}
