package services;

import DAO.UserDAO;
import DAO.impl.SimpleUserDAO;
import entities.User;

public class UserService {
        UserDAO userDAO = new SimpleUserDAO();
    public boolean registerNewUser(String name, String login, String password, String description){
        //TODO шифрование пароля
        return userDAO.addNewUser(new User(false,name,login,password,description));
    }
    public boolean isThere(String login){
        return userDAO.isThere(login);
    }
}
