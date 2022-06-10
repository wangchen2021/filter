package com.example.wangchenclound.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.wangchenclound.domain.User;
import com.example.wangchenclound.service.UserService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

@RestController
public class LoginRegisterController {
    @Resource
    private UserService userService;
    @CrossOrigin(origins = "*", maxAge = 3600)
    @PutMapping("/FBuilder/login")
    public Object login(@RequestBody User iputuser){
        User user=userService.getOne(new QueryWrapper<User>().eq("username",iputuser.getUsername()));
        JSONObject jsonObject=new JSONObject();
        if(user!=null){
            if(user.getPassword().equals(iputuser.getPassword())){
                jsonObject.put("Token","testToken");
                jsonObject.put("user",user.getUsername());
                jsonObject.put("result","Login successfully");
            }else {
                jsonObject.put("user","Password Error");
                jsonObject.put("result","Login failed");
            }
        }else {
            jsonObject.put("user","This user is not exits");
            jsonObject.put("result","Login failed");
        }
        return jsonObject;
    }

}
