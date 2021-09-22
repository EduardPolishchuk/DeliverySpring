package ua.training.delivery.filter;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
@Order(1)
public class LocaleFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest req = ((HttpServletRequest) servletRequest);
        HttpSession session = req.getSession();
        String locale = req.getParameter("language");
        if (locale == null) {
            if (session.getAttribute("locale") == null) {
                session.setAttribute("locale", "uk");
            }
        } else {
            session.setAttribute("locale", locale);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
