package ua.training.delivery.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.training.delivery.entity.Order;
import ua.training.delivery.entity.Parcel;
import ua.training.delivery.entity.User;
import ua.training.delivery.service.*;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private final ReceiptService receiptService;

    private final CityService cityService;

    private final OrderService orderService;

    private final TariffService tariffService;

    private final UserService userService;

    @Autowired
    public UserController(ReceiptService receiptService, CityService cityService,
                          OrderService orderService, TariffService tariffService,
                          UserService userService) {
        this.receiptService = receiptService;
        this.cityService = cityService;
        this.orderService = orderService;
        this.tariffService = tariffService;
        this.userService = userService;
    }

    @GetMapping
    public String mainPage(Model model) {
        model.addAttribute("orderForm", Order.builder().parcel(new Parcel()).build());
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
        List<Order> list = orderService.findUserOrders(user);
        model.addAttribute("orderList", list);
        return "user/userOrders";
    }

    @GetMapping("/receipts")
    public String receiptListPage(Model model, HttpSession session) {
        User user = (User) session.getAttribute("userProfile");
        model.addAttribute("receiptList", receiptService.findUserReceipts(user, false));
        return "user/userReceipts";
    }

    @PostMapping
    public String userOrderAction(@ModelAttribute("order") Order orderFrom,
                                  Model model, HttpSession session, @RequestParam() String action) {

        if ("makeOrder".equals(action)) {
            User user = (User) session.getAttribute("userProfile");
            orderFrom.setUserSender(user);
            orderFrom.setRequestDate(LocalDate.now());
            orderService.create(orderFrom);
            return "success";
        }


        model.addAttribute("cityList", cityService.findAll());
        model.addAttribute("tariff", tariffService.getTariff());
        return "user/userMain";
    }

    @PostMapping("/change_balance")
    public String changeBalance(HttpSession session, @RequestParam("amount") BigDecimal amount) {
        if (amount.intValue() < 0) {
            return "error";
        }
        User user = (User) session.getAttribute("userProfile");
        userService.balanceReplenishment(user, amount);
        return "redirect:/success";
    }


    @PostMapping("/pay_receipt")
    public String payReceipt(HttpSession session, @RequestParam("receiptID") Long receiptId) {
        User user = (User) session.getAttribute("userProfile");
        receiptService.userPaysReceipt(user, receiptId);
        return "redirect:/success";
    }
}
