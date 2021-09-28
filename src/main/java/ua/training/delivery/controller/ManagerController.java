package ua.training.delivery.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.training.delivery.entity.City;
import ua.training.delivery.entity.Order;
import ua.training.delivery.entity.OrderStatus;
import ua.training.delivery.entity.Role;
import ua.training.delivery.service.*;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/manager")
public class ManagerController {
    private static final Logger logger = LoggerFactory.getLogger(ManagerController.class);

    private final OrderService orderService;

    private final ReceiptService receiptService;

    private final UserService userService;

    private final TariffService tariffService;

    private final CityService cityService;

    @Autowired
    public ManagerController(OrderService orderService,
                             ReceiptService receiptService, UserService userService,
                             TariffService tariffService, CityService cityService) {
        this.orderService = orderService;
        this.receiptService = receiptService;
        this.userService = userService;
        this.tariffService = tariffService;
        this.cityService = cityService;
    }



    @GetMapping("/client_list")
    public String clientList(Model model) {
        model.addAttribute("clientList", userService.findAllByRole(Role.USER));
        return "manager/managerClientList";
    }





    @PostMapping("/send_receipt")
    public String sendReceipt(@RequestParam(name = "orderID") Long orderID) {
        receiptService.create(orderID);
        return "redirect:/manager/order_list";
    }

    @GetMapping("/order_details")
    public String orderDetails(Model model, @RequestParam Long orderID) {
        Order order = orderService.findById(orderID).orElseThrow(EntityNotFoundException::new);
        model.addAttribute("order", order);
        model.addAttribute("price", orderService.calculateOrderPrice(order));
        return "manager/managerOrderDetails";
    }
}
