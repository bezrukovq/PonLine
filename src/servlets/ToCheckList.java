package servlets;

import entities.News;
import entities.User;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import helper.Helper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class ToCheckList extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        Helper.logged(request, response);
        String user = (String) session.getAttribute("login");
        User cuser = (User) session.getAttribute("userClass");
        if (user == null) {
            response.sendRedirect("/news");
        } else {
            if (cuser == null) {
                response.sendRedirect("/profile?login=" + user);
            } else {
                if (cuser.isAdmin()) {
                    Configuration cfg = Helper.getConfig(getServletContext());
                    Template tmpl = cfg.getTemplate("all_topics.ftl");
                    HashMap<String, Object> root = new HashMap<>();
                    ArrayList<News> news = Helper.getNewsService().getNewsForAdminList();
                    root.put("form_url", request.getRequestURI());
                    root.put("all", false);
                    root.put("logged", user!=null);
                    root.put("login", user!=null?user:" ");
                    root.put("news", news);
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
}
