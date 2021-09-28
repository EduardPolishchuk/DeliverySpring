package ua.training.delivery.controller.user;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.training.delivery.entity.Order;
import ua.training.delivery.entity.OrderStatus;
import ua.training.delivery.entity.Parcel;
import ua.training.delivery.entity.User;
import ua.training.delivery.service.CityService;
import ua.training.delivery.service.OrderService;
import ua.training.delivery.service.TariffService;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDate;
import static ua.training.delivery.constants.Constants.*;


@Controller
@RequestMapping("/user/")
public class MakeOrderController {

    private static final Logger logger = LoggerFactory.getLogger(MakeOrderController.class);

    private final CityService cityService;

    private final TariffService tariffService;

    private final OrderService orderService;

    @Autowired
    public MakeOrderController(CityService cityService, TariffService tariffService,
                               OrderService orderService) {
        this.cityService = cityService;
        this.tariffService = tariffService;
        this.orderService = orderService;
    }

    @GetMapping
    public String mainPage(Model model) {
        model.addAttribute(ORDER_FORM, Order.builder().parcel(new Parcel()).build());
        model.addAttribute(CITY_LIST, cityService.findAll());
        model.addAttribute("tariff", tariffService.getTariff());
        return "user/userMain";
    }

    @GetMapping("/order_action")
    public String userOrderAction(@ModelAttribute(ORDER_FORM) @Valid Order orderFrom, BindingResult bindingResult,
                                  Model model, HttpSession session, @RequestParam() String action) {

        if (bindingResult.hasErrors()) {
            return "/error";
        }
        if (orderFrom.getCityFrom().equals(orderFrom.getCityTo())) {
            model.addAttribute(ERROR, SAME_CITY);
            model.addAttribute(CITY_LIST, cityService.findAll());
            model.addAttribute(TARIFF, tariffService.getTariff());
            return "/user/userMain";
        }
        if (MAKE_ORDER.equals(action)) {
            User user = (User) session.getAttribute(USER_PROFILE);
            orderFrom.setUserSender(user);
            orderFrom.setStatus(OrderStatus.WAITING_FOR_CONFIRM);
            orderFrom.setRequestDate(LocalDate.now());
            if (orderFrom.getParcel().getType() == null || orderFrom.getParcel().getType().isEmpty()) {
                orderFrom.getParcel().setType("Other");
            }
            orderService.create(orderFrom);
            logger.info("Order was made, ID: " + orderFrom.getId());
            return "redirect:/success";
        }
        model.addAttribute(CALCULATED_VALUE, orderService.calculateOrderPrice(orderFrom));
        model.addAttribute(CITY_LIST, cityService.findAll());
        model.addAttribute(TARIFF, tariffService.getTariff());
        return "user/userMain";
    }
}
