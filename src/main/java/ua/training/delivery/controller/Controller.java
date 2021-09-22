package ua.training.delivery.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@org.springframework.stereotype.Controller
public class Controller {
    @GetMapping("/hello")
    public String greeting(){
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/signUp")
    public String registerPage(){
        return "signUp";
    }

    @GetMapping("/success")
    public String success(){
        return "success";
    }

    @PostMapping("/signUp")
    public String register(){

        return "success";
    }
}
