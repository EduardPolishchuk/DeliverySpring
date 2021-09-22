package ua.training.delivery.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ua.training.delivery.service.impl.CityServiceImpl;

@org.springframework.stereotype.Controller
public class Controller {

    CityServiceImpl cityServiceImpl;

    @Autowired
    public Controller(CityServiceImpl cityServiceImpl) {
        this.cityServiceImpl = cityServiceImpl;
    }

    @GetMapping("/")
    public String greeting(Model model) {
        model.addAttribute("cityList", cityServiceImpl.findAll());
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/signUp")
    public String registerPage() {
        return "signUp";
    }

    @GetMapping("/success")
    public String success() {
        return "success";
    }

    @PostMapping("/signUp")
    public String register() {

        return "success";
    }
}
