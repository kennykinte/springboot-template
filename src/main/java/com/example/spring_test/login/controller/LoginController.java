package com.example.spring_test.login.controller;

import com.example.spring_test.common.annotation.JwtIgnore;
import com.example.spring_test.login.utils.JwtTokenUtil;
import com.example.spring_test.login.service.LoginService;
import com.example.spring_test.common.dto.Response;
import com.example.spring_test.login.dto.UserLoginData;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class LoginController {

    @Resource
    private LoginService loginService;

    @JwtIgnore
    @PostMapping("/login")
    public Response<Map<String, Object>> login(@RequestBody UserLoginData userDto) {
        try {
            Map<String, Object> map = loginService.login(userDto);
            return Response.success(map);
        } catch (Throwable t) {
            return Response.failed(t);
        }
    }

    @JwtIgnore
    @GetMapping("/info")
    public Response<String> getInfo(@RequestParam("token") String token) {
        try {
            return Response.success(JwtTokenUtil.verifyToken(token));
        } catch (Throwable t) {
            return Response.failed(t);
        }
    }

    @JwtIgnore
    @PostMapping("/logout")
    public Response<Void> logout() {
        try {
            return Response.success(null);
        } catch (Throwable t) {
            return Response.failed(t);
        }
    }
}
