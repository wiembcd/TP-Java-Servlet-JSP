
package ex1;

import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionBindingListener;

public class SessionUser implements HttpSessionBindingListener {

    private String username;

    public SessionUser(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        System.out.println("SessionUser added: " + username);
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        System.out.println("SessionUser removed: " + username);
    }
}
