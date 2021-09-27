package ua.training.delivery.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ua.training.delivery.entity.Order;
import ua.training.delivery.entity.OrderStatus;
import ua.training.delivery.entity.Parcel;
import ua.training.delivery.entity.User;
import ua.training.delivery.service.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

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
        model.addAttribute("orderForm", Order.builder().build());
        model.addAttribute("cityList", cityService.findAll());
        model.addAttribute("tariff", tariffService.getTariff());
        return "user/userMain";
    }


    @GetMapping("/profile")
    public String profilePage() {
        return "user/userprofile";
    }

    @GetMapping("/orders")
    public String orderListPage(HttpSession session, Model model, @RequestParam(defaultValue = "id") String sortBy,
                                @RequestParam Optional<String> status,
                                @RequestParam("page") Optional<Integer> pageNum) {

        User user = (User) session.getAttribute("userProfile");
        Page<Order> page;
        if ((sortBy.equals("cityTo.name") || sortBy.equals("cityFrom.name"))) {
            String locale = (String) session.getAttribute("locale");
            sortBy = "uk".equals(locale) ? sortBy.concat("Uk") : sortBy;
        }
        Sort.Direction direction = sortBy.contains("Desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(pageNum.orElse(0), 4,
                Sort.by(direction, sortBy.replace("Desc", "")));
        if (status.isPresent() && !status.get().isEmpty()) {
            page = orderService.findUserOrdersWithStatus(user, pageable, OrderStatus.valueOf(status.get()));
        } else {
            page = orderService.findUserOrders(user, pageable);
        }

        model.addAttribute("page", page);
        model.addAttribute("orderStatuses", OrderStatus.values());
        return "user/userOrders";
    }

    @GetMapping("/receipts")
    public String receiptListPage(Model model, HttpSession session) {
        User user = (User) session.getAttribute("userProfile");
        model.addAttribute("receiptList", receiptService.findUserReceipts(user, false));
        return "user/userReceipts";
    }

    @PostMapping
    public String userOrderAction(@ModelAttribute("orderForm") @Valid Order orderFrom, BindingResult bindingResult,
                                  Model model, HttpSession session, @RequestParam() String action) {

        if (bindingResult.hasErrors()) {
//            System.out.println("============================================");
//            System.out.println("============================================");
//           List<FieldError> list = bindingResult.getFieldErrors();
//            for (FieldError f: list                 ) {
//                System.out.println("FILED==>"+  f.getField());
//                System.out.println("MESS==>"+  f.getDefaultMessage());
//                System.out.println("OMJ NAME==>"+  f.getObjectName());
//                System.out.println("CODE==>"+  f.getCode());
//                System.out.println("CODE==>"+  f.getRejectedValue());
//            }
            return "user/userMain";
        }

        if ("makeOrder".equals(action)) {
            User user = (User) session.getAttribute("userProfile");
            orderFrom.setUserSender(user);
            orderFrom.setStatus(OrderStatus.WAITING_FOR_CONFIRM);
            orderFrom.setRequestDate(LocalDate.now());
            orderService.create(orderFrom);
            return "success";
        }

        model.addAttribute("calculatedValue", orderService.calculateOrderPrice(orderFrom));
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

        return receiptService.userPaysReceipt(user, receiptId) ?
                "redirect:/success" : "redirect:/error";
    }
}
