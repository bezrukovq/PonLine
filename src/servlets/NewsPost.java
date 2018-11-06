package servlets;

import entities.Comment;
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
import java.util.Date;
import java.util.HashMap;

public class NewsPost extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String status = request.getParameter("two");
        int id = Integer.parseInt(request.getParameter("id"));
        if (status != null) {
            if (status.equals("Deny")) {
                Helper.getNewsService().deleteNews(id);
            } else {
                Helper.getNewsService().acceptNews(id);
            }
            response.sendRedirect("/tcheck");
        } else {
            String user = (String) session.getAttribute("login");
            String comment = (String)request.getParameter("comment");
            Helper.getNewsService().comment(new Comment(user,new Date().toString(),comment,id));
            response.sendRedirect("/post?id="+id);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        Helper.logged(request,response);
        String user = (String) session.getAttribute("login");
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            News news = Helper.getNewsService().getNewsByID(id);
            if (news.isAccepted()) {
                //if (!Helper.logged(request,session,response)) {
                Configuration cfg = Helper.getConfig(getServletContext());
                Template tmpl = cfg.getTemplate("topic.ftl");
                HashMap<String, Object> root = new HashMap<>();
                ArrayList<Comment> comments = Helper.getUserService().getComments(id);
                root.put("form_url", request.getRequestURI());
                root.put("news", news);
                root.put("logged", user!=null);
                root.put("login", user!=null?user:" ");
                root.put("comments",comments);
                try {
                    tmpl.process(root, response.getWriter());
                } catch (TemplateException e) {
                    e.printStackTrace();
                }
            } else {
                Helper.logged(request, response);
                User cuser = (User) session.getAttribute("userClass");
                if (user == null) {
                    response.sendRedirect("/news");
                } else {
                    if (cuser == null) {
                        response.sendRedirect("/profile?login=" + user);
                    } else {
                        if (cuser.isAdmin()) {
                            Configuration cfg = Helper.getConfig(getServletContext());
                            Template tmpl = cfg.getTemplate("new_topic_admin.ftl");
                            HashMap<String, Object> root = new HashMap<>();
                            root.put("form_url", request.getRequestURI());
                            root.put("logged", user!=null);
                            root.put("login", user!=null?user:" ");
                            root.put("news", news);
                            try {
                                tmpl.process(root, response.getWriter());
                            } catch (TemplateException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        } catch (java.lang.NumberFormatException e) {
            response.sendRedirect("/news");
        }
    }
}
