package DAO.impl;

import DAO.NewsDAO;
import entities.News;
import helper.Helper;

import java.sql.*;
import java.util.ArrayList;

public class SimpleNewsDAO implements NewsDAO {
    ArrayList<News> newsList = new ArrayList<>();
    @Override
    public ArrayList<News> getNewsforList() {
        Connection conn = Helper.getConnection();
        try {
            PreparedStatement st = conn.prepareStatement(
                    "select n.id, u.login, n.header, n.date from news as n inner join users u on n.author_id = u.id");
            ResultSet rs =st.executeQuery();
            while (rs.next()){
                newsList.add(new News(rs.getInt("id"),rs.getString("login"),rs.getString("header"),rs.getString("date")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return newsList;
    }
}
