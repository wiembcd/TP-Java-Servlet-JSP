
package ex1;

import jakarta.servlet.ServletContextAttributeEvent;
import jakarta.servlet.ServletContextAttributeListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ApplicationAttributeListener
        implements ServletContextAttributeListener {

    @Override
    public void attributeAdded(ServletContextAttributeEvent event) {
        System.out.println("Application attribute added: "
                + event.getName());
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent event) {
        System.out.println("Application attribute replaced: "
                + event.getName());
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent event) {
        System.out.println("Application attribute removed: "
                + event.getName());
    }
}
