package ua.training.delivery.controller;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.training.delivery.entity.Order;
import ua.training.delivery.entity.User;
import ua.training.delivery.service.CityService;
import ua.training.delivery.service.OrderService;
import ua.training.delivery.service.ReceiptService;
import ua.training.delivery.service.TariffService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private final ReceiptService receiptService;

    private final CityService cityService;

    private final OrderService orderService;

    private final TariffService tariffService;


    @Autowired
    public UserController(ReceiptService receiptService, CityService cityService, OrderService orderService, TariffService tariffService) {
        this.receiptService = receiptService;
        this.cityService = cityService;
        this.orderService = orderService;
        this.tariffService = tariffService;
    }

    @GetMapping("/")
    public String mainPage(Model model) {
        model.addAttribute("cityList", cityService.findAll());
        model.addAttribute("tariff", tariffService.getTariff());
        return "user/userMain";
    }


    @GetMapping("/profile")
    public String profilePage() {
        return "user/userprofile";
    }

    @GetMapping("/orders")
    public String orderListPage(HttpSession session, Model model) {

        User user = (User) session.getAttribute("userProfile");
        System.out.println(user);
        List<Order> list = orderService.findUserOrders(user);
        System.out.println("LIST ================================>" + list);
        model.addAttribute("orderList", list);
        return "user/userOrders";
    }

    @GetMapping("/receipts")
    public String receiptListPage(Model model) {
//        User user = (User) model.getAttribute("userProfile");
//        model.addAttribute("receiptList", receiptService.findUserReceipts(user, false));
        return "redirect:/user/orders";
    }
}
