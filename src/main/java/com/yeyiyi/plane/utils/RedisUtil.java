package com.yeyiyi.plane.utils;


import redis.clients.jedis.Jedis;

public class RedisUtil {

    private static Jedis redis;



    public static Jedis getRedis(RedisCfg redisCfg){
        if(redis ==null){
            redis = new Jedis(redisCfg.getIp(),redisCfg.getPort());
            redis.auth(redisCfg.getPassword());
        }

        return redis;
    }

    public static void main(String[] args) {

    }

}
