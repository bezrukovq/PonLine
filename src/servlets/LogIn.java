package servlets;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import helper.Helper;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogIn extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String user = (String) session.getAttribute("name");
        String login = request.getParameter("login");
        String passw = request.getParameter("password");
        String remember = request.getParameter("remember");
        if (user != null) {
            response.sendRedirect("/profile"); //как он вообще сюда попал
        } else {
            Pattern pattern = Pattern.compile("^(?=.*[A-Za-z0-9]$)[A-Za-z][A-Za-z\\d.-]{0,19}$");
            Matcher matcher = pattern.matcher(login);
            Pattern p2 = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{6,}$");
            Matcher m2 = pattern.matcher(passw);
            if (matcher.matches()&& m2.matches() ) {
                if(Helper.getUserService().exist(login, passw)) {
                    session.setAttribute("login", login);
                    if(remember!=null) {
                        Cookie cookie = new Cookie("login", login);
                        cookie.setMaxAge(60);
                        response.addCookie(cookie);
                    }
                    response.sendRedirect("/news");

                } else {
                    response.sendRedirect("/login");
                }
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        String user = (String) session.getAttribute("login");
        if (user != null) {
            response.sendRedirect("/news"); //зачем авторизованному логиниться
        } else {
            if (!Helper.logged(request,session,response)) {
                Configuration cfg = Helper.getConfig(getServletContext());
                Template tmpl = cfg.getTemplate("LogIn.html");
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
