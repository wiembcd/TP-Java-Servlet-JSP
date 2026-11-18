
package ex1;

import java.util.concurrent.atomic.AtomicInteger;

import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class ActiveSessionListener implements HttpSessionListener {

    private static final AtomicInteger counter = new AtomicInteger(0);

    @Override
    public void sessionCreated(HttpSessionEvent event) {

        int count = counter.incrementAndGet();

        ServletContext context =
                event.getSession().getServletContext();

        context.setAttribute("activeSessions", count);

        System.out.println("Session created: " + count);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {

        int count = counter.decrementAndGet();

        ServletContext context =
                event.getSession().getServletContext();

        context.setAttribute("activeSessions", count);

        System.out.println("Session destroyed: " + count);
    }
}
