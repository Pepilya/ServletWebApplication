package servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

import services.Service;
import DB.Util;
import model.Model;
import roles.User;
import templeter.PageGenerator;

@WebServlet(urlPatterns = {"/auth", "/profile"})
public class AuthServlet extends HttpServlet {

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
        RequestDispatcher dispatcher = context.getRequestDispatcher("/profile.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action != null)
            if (action.equals("delete")){
            Model model = Model.getInstance();
            String session = request.getSession().getId();
            model.deleteSession(session);
            this.doGet(request, response);
            return;
        }
        Service service = null;
        try {
            service = Service.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String login = request.getParameter("login");
        String password = request.getParameter("pass");
        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("It is not correct input, try again please.");
            return;
        }
        User user = service.getByLogin(login);
        if (user == null || !user.getPassword().equals(password)) {
            ServletContext context = getServletContext();
            RequestDispatcher dispatcher = context.getRequestDispatcher("/ErrorAuth.html");
            dispatcher.forward(request, response);
            return;
        }
        Model model = Model.getInstance();
        model.addSession(request.getSession().getId(), user);
        HashMap<String, String> profileData = new HashMap<>();
        profileData.put("login", user.getLogin());
        profileData.put("password", user.getPassword());
        profileData.put("email", user.getEmail());
        profileData.put("info", user.getInfo());
        request.setAttribute("user", user);
        ServletContext context = getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher("/profile.jsp");
        dispatcher.forward(request, response);
    }
    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

}

