package com.yeyiyi.server.plane.controller;

import com.yeyiyi.server.plane.entity.User;
import com.yeyiyi.server.plane.servie.UserService;
import com.yeyiyi.server.plane.utils.RedisCfg;
import com.yeyiyi.server.plane.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;

@Controller
public class DemoController {

    @Autowired
    private UserService userService;

    @Autowired
    RedisCfg redisCfg;


    @GetMapping("/")
//    @ResponseBody
    public String login(){

        Jedis redis = RedisUtil.getRedis(redisCfg);
        //NX是不存在时才set， XX是存在时才set， EX是秒，PX是毫秒
        redis.set("test","test11", "NX", "EX", 5);
        System.out.println(redis.get("test"));

        //HashMap
        redis.hset("map","key","val");
        redis.hset("map","key2","val2");
        System.out.println(redis.hget("map","key2"));


        //list
        //lpush()在头部添加元素，rpush()在尾部添加元素
        //在头部添加元素，最后一个元素是null
        redis.lpush("array", "1","tst","null");
        List<String> lrange = redis.lrange("array", 0, -1);
        for (String item : lrange) {
            System.out.println(item);
        }
        //取出头部的第一个元素lpop();取出尾部的第一个元素rpop()
        //String lpop = redis.rpop("array");
        //System.out.println(lpop);

        //返回集合列表中元素的个数,取列表中的数据不会改变列表的长度
        //Long longth = redis.llen("array");
        //System.out.println(longth);


        // SortSet
        HashMap<String,Double> hashMap = new HashMap<String,Double>();
        hashMap.put("a", (double) 100);
        hashMap.put("b", (double) 80);
        hashMap.put("c", (double) 50);
        redis.zadd("hashMap", hashMap);
        //获取指定成员的分数
        Double zscore = redis.zscore("hashMap", "c");
        System.out.println("获取的分数是"+zscore);
        //获取成员的数量（这个里面有多少对成员）
        Long zcard = redis.zcard("hashMap");
        System.out.println("成员的数量:"+zcard);
        //删除指定的成员
        Long zrem = redis.zrem("hashMap", "c");
        System.out.println(zrem);



//        return "shanghai/login";
        return "shanghai/LtJtApp/page/home";
    }


    @PostMapping("/loginIn")
    public String loginIn(String userName,String passWord){
        System.out.println(userName);
        System.out.println(passWord);

        User user = userService.gerUser(userName,passWord);
        if(user != null){
            return "success";
        }

        return "error";
    }



    public String requestToJson(HttpServletRequest request) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
        String line = "";
        StringBuilder sb = new StringBuilder();
        while ((line = bf.readLine()) != null) {
            sb.append(line).append("\t\n");
        }

        return sb.toString();
    }



}
