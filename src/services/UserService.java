package services;

import DAO.UserDAO;
import DAO.impl.SimpleUserDAO;
import entities.Comment;
import entities.User;
import helper.Helper;

import java.util.ArrayList;

public class UserService {
        UserDAO userDAO = new SimpleUserDAO();

    public boolean exist(String login, String passw) {
        passw = Helper.md5Custom(passw);
        return userDAO.exist(login,passw);
    }

    public boolean registerNewUser(String name, String login, String password, String description,String fileName){
        password = Helper.md5Custom(password);
        User u = new User(false,name,login,password,description);
        u.setPicPath(fileName);
        return userDAO.addNewUser(u);
    }
    public boolean isThere(String login){
        return userDAO.isThere(login);
    }

    public User getUser(String login) {
        return userDAO.getUser(login);
    }

    public  ArrayList<Comment> getComments(int id) {
        return userDAO.getComments(id);
    }

    public ArrayList<User> getLikeUsers(String q) {
        return userDAO.getLikeUsers(q);
    }
}
