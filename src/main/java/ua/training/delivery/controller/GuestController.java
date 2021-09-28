package ua.training.delivery.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import ua.training.delivery.entity.Order;
import ua.training.delivery.entity.Parcel;
import ua.training.delivery.service.CityService;
import ua.training.delivery.service.OrderService;
import ua.training.delivery.service.TariffService;

import javax.validation.Valid;
import java.util.Collection;

@Controller
public class GuestController {

    private final CityService cityServiceImpl;

    private final OrderService orderService;

    private final TariffService tariffServiceImpl;


    @Autowired
    public GuestController(CityService cityServiceImpl, OrderService orderService, TariffService tariffServiceImpl) {
        this.cityServiceImpl = cityServiceImpl;
        this.orderService = orderService;
        this.tariffServiceImpl = tariffServiceImpl;
    }

    @GetMapping("/")
    public String greetingPage(Model model) {
        Collection<? extends GrantedAuthority> roles =
                SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        for (GrantedAuthority grantedAuthority : roles) {
            if (grantedAuthority.getAuthority().equals("ROLE_USER")) {
                return "redirect:/user/";
            } else if (grantedAuthority.getAuthority().equals("ROLE_MANAGER")) {
                return "redirect:/manager/order_list";
            }
        }

        model.addAttribute("orderForm", Order.builder().parcel(new Parcel()).build());
        model.addAttribute("cityList", cityServiceImpl.findAll());
        model.addAttribute("tariff", tariffServiceImpl.getTariff());

        return "index";
    }

    @GetMapping("/calculate")
    public String calculate(@ModelAttribute("orderForm") @Valid Order orderForm,
                            BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "/error";
        }
        if (orderForm.getCityFrom().equals(orderForm.getCityTo())) {
            model.addAttribute("error", "sameCity");
        } else {
            model.addAttribute("calculatedValue", orderService.calculateOrderPrice(orderForm));
        }
        model.addAttribute("cityList", cityServiceImpl.findAll());
        model.addAttribute("tariff", tariffServiceImpl.getTariff());
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/success")
    public String success() {
        return "success";
    }

}
