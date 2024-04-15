package com.yeyiyi.plane.entity;

import lombok.Data;


@Data
public class GameUser {
    private String userId;
    private String gameName;

    private String serverId;
    private long loginTime;
}
