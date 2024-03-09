package com.yeyiyi.server.plane.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "redis")
public class RedisCfg {
    public String ip;
    public int port;
    public String password;




}
