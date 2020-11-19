package com.example.securitydemo.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName MyExpiredSessionStrategy
 * @Description: TODO
 * @Author 有丶优秀的少年
 * @Date 2020/11/18
 * @Version V1.0
 **/
public class MyExpiredSessionStrategy implements SessionInformationExpiredStrategy {

    private  static ObjectMapper objectMapper=new ObjectMapper();
    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent sessionInformationExpiredEvent) throws IOException, ServletException {
        Map<String, Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","您在账号在另一设备上已登录");
        sessionInformationExpiredEvent.getResponse().setContentType("application/json;charset=UTF-8");
        sessionInformationExpiredEvent.getResponse().getWriter().write(objectMapper.writeValueAsString(map));
    }
}
