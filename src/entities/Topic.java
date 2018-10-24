package entities;

public class Topic {
    private String name;
    private String link;

    public String getName() {
        return name;
    }

    public String getLink() {
        return link;
    }

    public Topic(String name, String link) {
        this.name = name;
        this.link = link;
    }
}
