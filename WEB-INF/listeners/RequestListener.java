
package ex1;

import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class RequestListener implements ServletRequestListener {

    @Override
    public void requestInitialized(ServletRequestEvent event) {
        System.out.println("Request initialized");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent event) {
        System.out.println("Request destroyed");
    }
}
