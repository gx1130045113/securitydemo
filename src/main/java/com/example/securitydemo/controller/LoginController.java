package com.example.securitydemo.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName LoginController
 * @Description: TODO
 * @Author 有丶优秀的少年
 * @Date 2020/10/19
 * @Version V1.0
 **/
@Controller
public class LoginController {

/*    @RequestMapping("/toMain")
    public String login(){
        System.out.println("我进来了");
        return "redirect:index.html";
    }

    @RequestMapping("/toError")
    public String toError(){
        System.out.println("我进来了");
        return "redirect:error.html";
    }*/

/*@GetMapping("test")
public String login(){
    System.out.println("我进来了");
    return "荒野行动";
}

    @GetMapping("update")
    @Secured({"ROLE_sale","ROLE_manager"})
    public String update(){

        return "update";
    }*/
@GetMapping("/syslog")
public String syslog(){
    return "syslog";
}

    @GetMapping("/sysuser")
    public String sysuser(){
        return "sysuser";
    }

    @GetMapping("/biz1")
    public String biz1(){
        return "biz1";
    }

    @GetMapping("/biz2")
    public String biz2(){
        return "biz2";
    }



}
