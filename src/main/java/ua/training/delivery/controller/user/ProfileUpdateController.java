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

@Controller
@RequestMapping("/user/profile")
public class ProfileUpdateController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public String profilePage(HttpSession session, Model model) {
        User user = (User) session.getAttribute("userProfile");
        model.addAttribute("userForm", user);
        return "user/userprofile";
    }

    @PostMapping("/update")
    public String updateProfile(HttpSession session,
                                @ModelAttribute User userForm, BindingResult bindingResult) {
        userForm.setPassword(passwordEncoder.encode(userForm.getPassword()));
        userService.update(userForm);
        userForm.setPassword("");
        session.setAttribute("userProfile", userForm);
        return "redirect:/user/profile/";
    }
}
