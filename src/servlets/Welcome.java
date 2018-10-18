package servlets;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import helper.Helper;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

public class Welcome extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request,
                         javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        String user = (String) session.getAttribute("login");
        if (user != null) {
            response.sendRedirect("/main"); //зачем авторизованному логиниться
        } else {
            if (!Helper.logged(request,session,response)) {
                response.setContentType("text/html");
                Configuration cfg = Helper.getConfig(getServletContext());
                Template tmpl = cfg.getTemplate("welcome.html");
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
}
