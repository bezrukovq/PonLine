package entities;

public class Comment {
    private String crLogin;
    private String crname;
    private String crDesc;
    private String date;
    private String text;

    public Comment(String crLogin, String crname, String crDesc, String date, String text) {
        this.crLogin = crLogin;
        this.crname = crname;
        this.crDesc = crDesc;
        this.date = date;
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
