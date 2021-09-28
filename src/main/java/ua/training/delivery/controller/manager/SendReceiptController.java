package ua.training.delivery.controller.manager;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.training.delivery.service.ReceiptService;

@Controller
@RequestMapping("/manager/send_receipt")
public class SendReceiptController {

    private final ReceiptService receiptService;

    @Autowired
    public SendReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @PostMapping
    public String sendReceipt(@RequestParam(name = "orderID") Long orderID) {
        receiptService.create(orderID);
        return "redirect:/manager/order_list";
    }
}
