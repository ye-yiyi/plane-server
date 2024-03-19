package com.yeyiyi.plane.mapper;

import com.yeyiyi.plane.entity.Server;
import com.yeyiyi.plane.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value ="userMapper")
public interface CommonMapper {

    List<Server> getServerList();

}
