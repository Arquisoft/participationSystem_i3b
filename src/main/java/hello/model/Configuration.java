package hello.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

/**
 * @author Oriol
 */
@Document(collection = "VotingSystem")
public class Configuration {
    @Id
    private String id;
    private List<String> categories;
    private Date expirationDate;
    private List<String> notAllowedWords;
    private int minimumSupportVotes;

    public Configuration(List<String> categories, Date expirationDate,
                         List<String> notAllowedWords, int votes) {
        this.categories = categories;
        this.expirationDate = expirationDate;
        this.notAllowedWords = notAllowedWords;
        this.minimumSupportVotes = votes;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public void setActiveDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setNotAllowedWords(List<String> notAllowedWords) {
        this.notAllowedWords = notAllowedWords;
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

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Configuration that = (Configuration) o;

        return id.equals(that.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Configuration{" +
                "id='" + id + '\'' +
                ", categories=" + categories +
                ", expirationDate=" + expirationDate +
                ", notAllowedWords=" + notAllowedWords +
                ", minimumSupportVotes=" + minimumSupportVotes +
                '}';
    }
}
