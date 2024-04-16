package com.yeyiyi.plane.controller;

import com.alibaba.fastjson.JSONObject;
import com.yeyiyi.plane.entity.Server;
import com.yeyiyi.plane.service.CommonService;
import com.yeyiyi.plane.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author YeYiYi
 * @date 2024/3/11 14:27
 * @description
 */
@RestController
@RequestMapping("/common")
public class CommonController {

    @Autowired
    private CommonService commonService;


    @CrossOrigin
    @GetMapping("/getServerList")
    public List<Server> getServerList() {
        return commonService.getServerList();
    }

    @CrossOrigin
    @GetMapping("/linkGame")
    public JSONObject linkGame(@RequestParam("gameName") String gameName, @RequestParam("serverId") String serverId) {
        JSONObject ret = new JSONObject();
        //生成唯一ID
        String userId = CommonUtils.getUserId(serverId,gameName);



        ret.put("userId",userId);
        return ret;
    }


    @CrossOrigin
    @GetMapping("/closeGame")
    public JSONObject closeGame(@RequestParam("userId") String userId) {
        JSONObject ret = new JSONObject();

        commonService.closeGame(userId);

        ret.put("code","1");
        ret.put("desc","成功");
        return ret;
    }






}
