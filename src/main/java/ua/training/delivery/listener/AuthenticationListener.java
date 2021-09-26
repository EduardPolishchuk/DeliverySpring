package ua.training.delivery.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ua.training.delivery.entity.User;
import ua.training.delivery.service.UserService;

import javax.servlet.http.HttpSession;


@Component
public class AuthenticationListener implements ApplicationListener<InteractiveAuthenticationSuccessEvent> {

    private final HttpSession httpSession;

    private final UserService userService;

    @Autowired
    public AuthenticationListener(HttpSession httpSession, UserService userService) {
        this.httpSession = httpSession;
        this.userService = userService;
    }

    @Override
    public void onApplicationEvent(InteractiveAuthenticationSuccessEvent interactiveAuthenticationSuccessEvent) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            User userProfile = userService.findByLogin(((UserDetails) principal).getUsername()).get();
            userProfile.setPassword("");
            httpSession.setAttribute("userProfile", userProfile);
        }
    }
}
