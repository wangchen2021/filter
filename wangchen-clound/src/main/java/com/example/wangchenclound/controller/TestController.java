package com.example.wangchenclound.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.wangchenclound.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
public class TestController {
    @Resource
    private TestService testService;
    @CrossOrigin(origins = "*", maxAge = 3600)
    @GetMapping("/test/note")
    public String test(){
        String username=testService.getById("1231").getUsername();
        return "接口测试"+username;
    }
    @GetMapping("/test/cross")
    public String cross(HttpServletRequest request){
        System.out.println(request);
        return request.getHeader("Host");
    }
}
