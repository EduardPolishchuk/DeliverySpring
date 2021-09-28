package ua.training.delivery.controller.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.training.delivery.service.OrderService;

@Controller
@RequestMapping("/user/get_parcel")
public class GetParcelController {

    private final OrderService orderService;

    @Autowired
    public GetParcelController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public String getParcel(@RequestParam Long orderID) {
        orderService.getParcel(orderID);
        return "redirect:/user/orders";
    }
}
