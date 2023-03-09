package com.example.springproject.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Slf4j
@RestController
@RequestMapping("/sign")
public class SecurityController {
    @GetMapping("/hello")
    public String hello() {
        String hello = "Hello, everyone!";
        log.info("Hello for everyone");
        return hello;
    }

    @GetMapping("/in/user1")
    public String user(Principal principal) {
        String hello = "Hello, " + principal.getName() + "!";
        log.info("Hello for user");
        return hello;
    }

    @GetMapping("/in/admin")
    public String admin() {
        String hello = "Hello, admin!";
        log.info("Hello for admin");
        return hello;
    }

}
