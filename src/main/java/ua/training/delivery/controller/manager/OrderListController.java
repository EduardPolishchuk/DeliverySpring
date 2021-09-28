package ua.training.delivery.controller.manager;


import org.springframework.beans.factory.annotation.Autowired;
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

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping("/manager/order_list")
public class OrderListController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public String orderListPage(HttpSession session, Model model, @RequestParam(defaultValue = "id") String sortBy,
                                @RequestParam Optional<String> status,
                                @RequestParam("page") Optional<Integer> pageNum) {

        Page<Order> page;
        Sort.Direction direction = sortBy.contains("Desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        if ((sortBy.contains("cityTo.name") || sortBy.contains("cityFrom.name"))) {
            String locale = (String) session.getAttribute("locale");
            sortBy = "uk".equals(locale) ? sortBy.concat("Uk") : sortBy;
        }
        Pageable pageable = PageRequest.of(pageNum.orElse(0), 4,
                Sort.by(direction, sortBy.replace("Desc", "")));

        if (status.isPresent() && !status.get().isEmpty()) {
            page = orderService.findOrdersWithStatus(pageable, OrderStatus.valueOf(status.get()));
        } else {
            page = orderService.findAll(pageable);
        }
        model.addAttribute("page", page);
        model.addAttribute("orderStatuses", OrderStatus.values());
        return "manager/managerOrderList";
    }
}
