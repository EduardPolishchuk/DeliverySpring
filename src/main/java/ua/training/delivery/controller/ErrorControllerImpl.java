package ua.training.delivery.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorControllerImpl implements ErrorController {

    private static final Logger logger = LoggerFactory.getLogger(ErrorControllerImpl.class);

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String errorPage(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            logger.error("Error: error code " + statusCode);
        }

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
