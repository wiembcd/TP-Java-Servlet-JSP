
package ex1;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

public class LoggingFilter implements Filter {

    private String logPrefix;

    @Override
    public void init(FilterConfig config) {
        logPrefix = config.getInitParameter("logPrefix");

        if (logPrefix == null) {
            logPrefix = "LOG";
        }

        System.out.println(logPrefix + " - Filter initialized");
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        long start = System.nanoTime();

        System.out.println(logPrefix + " - Before: "
                + req.getRequestURL());

        chain.doFilter(request, response);

        System.out.println(logPrefix + " - After: "
                + req.getRequestURL());

        long end = System.nanoTime();

        double duration = (end - start) / 1_000_000.0;

        System.out.println(logPrefix + " - Execution time: "
                + duration + " ms");
    }

    @Override
    public void destroy() {
        System.out.println(logPrefix + " - Filter destroyed");
    }
}
