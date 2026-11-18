
package ex1;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ReadCookie")
public class ReadCookie extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println("<h2>Informations des cookies</h2>");

        Cookie[] cookies = request.getCookies();

        if (cookies == null || cookies.length == 0) {
            out.println("<p>Aucun cookie trouvé !</p>");
        } else {
            for (Cookie cookie : cookies) {
                out.println("<p><b>" + cookie.getName()
                        + " :</b> " + cookie.getValue() + "</p>");
            }
        }

        out.println("<a href='index.html'>Retour</a>");
        out.println("</body></html>");
    }
}
