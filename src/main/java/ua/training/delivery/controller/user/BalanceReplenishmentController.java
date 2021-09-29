package ua.training.delivery.controller.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.training.delivery.entity.User;
import ua.training.delivery.service.UserService;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

import static ua.training.delivery.constants.Constants.AMOUNT;
import static ua.training.delivery.constants.Constants.USER_PROFILE;


@Controller
@RequestMapping("/user/change_balance")
public class BalanceReplenishmentController {

    private final UserService userService;

    @Autowired
    public BalanceReplenishmentController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public String changeBalance(HttpSession session, @RequestParam(AMOUNT) BigDecimal amount) {
        if (amount.intValue() < 0) {
            return "error";
        }
        User user = (User) session.getAttribute(USER_PROFILE);
        userService.balanceReplenishment(user, amount);
        return "redirect:/success";
    }
}
