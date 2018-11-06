package entities;

public class Comment {
    private String crLogin;
    private String crname;
    private String crDesc;
    private int news_id;
    private String date;
    private String text;
    private String uPicPath;

    public String getuPicPath() {
        return uPicPath;
    }

    public int getNews_id() {
        return news_id;
    }



    public Comment(String crLogin, String date, String text, int news_id, String uPicPath) {
        this.crLogin = crLogin;
        this.date = date;
        this.news_id = news_id;
        this.text = text;
        this.uPicPath = uPicPath;
    }


    public Comment(String crLogin, String date, String text, int news_id) {
        this.crLogin = crLogin;
        this.date = date;
        this.news_id = news_id;
        this.text = text;
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

    public String getDate() {
        return date;
    }

    public String getText() {
        return text;
    }
}
