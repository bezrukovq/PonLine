package helper;

import entities.Comment;
import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import services.NewsService;
import services.UserService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

public class Helper {
    private static Configuration cfg = null;
    private static Connection connection = null;
    private static UserService userService = null;
    private static NewsService newsService = null;

    public static UserService getUserService() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    public static NewsService getNewsService() {
        if (newsService == null) {
            newsService = new NewsService();
        }
        return newsService;
    }

    public static boolean logged(HttpServletRequest request, HttpServletResponse response) {
        String login = "";
        boolean hasCookies = false;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("login")) {
                    login = cookie.getValue();
                    hasCookies = true;
                    request.getSession().setAttribute("login", login);
                }
            }
        }
        return hasCookies;
    }

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/PonLine",
                        "postgres",
                        "postgres");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static Configuration getConfig(ServletContext sc) {
        if (cfg == null) {
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

        while (md5Hex.length() < 32) {
            md5Hex = "0" + md5Hex;
        }

        return md5Hex;
    }

    public static String loadPic(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //CHANGE PATH
        final String path = "C:\\Univer\\Infa\\projectAbramskiy\\PonLine\\web\\UserPics";
        final Part filePart = request.getPart("file");
        String fileName="none";
        if (filePart.getSize() != -1) {
            fileName = System.currentTimeMillis() + getFileName(filePart);

            OutputStream out = null;
            InputStream filecontent = null;
            final PrintWriter writer = response.getWriter();

            try {
                out = new FileOutputStream(new File(path + File.separator
                        + fileName));
                filecontent = filePart.getInputStream();

                int read = 0;
                final byte[] bytes = new byte[512];

                while ((read = filecontent.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
            } catch (FileNotFoundException fne) {
            } finally {
                if (out != null) {
                    out.close();
                }
                if (filecontent != null) {
                    filecontent.close();
                }
                if (writer != null) {
                    writer.close();
                }
            }
        }
        return fileName;
    }

    private static String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
        LOGGER.log(Level.INFO, "Part Header = {0}", partHeader);
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}
