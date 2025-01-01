package com.springbootlearn.journalApp.controller_1;

import com.springbootlearn.journalApp.entity_2.User;
import com.springbootlearn.journalApp.service_3.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("public")
public class publicController {
    @GetMapping("/health-check")
    public String healthcheck()
    {
        return "ok";
    }

    @Autowired
    private Userservice userservice;

    @PostMapping("Create-user")
    public void CreateNewUser(@RequestBody User user)
    {
//        userservice.saveUser(user);
        userservice.saveNewUser(user); //here password will be encrypted
    }
}

