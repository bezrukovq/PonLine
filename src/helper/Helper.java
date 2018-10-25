package helper;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import services.NewsService;
import services.UserService;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Helper {
    private static Configuration cfg = null;
    private static Connection connection = null;
    private static UserService userService = null;
    private static NewsService newsService = null;

    public static UserService getUserService() {
        if(userService == null){
            userService= new UserService();
        }
        return userService;
    }
    public static NewsService getNewsService() {
        if(newsService == null){
            newsService= new NewsService();
        }
        return newsService;
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
                }
            }
        }
        return hasCookies;
    }

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
    public static String md5Custom(String st) {
        MessageDigest messageDigest = null;
        byte[] digest = new byte[0];

        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(st.getBytes());
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            // тут можно обработать ошибку
            // возникает она если в передаваемый алгоритм в getInstance(,,,) не существует
            e.printStackTrace();
        }

        BigInteger bigInt = new BigInteger(1, digest);
        String md5Hex = bigInt.toString(16);

        while( md5Hex.length() < 32 ){
            md5Hex = "0" + md5Hex;
        }

        return md5Hex;
    }
}
