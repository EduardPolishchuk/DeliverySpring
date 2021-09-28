package ua.training.delivery.controller.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.training.delivery.entity.Order;
import ua.training.delivery.entity.OrderStatus;
import ua.training.delivery.entity.Parcel;
import ua.training.delivery.entity.User;
import ua.training.delivery.service.CityService;
import ua.training.delivery.service.OrderService;
import ua.training.delivery.service.TariffService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDate;

@Controller
@RequestMapping("/user/")
public class MakeOrderController {

    private final CityService cityService;

    private final TariffService tariffService;

    private final OrderService orderService;

    @Autowired
    public MakeOrderController(CityService cityService, TariffService tariffService,
                               OrderService orderService) {
        this.cityService = cityService;
        this.tariffService = tariffService;
        this.orderService = orderService;
    }

    @GetMapping
    public String mainPage(Model model) {
        model.addAttribute("orderForm", Order.builder().parcel(new Parcel()).build());
        model.addAttribute("cityList", cityService.findAll());
        model.addAttribute("tariff", tariffService.getTariff());
        return "user/userMain";
    }

    @GetMapping("/order_action")
    public String userOrderAction(@ModelAttribute("orderForm") @Valid Order orderFrom, BindingResult bindingResult,
                                  Model model, HttpSession session, @RequestParam() String action) {

        if (bindingResult.hasErrors()) {
            return "/error";
        }
        if (orderFrom.getCityFrom().equals(orderFrom.getCityTo())) {
            model.addAttribute("error", "sameCity");
            model.addAttribute("cityList", cityService.findAll());
            model.addAttribute("tariff", tariffService.getTariff());
            return "/user/userMain";
        }
        if ("makeOrder".equals(action)) {
            User user = (User) session.getAttribute("userProfile");
            orderFrom.setUserSender(user);
            orderFrom.setStatus(OrderStatus.WAITING_FOR_CONFIRM);
            orderFrom.setRequestDate(LocalDate.now());
            if (orderFrom.getParcel().getType() == null || orderFrom.getParcel().getType().isEmpty()) {
                orderFrom.getParcel().setType("Other");
            }
            orderService.create(orderFrom);
            return "redirect:/success";
        }
        model.addAttribute("calculatedValue", orderService.calculateOrderPrice(orderFrom));
        model.addAttribute("cityList", cityService.findAll());
        model.addAttribute("tariff", tariffService.getTariff());
        return "user/userMain";
    }
}
