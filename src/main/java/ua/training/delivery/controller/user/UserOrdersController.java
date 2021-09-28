package ua.training.delivery.controller.user;


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
import ua.training.delivery.entity.User;
import ua.training.delivery.service.OrderService;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserOrdersController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/orders")
    public String orderListPage(HttpSession session, Model model, @RequestParam(defaultValue = "id") String sortBy,
                                @RequestParam Optional<String> status,
                                @RequestParam("page") Optional<Integer> pageNum) {

        User user = (User) session.getAttribute("userProfile");
        Page<Order> page;
        if ((sortBy.contains("cityTo.name") || sortBy.contains("cityFrom.name"))) {
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
        model.addAttribute("currentDate", LocalDate.now());
        model.addAttribute("orderStatuses", OrderStatus.values());
        return "user/userOrders";
    }
}