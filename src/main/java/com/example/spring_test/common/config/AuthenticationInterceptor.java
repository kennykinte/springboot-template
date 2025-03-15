package com.example.spring_test.common.config;

import java.io.PrintWriter;
import java.lang.reflect.Method;

import com.alibaba.fastjson2.JSONObject;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.spring_test.common.annotation.JwtIgnore;
import com.example.spring_test.common.dto.Response;
import com.example.spring_test.login.utils.JwtTokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        // 从http请求头中取出token
        final String token = request.getHeader(JwtTokenUtil.AUTH_HEADER_KEY);
        // 如果不是映射到方法，直接通过
        if (!(handler instanceof HandlerMethod handlerMethod)) {
            return true;
        }
        // 如果方法有JwtIgnore注解，直接通过
        Method method = handlerMethod.getMethod();
        if (method.isAnnotationPresent(JwtIgnore.class)) {
            JwtIgnore jwtIgnore = method.getAnnotation(JwtIgnore.class);
            if (jwtIgnore.value()) {
                return true;
            }
        }

        //  执行认证
        if (StringUtils.isEmpty(token)) {
            PrintWriter out = response.getWriter();
            out.append(JSONObject.toJSONString(Response.failed(401, "未登录")));
            return false;
        }

        try {
            JwtTokenUtil.verifyToken(token);
        } catch (TokenExpiredException e) {
            PrintWriter out = response.getWriter();
            out.append(JSONObject.toJSONString(Response.failed(402, "token已过期")));
            return false;
        } catch (Exception e) {
            PrintWriter out = response.getWriter();
            out.append(JSONObject.toJSONString(Response.failed(403, "token验证失败")));
            return false;
        }

        return true;
    }
}

