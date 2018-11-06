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

public class NewsList extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filter = (String) request.getParameter("filter");
        String s =(String) request.getParameter("search");
        HttpSession session = request.getSession();
        Helper.logged(request, response);
        String user = (String) session.getAttribute("login");
        //filter-> value in ftl
        //null politics nature celebrities
        Configuration cfg = Helper.getConfig(getServletContext());
        Template tmpl = cfg.getTemplate("all_topics.ftl");
        HashMap<String, Object> root = new HashMap<>();
        ArrayList<News> news = Helper.getNewsService().getNewsForListWithFilter(filter,s);
        root.put("form_url", request.getRequestURI());
        root.put("all", true);
        root.put("logged", user!=null);
        root.put("login", user!=null?user:" ");
        root.put("news", news);
        try {
            tmpl.process(root, response.getWriter());
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        String user = (String) session.getAttribute("login");
        //if (!Helper.logged(request,session,response)) {
        Configuration cfg = Helper.getConfig(getServletContext());
        Template tmpl = cfg.getTemplate("all_topics.ftl");
        HashMap<String, Object> root = new HashMap<>();
        ArrayList<News> news = Helper.getNewsService().getNewsForList();
        root.put("form_url", request.getRequestURI());
        root.put("logged", user!=null);
        root.put("login", user!=null?user:" ");
        root.put("all", true);
        root.put("news", news);
        try {
            tmpl.process(root, response.getWriter());
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        //  }

    }
}
