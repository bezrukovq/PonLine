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
             res = statement.execute("insert into users(admin, name, login, describtion, passw) values('false','"+user.getName()
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
}
