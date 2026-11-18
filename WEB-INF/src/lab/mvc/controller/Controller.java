
package lab.mvc.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lab.mvc.model.Person;

@WebServlet("/Controller")
public class Controller extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        // 1. Récupérer les données du formulaire
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String birthDay = request.getParameter("birthDay");
        String sex = request.getParameter("sex");

        // 2. Créer un objet Person
        Person person = new Person(
            firstName,
            lastName,
            birthDay,
            sex
        );

        // 3. Ajouter le bean à la requête
        request.setAttribute("person", person);

        // 4. Choisir la JSP selon l'année de naissance
        String page;

        try {
            int year = Integer.parseInt(
                birthDay.substring(0, 4)
            );

            if (year < 1990) {
                page = "/old.jsp";
            } else {
                page = "/young.jsp";
            }

        } catch (Exception e) {
            response.sendError(
                HttpServletResponse.SC_BAD_REQUEST,
                "Date de naissance invalide"
            );
            return;
        }

        // 5. Transférer la requête vers la JSP
        RequestDispatcher dispatcher =
            getServletContext().getRequestDispatcher(page);

        dispatcher.forward(request, response);
    }
}
