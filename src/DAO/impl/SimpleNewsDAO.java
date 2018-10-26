package DAO.impl;

import DAO.NewsDAO;
import entities.News;
import entities.Topic;
import helper.Helper;

import java.sql.*;
import java.util.ArrayList;

public class SimpleNewsDAO implements NewsDAO {
    Connection conn = Helper.getConnection();

    @Override
    public ArrayList<News> getNewsforList() {
        ArrayList<News> newsList = new ArrayList<>();
        try {
            PreparedStatement st = conn.prepareStatement(
                    "select n.id, u.login, n.header, n.date from news as n inner join users u on n.author_id = u.id where n.accepted = true order by n.id limit 5");
            ResultSet rs =st.executeQuery();
            while (rs.next()){
                newsList.add(new News(rs.getInt("id"),rs.getString("login"),rs.getString("header"),rs.getString("date")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return newsList;
    }

    @Override
    public ArrayList<String> getTags(int id) {
        ArrayList<String> tags = new ArrayList<>();
        try {
            PreparedStatement st = conn.prepareStatement("select tag.t from tags_to_news inner join tag on tags_to_news.tag_id = tag.id where tags_to_news.news_id=?");
            st.setInt(1,id);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                tags.add(rs.getString("t"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tags;
    }

    @Override
    public ArrayList<String> getCategories(int id) {
        ArrayList<String> tags = new ArrayList<>();
        try {
            PreparedStatement st = conn.prepareStatement("select category.c from category_to_news inner join category on category_to_news.category_id = category.id where category_to_news.news_id=?");
            st.setInt(1,id);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                tags.add(rs.getString("c"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tags;
    }

    @Override
    public News getNewsByID(int i) {
        try {
            PreparedStatement st = conn.prepareStatement("select n.accepted,u.login,u.description,u.name,n.header,n.text,n.date,t.nam,t.link from news as n inner join users as u on n.author_id = u.id inner join topic as t on n.topic_id = t.id where n.id=?");
            st.setInt(1,i);
            ResultSet rs = st.executeQuery();
            rs.next();
            return new News(i,rs.getBoolean("accepted"),rs.getString("login"),rs.getString("name"),rs.getString("description"),new Topic(rs.getString("nam"),rs.getString("link")),rs.getString("header"),rs.getString("text"),rs.getString("date"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<News> getNewsforAdminList() {
        ArrayList<News> newsList = new ArrayList<>();
        try {
            PreparedStatement st = conn.prepareStatement(
                    "select n.id, u.login, n.header, n.date from news as n inner join users u on n.author_id = u.id where n.accepted = false order by n.id limit 20");
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
