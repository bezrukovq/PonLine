package entities;

public class User {

    private int id;
    private boolean admin;
    private String name;
    private String login;
    private String password;
    private String description;

    public User(boolean admin, String name, String login, String password, String description) {
        this.admin = admin;
        this.name = name;
        this.login = login;
        this.password = password;
        this.description = description;
    }

    public User(boolean admin, String name, String login, String description) {
        this.admin = admin;
        this.name = name;
        this.login = login;
        this.description = description;
    }

    public User(int id, boolean admin, String name, String login, String password) {
        this.id = id;
        this.admin = admin;
        this.name = name;
        this.login = login;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public boolean isAdmin() {
        return admin;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getDescription() {
        return description;
    }
}
