package com.yeyiyi.plane.mapper;

import com.yeyiyi.plane.entity.Server;
import com.yeyiyi.plane.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value ="commonMapper")
public interface CommonMapper {

    List<Server> getServerList();

    void linkGame(@Param("serverId") String serverId,@Param("userId") String userId,@Param("gameName") String gameName);
}
