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

    @GetMapping("/order_list")
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

    @GetMapping("/client_list")
    public String clientList(Model model) {
        model.addAttribute("clientList", userService.findAllByRole(Role.USER));
        return "manager/managerClientList";
    }

    @GetMapping("/tariff")
    public String tariffView(Model model) {
        model.addAttribute("tariff", tariffService.getTariff());
        return "manager/managerChangeTariff";
    }

    @GetMapping("/add_city")
    public String addCityGet(Model model) {
        model.addAttribute("cityForm", new City());
        return "manager/managerAddCity";
    }

    @PostMapping("/add_city")
    public String addCityPost(@ModelAttribute @Valid City cityForm, BindingResult bindingResult, Model model,
                              @RequestParam float lngDeg, @RequestParam float lngMin, @RequestParam float lngSec,
                              @RequestParam float latDeg, @RequestParam float latMin, @RequestParam float latSec,
                              @RequestParam String latParam, @RequestParam String lngParam) {

        boolean notValid = false;
        float latitude = cityService.convertToDecimalDegrees(latDeg, latMin, latSec);
        float longitude = cityService.convertToDecimalDegrees(lngDeg, lngMin, lngSec);
        latitude = "north".equals(latParam) ? latitude : -1 * latitude;
        longitude = "east".equals(lngParam) ? longitude : -1 * longitude;

        if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors()
                    .forEach(s -> model.addAttribute("nameError", s.getRejectedValue()));
            model.addAttribute("errro", "errror");
            notValid = true;
        }
        if (latitude > 90 || longitude > 90) {
            model.addAttribute("coordinateError", "coordinateError");
            notValid = true;
        }
        if (notValid) {
            return "manager/managerAddCity";
        }
        cityForm.setLatitude(latitude);
        cityForm.setLongitude(longitude);
        try {
            cityService.create(cityForm);
            return "redirect:/success";
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("cityExitsError", cityForm.getName());
            logger.error("City: " + cityForm.getName() + " already exists!");
            return "manager/managerAddCity";
        }

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
