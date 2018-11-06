package DAO.impl;

import DAO.NewsDAO;
import entities.Comment;
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
                    "select u.picpath,n.id, u.login, n.header,n.category, n.date from news as n inner join users u on n.author_id = u.id where n.accepted = true order by n.id limit 5");
            ResultSet rs =st.executeQuery();
            while (rs.next()){
                newsList.add(new News(rs.getInt("id"),rs.getString("login"),rs.getString("header"),rs.getString("date"),getCat(rs.getInt("category")),rs.getString("picpath")));
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
            PreparedStatement st = conn.prepareStatement("select u.picpath,n.accepted,n.category,u.login,u.description,u.name,n.header,n.text,n.date,t.nam,t.link from news as n inner join users as u on n.author_id = u.id inner join topic as t on n.topic_id = t.id where n.id=?");
            st.setInt(1,i);
            ResultSet rs = st.executeQuery();
            rs.next();
            return new News(i,rs.getBoolean("accepted"),rs.getString("login"),rs.getString("name"),rs.getString("description"),new Topic(rs.getString("nam"),rs.getString("link")),rs.getString("header"),rs.getString("text"),rs.getString("date"),rs.getString("picpath"),getCat(rs.getInt("category")));
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
                    "select u.picpath,n.id, u.login, n.header,n.category, n.date from news as n inner join users u on n.author_id = u.id where n.accepted = false order by n.id limit 20");
            ResultSet rs =st.executeQuery();
            while (rs.next()){
                newsList.add(new News(rs.getInt("id"),rs.getString("login"),rs.getString("header"),rs.getString("date"),getCat(rs.getInt("category")),rs.getString("picpath")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return newsList;
    }

    @Override
    public ArrayList<News> getNewsforUserList(String userToShow) {
        ArrayList<News> newsList = new ArrayList<>();
        try {
            PreparedStatement st = conn.prepareStatement(
                    "select n.id, u.picpath,u.login, n.header,n.category, n.date from news as n inner join users u on n.author_id = u.id where n.accepted = true and u.login=? order by n.id limit 5");
            st.setString(1,userToShow);
            ResultSet rs =st.executeQuery();
            while (rs.next()){
                newsList.add(new News(rs.getInt("id"),rs.getString("login"),rs.getString("header"),rs.getString("date"),getCat(rs.getInt("category")),rs.getString("picpath")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return newsList;
    }

    @Override
    public void addPost(News news) {
        try {
            PreparedStatement st = conn.prepareStatement("insert into news(author_id, topic_id, header, text, accepted, date) VALUES ((select id from users where login = ?),1,?,?,false ,?)");
            st.setString(1,news.getCrLogin());
            st.setString(2,news.getHeader());
            st.setString(3,news.getText());
            st.setString(4,news.getDate());
            st.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteNews(int id) {
        try {
            PreparedStatement st = conn.prepareStatement("delete from news where id=?");
            st.setInt(1,id);
            st.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void acceptNews(int id) {
        try {
            PreparedStatement st = conn.prepareStatement("update news set accepted=true where id=?");
            st.setInt(1,id);
            st.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void comment(Comment comment) {
        try {
            PreparedStatement st = conn.prepareStatement("insert into comment(sender_id, date, text, news_id) VALUES ((select id from users where login=?),?,?,?)");
            st.setString(1,comment.getCrLogin());
            st.setString(2,comment.getDate());
            st.setString(3,comment.getText());
            st.setInt(4,comment.getNews_id());
            st.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<News> getNewsforListWithFilter(String f,String s) {
        int filter = Integer.parseInt(f);
        ArrayList<News> newsList = new ArrayList<>();
        PreparedStatement st;
        try {
            if(filter!=0) {
                st = conn.prepareStatement(
                        "select u.picpath,n.id, u.login, n.header,n.category, n.date from news as n inner join users u on n.author_id = u.id where n.accepted = true and (n.header like ?) and n.category=?  order by n.id limit 5");
                st.setInt(2, filter);
            } else {
                st = conn.prepareStatement(
                        "select u.picpath,n.id, u.login, n.header,n.category, n.date from news as n inner join users u on n.author_id = u.id where n.accepted = true and n.header like ? order by n.id limit 5");
            }
            st.setString(1, "%" + s + "%");
            ResultSet rs =st.executeQuery();
            while (rs.next()){
                newsList.add(new News(rs.getInt("id"),rs.getString("login"),rs.getString("header"),rs.getString("date"),getCat(rs.getInt("category")),rs.getString("picpath")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return newsList;
    }
    public String getCat(int id){
        switch (id){
            case 1:
                return "No Category";
            case 2:
                return "Politics";
            case 3:
                return "Nature";
            case 4:
                return "Celebrities";
        }
        return "NOT FOUND";
    }
}

