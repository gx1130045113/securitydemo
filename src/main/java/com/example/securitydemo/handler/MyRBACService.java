package com.example.securitydemo.handler;



import com.example.securitydemo.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName MyRBACService
 * @Description: TODO
 * @Author 有丶优秀的少年
 * @Date 2020/11/19
 * @Version V1.0
 **/
@Component("rbacService")
public class MyRBACService {
    @Autowired
    SysUserMapper sysUserMapper;

    private AntPathMatcher antPathMatcher=new AntPathMatcher();

    public boolean hasPermission(HttpServletRequest request, Authentication authentication){
        Object principal=authentication.getPrincipal();

        if (principal instanceof UserDetails){
            String username=((UserDetails) principal).getUsername();
            List<String> urls = sysUserMapper.findUrlsByUserName(username);
           return urls.stream().anyMatch(
                    url ->antPathMatcher.match(url,request.getRequestURI())
            );
        }
        return false;
    }
}
