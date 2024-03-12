package com.yeyiyi.plane.controller;

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




    @GetMapping("/test")
    public List<Map<String,Object>> test(@RequestParam String dictType) {

        List<Map<String,Object>> data = new ArrayList<>();
        return data;
    }
}
