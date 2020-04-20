package servlets;

import model.Model;
import roles.User;
import services.Service;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/editing")
public class EditorServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Model model = Model.getInstance();
        String session = request.getSession().getId();
        User user = model.getUserBySessionId(session);
        if (user == null){
            ServletContext context = getServletContext();
            RequestDispatcher dispatcher = context.getRequestDispatcher("/auth.jsp");
            dispatcher.forward(request, response);
            return;
        }
        request.setAttribute("user", user);
        ServletContext context = getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher("/editing.jsp");
        dispatcher.forward(request, response);
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Model model = Model.getInstance();
        Service service = null;
        try {
            service = Service.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String pass_1 = request.getParameter("pass1");
        String pass_2 = request.getParameter("pass2");
        String email = request.getParameter("email");
        String info = request.getParameter("info");

        if ((pass_1 == null && pass_2 != null) || ((pass_1 != null && pass_2 == null)) || (!pass_1.equals(pass_2))){
            response.setContentType("text/html");
            response.getWriter().println("<h2> Passwords not match </h2>");
            return;
        }
        User user = model.getUserBySessionId(request.getSession().getId());
        User userChange = new User();
        userChange.setLogin(user.getLogin());

        if (pass_1 == null || pass_1.isEmpty())
            userChange.setPassword(user.getPassword());
        else
            userChange.setPassword(pass_1);
        if (email == null || email.isEmpty())
            userChange.setEmail(user.getEmail());
        else
            userChange.setEmail(email);
        if (info == null || info.isEmpty())
            userChange.setInfo(user.getInfo());
        else
            userChange.setInfo(info);
        try {
            service.updateUser(userChange);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        model.deleteSession(request.getSession().getId());
        model.addSession(request.getSession().getId(), userChange);
        request.setAttribute("user", userChange);
        ServletContext context = getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher("/profile.jsp");
        dispatcher.forward(request, response);
    }
}
