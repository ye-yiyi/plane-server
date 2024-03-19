package com.yeyiyi.plane.controller;

import com.yeyiyi.plane.entity.Server;
import com.yeyiyi.plane.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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



    @GetMapping("/getServerList")
    public List<Server> getServerList() {

        List<Server> serverList = commonService.getServerList();
        return serverList;
    }






}
