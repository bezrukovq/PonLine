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
import java.sql.Time;
import java.sql.Timestamp;
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
            if (matcher.matches()&& m2.matches() ){
                if (!userService.isThere(login)) {
                    //downloading
                    final String path = "C:\\Univer\\Infa\\projectAbramskiy\\PonLine\\web\\UserPics";
                    final Part filePart = request.getPart("file");
                    String fileName;
                    if(filePart!=null) {
                        fileName = System.currentTimeMillis()+ getFileName(filePart);

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
                            writer.println("New file " + fileName + " created at " + path);
                            LOGGER.log(Level.INFO, "File{0}being uploaded to {1}",
                                    new Object[]{fileName, path});
                        } catch (FileNotFoundException fne) {
                            writer.println("You either did not specify a file to upload or are "
                                    + "trying to upload a file to a protected or nonexistent "
                                    + "location.");
                            writer.println("<br/> ERROR: " + fne.getMessage());

                            LOGGER.log(Level.SEVERE, "Problems during file upload. Error: {0}",
                                    new Object[]{fne.getMessage()});
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
                        userService.registerNewUser(name, login, password, desc, "../UserPics/"+fileName);
                    }
                    response.sendRedirect("/login");

                } else {
                    response.sendRedirect("/registration?msg=loginerror");
                }
            } else {
                response.sendRedirect("/registration");
            }
        }
            }

    private String getFileName(final Part part) {
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        String user = (String) session.getAttribute("login");
        if (user != null) {
            response.sendRedirect("/main"); //зачем авторизованному логиниться
        } else {
            if (!Helper.logged(request,response)) {
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
