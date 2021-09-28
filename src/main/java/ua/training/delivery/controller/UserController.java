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

    @GetMapping("/receipts")
    public String receiptListPage(Model model, HttpSession session) {
        User user = (User) session.getAttribute("userProfile");
        model.addAttribute("receiptList", receiptService.findUserReceipts(user, false));
        return "user/userReceipts";
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
                "redirect:/success" : "redirect:/insufficientFundsError";
    }

    @PostMapping("/get_parcel")
    public String getParcel(@RequestParam Long orderID){

        orderService.getParcel(orderID);

        return "redirect:/user/orders";
    }
}
