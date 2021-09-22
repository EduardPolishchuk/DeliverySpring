package ua.training.delivery.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.training.delivery.service.CityService;
import ua.training.delivery.service.TariffService;
import ua.training.delivery.service.impl.CityServiceImpl;

@Controller
public class MainController {

    private final CityService cityServiceImpl;

    private final TariffService tariffServiceImpl;

    @Autowired
    public MainController(CityService cityServiceImpl, TariffService tariffServiceImpl) {
        this.cityServiceImpl = cityServiceImpl;
        this.tariffServiceImpl = tariffServiceImpl;
    }



    @GetMapping("/")
    public String greeting(Model model) {
        model.addAttribute("cityList", cityServiceImpl.findAll());
        model.addAttribute("tariff", tariffServiceImpl.getTariff());
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
