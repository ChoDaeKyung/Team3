package com.example.tobi.team3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main")
public class MemberController {

    @GetMapping
    public String main() {
        return "main";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/join")
    public String signUp() {
        return "signUp";
    }

    @GetMapping("/find")
    public String Find() {
        return "find";
    }

    @GetMapping("/find/findId")
    public String FindId() {
        return "findId";
    }

    @GetMapping("/find/changePw")
    public String changePw() {
        return "changePw";
    }
}