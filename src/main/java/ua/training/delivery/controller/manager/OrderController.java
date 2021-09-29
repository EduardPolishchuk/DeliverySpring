package ua.training.delivery.controller.manager;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.training.delivery.entity.Order;
import ua.training.delivery.entity.OrderStatus;
import ua.training.delivery.service.OrderService;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpSession;
import java.util.Optional;

import static ua.training.delivery.constants.Constants.*;

@Controller
@RequestMapping("/manager")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/order_list")
    public String orderListPage(HttpSession session, Model model, @RequestParam(defaultValue = "id") String sortBy,
                                @RequestParam Optional<String> status,
                                @RequestParam("page") Optional<Integer> pageNum) {

        Page<Order> page;
        Sort.Direction direction = sortBy.contains(DESC) ? Sort.Direction.DESC : Sort.Direction.ASC;
        if ((sortBy.contains(CITY_TO_NAME) || sortBy.contains(CITY_FROM_NAME))) {
            String locale = (String) session.getAttribute("locale");
            sortBy = "uk".equals(locale) ? sortBy.concat("Uk") : sortBy;
        }
        Pageable pageable = PageRequest.of(pageNum.orElse(0), 4,
                Sort.by(direction, sortBy.replace(DESC, "")));

        if (status.isPresent() && !status.get().isEmpty()) {
            page = orderService.findOrdersWithStatus(pageable, OrderStatus.valueOf(status.get()));
        } else {
            page = orderService.findAll(pageable);
        }
        model.addAttribute(PAGE, page);
        model.addAttribute(ORDER_STATUSES, OrderStatus.values());
        return "manager/managerOrderList";
    }


    @GetMapping("/order_details")
    public String orderDetails(Model model, @RequestParam Long orderID) {
        Order order = orderService.findById(orderID).orElseThrow(EntityNotFoundException::new);
        model.addAttribute(ORDER, order);
        model.addAttribute(PRICE, orderService.calculateOrderPrice(order));
        return "manager/managerOrderDetails";
    }
}
