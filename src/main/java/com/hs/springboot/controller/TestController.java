package com.hs.springboot.controller;

import com.hs.springboot.pojo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author bys
 * @date 2020/9/24 10:18
 */
@RestController
public class TestController {

    @GetMapping("/getUser")
    public User getUser() {
        User user = new User();
        user.setId(1L);
        user.setAccount("12345678");
        user.setPassword("12345678");
        user.setEmail("123@qq.com");
        return user;
    }

    @PostMapping("/addUser")
    public void addUser(@Valid @RequestBody User user){
    }

}
