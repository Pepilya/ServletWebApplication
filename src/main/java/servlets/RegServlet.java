package servlets;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.Service;

import roles.User;

public class RegServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher("/reg.jsp");
        dispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Service service = null;
        try {
            service = Service.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String login = request.getParameter("login");
        String password = request.getParameter("pass");
        String email = request.getParameter("email");
        String info = request.getParameter("info");
        if (login == null || password == null || email == null || login.isEmpty() || password.isEmpty() || email.isEmpty()) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("It is not correct input, try again please.");
            return;
        }
        if (service.getByLogin(login) != null) {
            response.setContentType("text/html;charset=utf-8");
            try {
                response.getWriter().println("<h2> Login " + login + " has already registered </h2>");
            } finally {
                response.getWriter().close();
                return;
            }
        }
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setEmail(email);
        user.setInfo(info);
        try {
            service.addUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ServletContext context = getServletContext();
        RequestDispatcher desp = context.getRequestDispatcher("/index.jsp");
        desp.forward(request, response);
    }
}

