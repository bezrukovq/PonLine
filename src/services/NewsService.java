package services;

import DAO.NewsDAO;
import DAO.impl.SimpleNewsDAO;
import entities.Comment;
import entities.News;

import java.util.ArrayList;

public class NewsService {
    NewsDAO newsDAO = new SimpleNewsDAO();
    public ArrayList<News> getNewsForList(){
        ArrayList<News> news = newsDAO.getNewsforList();
        for (News n : news){
            n.setTags(newsDAO.getTags(n.getId()));
        }
        return news;
    }

    public News getNewsByID(int i) {
        News n = newsDAO.getNewsByID(i);
        //n.setFiles(newsDAO.getFiles(i));
        n.setTags(newsDAO.getTags(i));
        return n;
    }

    public ArrayList<News> getNewsForAdminList() {
        ArrayList<News> news = newsDAO.getNewsforAdminList();
        for (News n : news){
            n.setTags(newsDAO.getTags(n.getId()));
        }
        return news;
    }

    public ArrayList<News> getNewsForUserList(String userToShow) {
        ArrayList<News> news = newsDAO.getNewsforUserList(userToShow);
        for (News n : news){
            n.setTags(newsDAO.getTags(n.getId()));
        }
        return news;
    }

    public void addPost(News news) {
        newsDAO.addPost(news);
    }

    public void deleteNews(int id) {
        newsDAO.deleteNews(id);
    }

    public void acceptNews(int id) {
        newsDAO.acceptNews(id);
    }

    public void comment(Comment comment) {
        newsDAO.comment(comment);
    }

    public ArrayList<News> getNewsForListWithFilter(String filter,String s) {
        return newsDAO.getNewsforListWithFilter(filter,s);
    }
}
