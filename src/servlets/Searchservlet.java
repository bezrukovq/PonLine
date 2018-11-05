package servlets;

import entities.User;
import helper.Helper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class Searchservlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String q = request.getParameter("q");
        ArrayList<String> users = Helper.getUserService().getLikeUsers(q);
        JSONArray ja = new JSONArray();

        for(String b: users) {
            JSONObject name = new JSONObject();
            try {
                name.put("nam",b);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            ja.put(name);
        }
        JSONObject jo = new JSONObject();
        try {
            jo.put("res", ja);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        response.setContentType("text/json");
        response.getWriter().println(jo.toString());

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
