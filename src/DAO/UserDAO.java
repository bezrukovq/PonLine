package DAO;

import entities.Comment;
import entities.User;

import java.util.ArrayList;

public interface UserDAO {
    public boolean addNewUser(User user);
    public boolean isThere(String login);
    public boolean exist(String login, String passw);

    User getUser(String login);

    ArrayList<Comment> getComments(int id);

    ArrayList<User> getLikeUsers(String q);
}
