package servlets;

import entities.News;
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
import java.util.Date;
import java.util.HashMap;

public class NewPost extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String text = request.getParameter("text");
        String user = (String) request.getSession().getAttribute("login");
        Helper.getNewsService().addPost(new News(title,text,user,new Date().toString()));
        response.sendRedirect("/news");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        String user = (String) session.getAttribute("login");
        if(user==null) {
            Helper.logged(request, response);
            user = (String) session.getAttribute("login");
        }
        if (user == null) {
            response.sendRedirect("/news");
        } else {
            Configuration cfg = Helper.getConfig(getServletContext());
            Template tmpl = cfg.getTemplate("new_topic.html");
            HashMap<String, Object> root = new HashMap<>();
            root.put("form_url", request.getRequestURI());
            try {
                tmpl.process(root, response.getWriter());
            } catch (TemplateException e) {
                e.printStackTrace();
            }
        }
    }
}
