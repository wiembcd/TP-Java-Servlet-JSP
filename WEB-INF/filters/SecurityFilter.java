
package ex1;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SecurityFilter implements Filter {

    @Override
    public void init(FilterConfig config) {
        System.out.println("SecurityFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        System.out.println("SecurityFilter - Before");

        HttpSession session = req.getSession(false);

        if (session != null && session.getAttribute("user") != null) {

            System.out.println("Access authorized");

            chain.doFilter(request, response);

        } else {

            System.out.println("Access refused");

            res.sendRedirect(req.getContextPath() + "/login.html");
        }

        System.out.println("SecurityFilter - After");
    }

    @Override
    public void destroy() {
        System.out.println("SecurityFilter destroyed");
    }
}
