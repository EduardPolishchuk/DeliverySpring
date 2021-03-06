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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.training.delivery.entity.Role;
import ua.training.delivery.entity.User;
import ua.training.delivery.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static ua.training.delivery.constants.Constants.*;

@Controller
@RequestMapping(value = "/signUp")
public class SignController {
    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    final static Logger logger = LogManager.getLogger();

    @Autowired
    public SignController(UserService userService,
                          AuthenticationManager authenticationManager,
                          PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String getSignPage(Model model) {
        model.addAttribute(USER_FORM, new User());
        return "signUp";
    }

    @PostMapping()
    public String signUp(HttpServletRequest request, HttpSession session,
                         @ModelAttribute(USER_FORM) @Valid User user,
                         BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            bindingResult.getFieldErrors().stream()
                    .filter(er -> !er.getObjectName().equals(PASSWORD))
                    .forEach(er -> sb.append(er.getRejectedValue()).append(" "));
            model.addAttribute(ERROR, sb.toString());
            return "signUp";
        }
        user.setRole(Role.USER);
        String password = user.getPassword();
        String passHash = passwordEncoder.encode(user.getPassword());
        user.setPassword(passHash);

        if (!userService.create(user)) {
            model.addAttribute("error_user", true);
            user.setPassword("");
            return "signUp";
        }
        authWithAuthManager(request, user.getLogin(), password);
        user.setPassword("");
        session.setAttribute(USER_PROFILE, user);
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
