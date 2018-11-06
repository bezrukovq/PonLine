package servlets;

import entities.User;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import helper.Helper;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

public class Profile extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        Helper.logged(request, response);
        String user = (String) session.getAttribute("login");
        if (user == null) {
            response.sendRedirect("/news");
        } else {
            String userToShow =(String) request.getParameter("login");
            Configuration cfg = Helper.getConfig(getServletContext());
            Template tmpl = cfg.getTemplate("profile.ftl");
            HashMap<String, Object> root = new HashMap<>();
            root.put("form_url", request.getRequestURI());
            root.put("logged", user!=null);
            root.put("login", user!=null?user:" ");
            root.put("thisUser", userToShow.equals(user));
            User DBuser = Helper.getUserService().getUser(userToShow);
            if(DBuser!=null) {
                if(userToShow.equals(user))
                session.setAttribute("userClass",DBuser);
                root.put("user", DBuser);
                try {
                    tmpl.process(root, response.getWriter());
                } catch (TemplateException e) {
                    e.printStackTrace();
                }
            } else {
                response.sendRedirect("/news");
            }

        }
    }
}
