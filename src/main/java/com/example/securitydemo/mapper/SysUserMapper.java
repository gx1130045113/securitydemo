package com.example.securitydemo.mapper;

import com.example.securitydemo.domain.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.securitydemo.handler.MyUserDetails;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gx
 * @since 2020-11-19
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    //根据用户查出用户信息
    MyUserDetails findByUserName(@Param("username") String username);

    //根据userid查询用户信息
    List<String> findRoleByUserName(@Param("username") String username);

    //根据用户角色查询权限
    List<String> findAuthorityByRoleCodes(@Param("roleCodes") List<String> roleCodes);

    List<String> findUrlsByUserName(@Param("username") String username);

}
