package DAO;

import entities.User;

public interface UserDAO {
    public boolean addNewUser(User user);
    public boolean isThere(String login);
    public boolean exist(String login, String passw);
}
