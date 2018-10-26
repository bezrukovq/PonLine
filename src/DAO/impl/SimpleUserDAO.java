package DAO.impl;

import DAO.UserDAO;
import entities.User;
import helper.Helper;

import java.sql.*;

public class SimpleUserDAO implements UserDAO {
    Connection connection= Helper.getConnection();
    @Override
    public boolean addNewUser(User user) {
        boolean res = false;
        try {
            Statement statement = connection.createStatement();
             res = statement.execute("insert into users(admin, name, login, description, passw) values('false','"+user.getName()
                    +"','"+user.getLogin()+ "','"
                    +user.getDescription()+"','"+user.getPassword()+"')");
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
        try {
            PreparedStatement st = connection.prepareStatement("select * from users where login=?");
            st.setString(1,login);
            ResultSet rs = st.executeQuery();
            rs.next();
            return new User(rs.getBoolean("admin"),rs.getString("name"),rs.getString("login"),rs.getString("description"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
