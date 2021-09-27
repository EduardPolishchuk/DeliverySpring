package ua.training.delivery.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.training.delivery.entity.Order;
import ua.training.delivery.entity.OrderStatus;
import ua.training.delivery.service.OrderService;
import ua.training.delivery.service.ReceiptService;
import ua.training.delivery.service.TariffService;
import ua.training.delivery.service.UserService;

import java.util.Optional;

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
    public String orderListPage( Model model, @RequestParam(defaultValue = "id") String sortBy,
                                @RequestParam Optional<String> status,
                                @RequestParam("page") Optional<Integer> pageNum) {

        Page<Order> page;
        Sort.Direction direction = sortBy.contains("Desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(pageNum.orElse(0), 4,
                Sort.by(direction,sortBy.replace("Desc","")));
        if(status.isPresent() && !status.get().isEmpty()){
            page = orderService.findOrdersWithStatus(pageable, OrderStatus.valueOf(status.get()));
        }else {
            page = orderService.findAll(pageable);
        }
        model.addAttribute("page", page);
        model.addAttribute("orderStatuses", OrderStatus.values());
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
