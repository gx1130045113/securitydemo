package com.example.securitydemo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.securitydemo.domain.SysUser;
import com.example.securitydemo.entity.Users;
import com.example.securitydemo.handler.MyUserDetails;
import com.example.securitydemo.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName UserDetailServiceImpl
 * @Description: TODO
 * @Author 有丶优秀的少年
 * @Date 2020/10/19
 * @Version V1.0
 **/
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //加载基础用户信息
        MyUserDetails myUserDetails=sysUserMapper.findByUserName(s);
        //加载用户角色列表
        List<String> roleCodes=sysUserMapper.findRoleByUserName(s);
        //通过用户列表加载用户资源权限列表
        List<String> authorties=sysUserMapper.findAuthorityByRoleCodes(roleCodes);
        //角色是个特殊的权限 有ROLE_前缀
        roleCodes=roleCodes.stream()
                .map(rc ->"ROLE_"+rc)
                .collect(Collectors.toList());

        authorties.addAll(roleCodes);
        myUserDetails.setAuthorities(
                AuthorityUtils.commaSeparatedStringToAuthorityList(
                        String.join(",",authorties)
                ));

/*        QueryWrapper<SysUser> wrapper = new QueryWrapper();
        wrapper.eq("username",s);
        SysUser sysUser = sysUserMapper.selectOne(wrapper);
        if (sysUser==null){
                throw new UsernameNotFoundException("用户不存在");
        }*/
    /*    System.out.println("执行了");
            if (!"admin".equals(s)){
                throw  new UsernameNotFoundException("用户不存在");
            }*/
            //密码加密
           // String password=passwordEncoder.encode(users.getPassword());
       // List<GrantedAuthority> auths=AuthorityUtils.commaSeparatedStringToAuthorityList("admins,ROLE_saler");

      //  return new User(sysUser.getUsername(),new BCryptPasswordEncoder().encode(sysUser.getPassword()),auths);
        return myUserDetails;
    }
}
