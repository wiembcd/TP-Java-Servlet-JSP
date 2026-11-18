
package ex1;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ShowSession")
public class ShowSession extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        // Compteur de visites
        Integer visits = (Integer) session.getAttribute("visits");

        if (visits == null) {
            visits = 1;
        } else {
            visits++;
        }

        session.setAttribute("visits", visits);

        // Nombre de sessions actives
        Integer activeSessions = (Integer)
                getServletContext().getAttribute("activeSessions");

        if (activeSessions == null) {
            activeSessions = 0;
        }

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String fullName = (String) session.getAttribute("fullName");
        String birthDate = (String) session.getAttribute("birthDate");

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head><meta charset='UTF-8'>");
        out.println("<title>HTTP Session</title></head>");
        out.println("<body>");

        out.println("<h2>HTTP Session Information</h2>");

        // Afficher le nombre de sessions actives
        out.println("<p>Active sessions: " + activeSessions + "</p>");

        if (fullName == null || birthDate == null) {

            out.println("<h2>Welcome on my site!</h2>");
            out.println("<p>Please enter your information.</p>");

            out.println("<form action='ShowSession' method='POST'>");

            out.println("Full name: <input type='text' name='fullName' required><br><br>");

            out.println("Date of birth: <input type='date' name='birthDate' required><br><br>");

            out.println("<input type='submit' value='Submit'>");

            out.println("</form>");

        } else {

            out.println("<h2>Welcome back, " + escape(fullName) + "!</h2>");

            out.println("<p>Number of visits: " + visits + "</p>");

            out.println("<p>There are " + daysToBirthday(birthDate)
                    + " days to your birthday.</p>");
        }

        out.println("<br><a href='LogoutServlet'>Logout</a>");

        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String fullName = request.getParameter("fullName");
        String birthDate = request.getParameter("birthDate");

        if (fullName == null || fullName.trim().isEmpty()
                || birthDate == null || birthDate.isEmpty()) {

            response.sendRedirect("ShowSession");
            return;
        }

        try {
            LocalDate.parse(birthDate);

        } catch (Exception e) {

            response.sendRedirect("ShowSession");
            return;
        }

        HttpSession session = request.getSession();

        session.setAttribute("fullName", fullName.trim());
        session.setAttribute("birthDate", birthDate);

        response.sendRedirect("ShowSession");
    }

    private long daysToBirthday(String birthDate) {

        LocalDate today = LocalDate.now();
        LocalDate birth = LocalDate.parse(birthDate);

        LocalDate nextBirthday = birth.withYear(today.getYear());

        if (!nextBirthday.isAfter(today)) {
            nextBirthday = birth.withYear(today.getYear() + 1);
        }

        return ChronoUnit.DAYS.between(today, nextBirthday);
    }

    private String escape(String value) {

        return value.replace("&", "&amp;")
                    .replace("<", "&lt;")
                    .replace(">", "&gt;")
                    .replace("\"", "&quot;")
                    .replace("'", "&#39;");
    }
}
