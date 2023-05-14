package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class Contr {
    @GetMapping("/1")
    public String showGetInfo1() {
        return "hello1";
    }

    @GetMapping("/2")
    public String showGetInfo2() {
        return "hello2";
    }

    @GetMapping("/3")
    public String showGetInfo3() {
        return "hello3";
    }

    @GetMapping("/4")
    public String showGetInfo4() {
        return "hello4";
    }
}
