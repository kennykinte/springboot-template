package com.example.spring_test.login.service;

import com.alibaba.fastjson2.JSONObject;
import com.example.spring_test.login.dto.UserLoginData;
import com.example.spring_test.login.dto.UserToken;
import com.example.spring_test.login.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class LoginService {

    public Map<String, Object> login(UserLoginData userLoginData) throws Exception {
        try {
            Map<String, Object> map = new HashMap<>();

            if (!"admin".equals(userLoginData.getUsername()) || !"111111".equals(userLoginData.getPassword())) {
                throw new Exception("账号密码错误");
            }

            UserToken userToken = new UserToken();
            userToken.setUsername(userLoginData.getUsername());
            userToken.setPassword(userLoginData.getPassword());

            map.put("user", userToken);
            map.put("token", JwtTokenUtil.createToken(JSONObject.toJSONString(userToken)));

            return map;
        } catch (Exception e) {
            log.error("======================login failed", e);
            throw new Exception(e.getMessage());
        }
    }
}

