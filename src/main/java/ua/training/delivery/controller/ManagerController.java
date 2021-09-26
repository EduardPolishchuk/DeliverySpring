package ua.training.delivery.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.training.delivery.entity.Order;
import ua.training.delivery.service.OrderService;
import ua.training.delivery.service.ReceiptService;
import ua.training.delivery.service.TariffService;
import ua.training.delivery.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/manager")
public class ManagerController {

    private final OrderService orderService;

    private final ReceiptService receiptService;

    private final UserService userService;

    private final TariffService tariffService;

    @Autowired
    public ManagerController(OrderService orderService, ReceiptService receiptService,
                             UserService userService, TariffService tariffService) {
        this.orderService = orderService;
        this.receiptService = receiptService;
        this.userService = userService;
        this.tariffService = tariffService;
    }

    @GetMapping("/order_list")
    public String orderListPage(Model model) {
        model.addAttribute("order", new Order());
        model.addAttribute("orderList", orderService.findAll());
        return "manager/managerOrderList";
    }

    @GetMapping("/client_list")
    public String clientList(Model model) {

        model.addAttribute("clientList", userService.findAll());
        return "manager/managerClientList";
    }

    @GetMapping("/tariff")
    public String tariffView(Model model) {

        model.addAttribute("tariff", tariffService.getTariff());
        return "manager/managerChangeTariff";
    }

    @GetMapping("/add_city")
    public String addCity(Model model) {

        return "manager/managerAddCity";
    }

    @PostMapping("/send_receipt")
    public String sendReceipt(@RequestParam(name = "orderID") Long orderID) {
        receiptService.create(orderID);
        return "redirect:/manager/order_list";
    }
}
