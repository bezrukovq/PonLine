package services;

import DAO.UserDAO;
import DAO.impl.SimpleUserDAO;
import entities.User;

public class UserService {
        UserDAO userDAO = new SimpleUserDAO();
    public void registerNewUser(String name, String login, String password, String description){
        userDAO.addNewUser(new User(false,name,login,password,description));
    }
}
