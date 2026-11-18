
package ex1;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String username = request.getParameter("username");

        if (username == null || username.trim().isEmpty()) {
            response.sendRedirect("login.html");
            return;
        }

        username = username.trim();

        HttpSession session = request.getSession();

        session.setAttribute("user", username);

        // Extra : stocker un objet SessionUser
        SessionUser sessionUser = new SessionUser(username);

        session.setAttribute("sessionUser", sessionUser);

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html><head><meta charset='UTF-8'>");
        out.println("<title>Login</title></head><body>");

        out.println("<h2>Welcome " + escape(username) + "!</h2>");
        out.println("<p>Login successful.</p>");

        out.println("<p><a href='items.html'>Manage my items</a></p>");

        out.println("<p><a href='RemoveSessionUser'>Remove SessionUser object</a></p>");

        out.println("<p><a href='index.html'>Home</a></p>");

        out.println("</body></html>");
    }

    private String escape(String value) {
        return value.replace("&", "&amp;")
                    .replace("<", "&lt;")
                    .replace(">", "&gt;")
                    .replace("\"", "&quot;")
                    .replace("'", "&#39;");
    }
}
