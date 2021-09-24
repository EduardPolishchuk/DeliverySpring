package ua.training.delivery.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.training.delivery.service.CityService;
import ua.training.delivery.service.TariffService;

@Controller
@RequestMapping("/user")
public class UserController {


    private final CityService cityServiceImpl;

    private final TariffService tariffServiceImpl;

    @Autowired
    public UserController(CityService cityServiceImpl, TariffService tariffServiceImpl) {
        this.cityServiceImpl = cityServiceImpl;
        this.tariffServiceImpl = tariffServiceImpl;
    }

    @GetMapping("/")
    public String mainPage(Model model){
        model.addAttribute("cityList", cityServiceImpl.findAll());
        model.addAttribute("tariff", tariffServiceImpl.getTariff());
        return "user/userMain";
    }


    @GetMapping("/profile")
    public String profilePage(){
        return "user/userprofile";
    }
}
