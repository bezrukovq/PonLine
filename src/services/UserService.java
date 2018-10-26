package services;

import DAO.UserDAO;
import DAO.impl.SimpleUserDAO;
import entities.User;
import helper.Helper;

import java.security.MessageDigest;

public class UserService {
        UserDAO userDAO = new SimpleUserDAO();

    public boolean exist(String login, String passw) {
        passw = Helper.md5Custom(passw);
        return userDAO.exist(login,passw);
    }

    public boolean registerNewUser(String name, String login, String password, String description){
        //TODO шифрование пароля
        password = Helper.md5Custom(password);
        return userDAO.addNewUser(new User(false,name,login,password,description));
    }
    public boolean isThere(String login){
        return userDAO.isThere(login);
    }

    public User getUser(String login) {
        return userDAO.getUser(login);
    }
}
