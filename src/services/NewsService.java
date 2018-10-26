package services;

import DAO.NewsDAO;
import DAO.impl.SimpleNewsDAO;
import entities.News;

import java.util.ArrayList;

public class NewsService {
    NewsDAO newsDAO = new SimpleNewsDAO();
    public ArrayList<News> getNewsForList(){
        ArrayList<News> news = newsDAO.getNewsforList();
        for (News n : news){
            n.setTags(newsDAO.getTags(n.getId()));
            n.setCategories(newsDAO.getCategories(n.getId()));
        }
        return news;
    }

    public News getNewsByID(int i) {
        News n = newsDAO.getNewsByID(i);
        //n.setFiles(newsDAO.getFiles(i));
        n.setTags(newsDAO.getTags(i));
        n.setCategories(newsDAO.getCategories(i));
        return n;
    }

    public ArrayList<News> getNewsForAdminList() {
        ArrayList<News> news = newsDAO.getNewsforAdminList();
        for (News n : news){
            n.setTags(newsDAO.getTags(n.getId()));
            n.setCategories(newsDAO.getCategories(n.getId()));
        }
        return news;
    }

    public ArrayList<News> getNewsForUserList(String userToShow) {
        ArrayList<News> news = newsDAO.getNewsforUserList(userToShow);
        for (News n : news){
            n.setTags(newsDAO.getTags(n.getId()));
            n.setCategories(newsDAO.getCategories(n.getId()));
        }
        return news;
    }
}
