package DAO.impl;

import DAO.UserDAO;
import entities.Comment;
import entities.User;
import helper.Helper;

import javax.jws.soap.SOAPBinding;
import java.sql.*;
import java.util.ArrayList;

public class SimpleUserDAO implements UserDAO {
    Connection connection= Helper.getConnection();
    @Override
    public boolean addNewUser(User user) {
        boolean res = false;
        try {
            Statement statement = connection.createStatement();
             res = statement.execute("insert into users(admin, name, login, description, passw,picpath) values('false','"+user.getName()
                    +"','"+user.getLogin()+ "','"
                    +user.getDescription()+"','"+user.getPassword()+"','"+user.getPicPath()+"')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public boolean isThere(String login) {
        try {
            PreparedStatement ps = connection.prepareStatement("select login from users where login=?");
            ps.setString(1,login);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean exist(String login, String passw) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("select login from users where login=? and passw=?");
            ps.setString(1,login);
            ps.setString(2,passw);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User getUser(String login) {
        User user;
        try {
            PreparedStatement st = connection.prepareStatement("select * from users where login=?");
            st.setString(1,login);
            ResultSet rs = st.executeQuery();
            rs.next();
            user = new User(rs.getBoolean("admin"),rs.getString("name"),rs.getString("login"),rs.getString("description"));
            user.setPicPath(rs.getString("picpath"));
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public ArrayList<Comment> getComments(int id) {
        ArrayList<Comment> comments = new ArrayList<>();
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement("select * from comment inner join users on comment.sender_id = users.id where news_id=? order by comment.id");
            st.setInt(1,id);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                comments.add(new Comment(rs.getString("name"),rs.getString("date"),rs.getString("text"),id,rs.getString("picpath")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comments;
    }

    @Override
    public ArrayList<User> getLikeUsers(String q) {
        ArrayList<User> logins= new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement("select login,picpath from users where login like ? or name like ? limit 10");
            st.setString(1, "%" + q + "%");
            st.setString(2, "%" + q + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                logins.add(new User(rs.getString("login"),rs.getString("picpath")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return logins;
    }
}
