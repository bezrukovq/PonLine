package servlets;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import helper.Helper;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import java.io.*;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

@MultipartConfig
public class Registration extends HttpServlet {
    UserService userService = Helper.getUserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String user = (String) session.getAttribute("login");
        String name = request.getParameter("name");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String desc = request.getParameter("description");
        if (user != null) {
            response.sendRedirect("/profile"); //как он вообще сюда попал
        } else {
            Pattern pattern = Pattern.compile("^(?=.*[A-Za-z0-9]$)[A-Za-z][A-Za-z\\d.-]{1,19}$");
            Matcher matcher = pattern.matcher(login);
            Pattern p2 = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{6,}$");
            Matcher m2 = p2.matcher(password);
            if (matcher.matches() && m2.matches()) {
                if (!userService.isThere(login)) {

                    String fileName =Helper.loadPic(request, response);
                    userService.registerNewUser(name, login, password, desc, "../UserPics/" + fileName);
                    response.sendRedirect("/login");
                } else {
                    userService.registerNewUser(name, login, password, desc, null);
                    response.sendRedirect("/login");
                }
            } else {
                response.sendRedirect("/registration?msg=loginerror");

            }
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        String user = (String) session.getAttribute("login");
        if (user != null) {
            response.sendRedirect("/main"); //зачем авторизованному логиниться
        } else {
            if (!Helper.logged(request, response)) {
                Configuration cfg = Helper.getConfig(getServletContext());
                Template tmpl = cfg.getTemplate("registration.html");
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
