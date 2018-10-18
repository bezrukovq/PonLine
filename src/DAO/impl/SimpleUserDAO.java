package DAO.impl;

import DAO.UserDAO;
import entities.User;
import helper.Helper;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SimpleUserDAO implements UserDAO {
    @Override
    public void addNewUser(User user) {
        Connection connection= Helper.getConnection();
        try {
            Statement statement = connection.createStatement();
            boolean res = statement.execute("insert into users(admin, name, login, describtion, passw) values('false','"+user.getName()
                    +"','"+user.getLogin()+ "','"
                    +user.getDescription()+"','"+user.getPassword()+"')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
