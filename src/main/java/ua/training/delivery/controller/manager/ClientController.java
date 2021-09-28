package ua.training.delivery.controller.manager;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.training.delivery.entity.Role;
import ua.training.delivery.service.UserService;
import static ua.training.delivery.constants.Constants.*;

@Controller
@RequestMapping("/manager/client_list")
public class ClientController {

    private final UserService userService;

    @Autowired
    public ClientController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String clientList(Model model) {
        model.addAttribute(CLIENT_LIST, userService.findAllByRole(Role.USER));
        return "manager/managerClientList";
    }
}
