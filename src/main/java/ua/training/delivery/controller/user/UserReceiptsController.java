package ua.training.delivery.controller.user;


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

@Controller
@RequestMapping("/user")
public class UserReceiptsController {

    @Autowired
    private ReceiptService receiptService;

    @GetMapping("/receipts")
    public String receiptListPage(Model model, HttpSession session) {
        User user = (User) session.getAttribute("userProfile");
        model.addAttribute("receiptList", receiptService.findUserReceipts(user, false));
        return "user/userReceipts";
    }


    @PostMapping("/pay_receipt")
    public String payReceipt(HttpSession session, @RequestParam("receiptID") Long receiptId) {
        User user = (User) session.getAttribute("userProfile");

        return receiptService.userPaysReceipt(user, receiptId) ?
                "redirect:/success" : "redirect:/insufficientFundsError";
    }
}
