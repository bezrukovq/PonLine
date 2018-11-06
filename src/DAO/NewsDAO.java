package DAO;

import entities.Comment;
import entities.News;

import java.util.ArrayList;

public interface NewsDAO {
    ArrayList<News> getNewsforList();

    ArrayList<String> getTags(int id);

    ArrayList<String> getCategories(int id);

    News getNewsByID(int i);

    ArrayList<News> getNewsforAdminList();

    ArrayList<News> getNewsforUserList(String userToShow);

    void addPost(News news);

    void deleteNews(int id);

    void acceptNews(int id);

    void comment(Comment comment);

    ArrayList<News> getNewsforListWithFilter(String filter,String s);
}
