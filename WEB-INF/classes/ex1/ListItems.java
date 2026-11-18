
package ex1;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ListItems")
public class ListItems extends HttpServlet {

    @Override
    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String newItem = request.getParameter("newItem");

        HttpSession session = request.getSession();

        ArrayList<String> items =
                (ArrayList<String>) session.getAttribute("items");

        HashMap<String, Integer> itemCounts =
                (HashMap<String, Integer>) session.getAttribute("itemCounts");

        if (items == null) {
            items = new ArrayList<>();
            session.setAttribute("items", items);
        }

        if (itemCounts == null) {
            itemCounts = new HashMap<>();
            session.setAttribute("itemCounts", itemCounts);
        }

        if (newItem != null && !newItem.trim().isEmpty()) {
            String item = newItem.trim();

            items.add(item);
            itemCounts.put(item, itemCounts.getOrDefault(item, 0) + 1);
        }

        response.sendRedirect("ListItems");
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        ArrayList<String> items =
                (ArrayList<String>) session.getAttribute("items");

        HashMap<String, Integer> itemCounts =
                (HashMap<String, Integer>) session.getAttribute("itemCounts");

        if (items == null) {
            items = new ArrayList<>();
        }

        if (itemCounts == null) {
            itemCounts = new HashMap<>();
        }

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html lang='fr'>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>My Items</title>");
        out.println("</head>");
        out.println("<body>");

        out.println("<h2>My Items</h2>");

        if (items.isEmpty()) {
            out.println("<p>Aucun élément ajouté.</p>");
        } else {
            out.println("<table border='1' cellpadding='8' cellspacing='0'>");

            out.println("<tr>");
            out.println("<th>Numéro</th>");
            out.println("<th>Nom de l'élément</th>");
            out.println("<th>Nombre de fois ajouté</th>");
            out.println("</tr>");

            for (int i = 0; i < items.size(); i++) {
                String item = items.get(i);

                out.println("<tr>");
                out.println("<td>" + (i + 1) + "</td>");
                out.println("<td>" + escape(item) + "</td>");
                out.println("<td>" + itemCounts.getOrDefault(item, 0) + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");
            out.println("<p>Total des entrées : " + items.size() + "</p>");
        }

        out.println("<p><a href='items.html'>Ajouter un élément</a></p>");
        out.println("<p><a href='index.html'>Retour à l'accueil</a></p>");

        out.println("</body>");
        out.println("</html>");
    }

    private String escape(String value) {
        return value.replace("&", "&amp;")
                    .replace("<", "&lt;")
                    .replace(">", "&gt;")
                    .replace("\"", "&quot;")
                    .replace("'", "&#39;");
    }
}
