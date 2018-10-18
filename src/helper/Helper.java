package helper;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import services.UserService;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Helper {
    private static Configuration cfg = null;
    private static Connection connection = null;

    public static UserService getUserService() {
        if(userService == null){
            userService= new UserService();
        }
        return userService;
    }

    public static boolean logged(HttpServletRequest request, HttpSession session, HttpServletResponse response){
        String login = "";
        boolean hasCookies = false;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("login")) {
                    login = cookie.getValue();
                    hasCookies= true;
                    session.setAttribute("login", login);
                    try {
                        response.sendRedirect("/main");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return hasCookies;
    }

    private static UserService userService = null;
    public static Connection getConnection() {
        if(connection==null){
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/PonLine", "postgres", "postgres");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static Configuration getConfig(ServletContext sc) {
        if (cfg == null){
            cfg = new Configuration();
            cfg.setServletContextForTemplateLoading(
                    sc,
                    "/WEB-INF/templates"
            );
            cfg.setTemplateExceptionHandler(
                    TemplateExceptionHandler.HTML_DEBUG_HANDLER
            );
        }
        return cfg;
    }
}
