package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registration extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String user = (String) session.getAttribute("name");
        String name = request.getParameter("name");
        if (user != null) {
            response.sendRedirect("/profile"); //как он вообще сюда попал
        } else {
            Pattern pattern = Pattern.compile("^(?=.*[A-Za-z0-9]$)[A-Za-z][A-Za-z\\d.-]{0,19}$");
                /*
    (?=.*[A-Za-z0-9]$) Asserts that the match must ends with a letter or digit.
    [A-Za-z] Must starts with a letter.
    [A-Za-z\d.-]{0,19} matches the chars according to the pattern present inside the char class. And the number of matched chars must be from 0 to 19.
                 */
            Matcher matcher = pattern.matcher(name);
            if (matcher.matches()) {
                session.setAttribute("name", name);
                Cookie cookie = new Cookie("name",name);
                cookie.setMaxAge(60*10);
                response.addCookie(cookie);
                response.sendRedirect("/profile?user"); //переходим в свой профиль
            } else {
                response.sendRedirect("/login");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
