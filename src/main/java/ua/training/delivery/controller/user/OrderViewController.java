package ua.training.delivery.controller.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.training.delivery.entity.Order;
import ua.training.delivery.service.OrderService;

import javax.persistence.EntityNotFoundException;

@Controller
@RequestMapping("/user/order_view")
public class OrderViewController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public String orderViewPage(@RequestParam long orderID, Model model) {
        Order order = orderService.findById(orderID).orElseThrow(EntityNotFoundException::new);
        model.addAttribute("order", order);
        model.addAttribute("price",orderService.calculateOrderPrice(order));
        return "user/userOrderView";
    }
}
