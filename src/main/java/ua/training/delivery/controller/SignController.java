package ua.training.delivery.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.training.delivery.entity.Role;
import ua.training.delivery.entity.User;
import ua.training.delivery.service.UserService;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;



@Controller
@RequestMapping(value = "/signUp")
public class SignController {
    @Autowired
    UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    final static Logger logger = LogManager.getLogger();

    @GetMapping
    public String getSignPage(HttpSession session, Model model) {
        return "signUp";
    }

    @PostMapping(value = "/up")
    public String signUp(HttpServletRequest request,   User user, Model model) {
        user.setRole(Role.USER);

        String password = user.getPassword();
        String passHash = passwordEncoder.encode(user.getPassword());
        user.setPassword(passHash);

        if (!userService.create(user)) {
            model.addAttribute("error_user", true);
            return "redirect:/sign";
        }
        authWithAuthManager(request, user.getLogin(), password);
        logger.info("create user with id = " + user.getId());

        return "redirect:/user/";
    }

    public void authWithAuthManager(HttpServletRequest request, String username, String password) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
        authToken.setDetails(new WebAuthenticationDetails(request));

        Authentication authentication = authenticationManager.authenticate(authToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}