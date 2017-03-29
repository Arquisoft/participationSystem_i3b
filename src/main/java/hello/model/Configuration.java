package hello.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Oriol
 * The council gives the config directly to the system admin (?)
 */
public class Configuration {
    private Configuration instance;
    private List<String> categories;
    private Date expirationDate;
    private List<String> notAllowedWords;
    private int minimumSupportVotes;

    private Configuration() {
        instance.categories = new ArrayList<>();
        instance.categories.add("Cat1");
        instance.categories.add("Cat2");
        instance.categories.add("Cat3");
        instance.expirationDate = expirationDate;
        instance.notAllowedWords = new ArrayList<>();
        instance.notAllowedWords.add("ex1");
        instance.notAllowedWords.add("ex2");
        instance.notAllowedWords.add("ex3");
        instance.minimumSupportVotes = 10;
    }

    public static Configuration getInstance() {
        if(instance == null) {
            instance = new Configuration();
        }
        return instance;
    }

    public List<String> getCategories() {
        return categories;
    }

    public Date getActiveDate() {
        return expirationDate;
    }

    public List<String> getNotAllowedWords() {
        return notAllowedWords;
    }

    public int getMinimumSupportVotes() {
        return minimumSupportVotes;
    }

    public void setMinimumSupportVotes(int minimumSupportVotes) {
        this.minimumSupportVotes = minimumSupportVotes;
    }

    @Override
    public String toString() {
        return "Configuration{" +
                ", categories=" + categories +
                ", expirationDate=" + expirationDate +
                ", notAllowedWords=" + notAllowedWords +
                ", minimumSupportVotes=" + minimumSupportVotes +
                '}';
    }
}
