package ua.training.delivery.controller.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.training.delivery.entity.User;
import ua.training.delivery.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static ua.training.delivery.constants.Constants.*;

@Controller
@RequestMapping("/user/profile")
public class ProfileUpdateController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ProfileUpdateController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String profilePage(HttpSession session, Model model) {
        User user = (User) session.getAttribute(USER_PROFILE);
        model.addAttribute(USER_FORM, user);
        return "user/userProfile";
    }

    @PostMapping("/update")
    public String updateProfile(HttpSession session, Model model,
                                @ModelAttribute(USER_FORM) @Valid User userForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            bindingResult.getFieldErrors().stream()
                    .filter(er -> !er.getObjectName().equals(PASSWORD))
                    .forEach(er -> sb.append(er.getRejectedValue()).append(" "));
            model.addAttribute(ERROR, sb.toString());
            return "user/userProfile";
        }
        userForm.setPassword(passwordEncoder.encode(userForm.getPassword()));
        userService.update(userForm);
        userForm.setPassword("");
        session.setAttribute(USER_PROFILE, userForm);
        return "redirect:/user/profile/";
    }
}
