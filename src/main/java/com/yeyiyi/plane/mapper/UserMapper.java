package com.yeyiyi.plane.mapper;

import com.yeyiyi.plane.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component(value ="userMapper")
public interface UserMapper {

    User getUser(@Param("userName") String userName, @Param("passWord") String passWord);

}
