package entities;

import java.util.ArrayList;

public class News {
    private int id;
    private boolean accepted;
    private String crLogin;
    private String crname;
    private String crDesc;
    private String uPicPath;
    private Topic topic;
    private String header;
    private String text;
    private String date;
    private ArrayList<String> tags;
    private String category;
    private ArrayList<String> files;

    public News(String title, String text, String user, String date) {
        this.header = title;
        this.text = text;
        this.crLogin = user;
        this.date = date;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }


    public void setFiles(ArrayList<String> files) {
        this.files = files;
    }

    public News(int id, String crLogin, String header, String date,String category,String uPicPath) {
        this.id = id;
        this.category = category;
        this.crLogin = crLogin;
        this.header = header;
        this.date = date;
        this.uPicPath = uPicPath;
    }

    public News(int id,boolean accepted,String crLogin, String crname, String crDesc, Topic topic, String header, String text, String date,String uPicPath,String category) {
        this.id = id;
        this.category = category;
        this.accepted = accepted;
        this.crLogin = crLogin;
        this.crname = crname;
        this.crDesc = crDesc;
        this.topic = topic;
        this.header = header;
        this.text = text;
        this.date = date;
        this.uPicPath = uPicPath;
    }

    public int getId() {
        return id;
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

    public boolean isAccepted() {
        return accepted;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public String getuPicPath() {
        return uPicPath;
    }

    public ArrayList<String> getFiles() {
        return files;
    }

    public String getCategory() {
        return category;
    }
}
