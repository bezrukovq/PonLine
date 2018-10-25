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
import java.util.ArrayList;
import java.util.HashMap;

public class NewsPost extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        String user = (String) session.getAttribute("login");
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            //if (!Helper.logged(request,session,response)) {
            Configuration cfg = Helper.getConfig(getServletContext());
            Template tmpl = cfg.getTemplate("topic.ftl");
            HashMap<String, Object> root = new HashMap<>();
            News news = Helper.getNewsService().getNewsByID(id);
            root.put("form_url", request.getRequestURI());
            root.put("news", news);
            try {
                tmpl.process(root, response.getWriter());
            } catch (TemplateException e) {
                e.printStackTrace();
            }
        } catch (java.lang.NumberFormatException e) {
            response.sendRedirect("/news");
        }
        //  }
    }
}
