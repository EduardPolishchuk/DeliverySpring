package ua.training.delivery.controller;


import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ErrorControllerImpl implements ErrorController {

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String errorPage() {
        return "error";
    }

    @RequestMapping(value = "/noAccess", method = RequestMethod.GET)
    public String noAccessPage() {
        return "errors/noAccess";
    }

    @RequestMapping(value = "/insufficientFundsError", method = RequestMethod.GET)
    public String insufficientFundsErrorPage() {
        return "errors/insufficientFundsError";
    }

}
