package ua.training.delivery.controller.user;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.training.delivery.entity.User;
import ua.training.delivery.service.ReceiptService;

import javax.servlet.http.HttpSession;

import static ua.training.delivery.constants.Constants.*;

@Controller
@RequestMapping("/user")
public class UserReceiptsController {

    public static final Logger logger = LoggerFactory.getLogger(UserReceiptsController.class);
    private final ReceiptService receiptService;

    @Autowired
    public UserReceiptsController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @GetMapping("/receipts")
    public String receiptListPage(Model model, HttpSession session) {
        User user = (User) session.getAttribute(USER_PROFILE);
        model.addAttribute(RECEIPT_LIST, receiptService.findUserReceipts(user, false));
        return "user/userReceipts";
    }


    @PostMapping("/pay_receipt")
    public String payReceipt(HttpSession session, @RequestParam(RECEIPT_ID) Long receiptId) {
        User user = (User) session.getAttribute(USER_PROFILE);
        logger.info("Receipt id: " + receiptId + " was paid by user: " + user.getLogin());
        return receiptService.userPaysReceipt(user, receiptId) ?
                "redirect:/success" : "redirect:/insufficientFundsError";
    }
}
