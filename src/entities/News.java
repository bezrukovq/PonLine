package entities;

import java.util.ArrayList;

public class News {
    private int id;
    private String crLogin;
    private String crname;
    private String crDesc;
    private Topic topic;
    private String header;
    private String text;
    private String date;
    private ArrayList<String> tags;
    private ArrayList<String> categories;
    private ArrayList<String> files;

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public void setCategories(ArrayList<String> categories) {
        this.categories = categories;
    }

    public void setFiles(ArrayList<String> files) {
        this.files = files;
    }

    public News(int id, String crLogin, String header, String date) {
        this.id = id;
        this.crLogin = crLogin;
        this.header = header;
        this.date = date;
    }

    public News(int id,String crLogin, String crname, String crDesc, Topic topic, String header, String text, String date, ArrayList<String> tags, ArrayList<String> categories, ArrayList<String> files) {
        this.id = id;
        this.crLogin = crLogin;
        this.crname = crname;
        this.crDesc = crDesc;
        this.topic = topic;
        this.header = header;
        this.text = text;
        this.date = date;
        this.tags = tags;
        this.categories = categories;
        this.files = files;
    }

    public String getCrLogin() {
        return crLogin;
    }

    public String getCrname() {
        return crname;
    }

    public String getCrDesc() {
        return crDesc;
    }

    public Topic getTopic() {
        return topic;
    }

    public String getHeader() {
        return header;
    }

    public String getText() {
        return text;
    }

    public String getDate() {
        return date;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public ArrayList<String> getCategories() {
        return categories;
    }

    public ArrayList<String> getFiles() {
        return files;
    }
}
