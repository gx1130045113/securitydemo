package com.example.securitydemo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class SecuritydemoApplicationTests {

    @Test
    void contextLoads() {
        PasswordEncoder pw = new BCryptPasswordEncoder();
        String  encode=pw.encode("123456");
        System.out.println(encode);
        boolean matches=pw.matches("123",encode);
        System.out.println(matches);
    }

}
