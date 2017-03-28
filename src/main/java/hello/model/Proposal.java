package hello.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "VotingSystem")
public class Proposal extends AbstractVotable {
	@Id
	private String id;
	private User author;
	private List<Comment> comments;
	private boolean isAccepted;
	private String category;
	private String title;
	private String content;

	private Proposal() {

	}

	public Proposal(User author, String category, String title, String content) {
		super();
		this.author = author;
		this.category = category;
		this.title = title;
		this.content = content;
	}

	public boolean isAccepted() {
		return isAccepted;
	}

	public void setAccepted(boolean isAccepted) {
		this.isAccepted = isAccepted;
	}

	public User getAuthor() {
		return author;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public String getCategory() {
		return category;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	@Override
	public String toString() {
		return "Proposal [author=" + author + ", category=" + category + ", title=" + title + ", isAccepted="
				+ isAccepted + "]";
	}

	public String getId() {
		return id;
	}
}
