package com.example.securitydemo.handler;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName MyAuthenticationFailureHandler
 * @Description: TODO
 * @Author 有丶优秀的少年
 * @Date 2020/11/13
 * @Version V1.0
 **/
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private  String url;

    public MyAuthenticationFailureHandler(String url){
        this.url=url;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.sendRedirect(url);
    }
}
