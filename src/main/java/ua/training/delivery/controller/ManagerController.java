package ua.training.delivery.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.training.delivery.service.OrderService;
import ua.training.delivery.service.TariffService;
import ua.training.delivery.service.UserService;

@Controller
@RequestMapping("/manager")
public class ManagerController {

    private final OrderService orderService;

    private final UserService userService;

    @Autowired
    private TariffService tariffService;


    public ManagerController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping("/order_list")
    public String orderListPage(Model model) {
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

    public String sendReceipt(Model model){


        return "redirect:/manager/order_list";
    }
}
