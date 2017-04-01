package hello.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "VotingSystem")
public class Proposal extends AbstractVotable {
	

	@Id
	private String id;

	private User author;
	private List<Comment> comments = new ArrayList<Comment>();
	private boolean isAccepted;
	private String category;
	private String title;
	private String content;

	public void setId(String id) {
		this.id = id;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Proposal() {

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

	public String getId() {
		return id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Proposal proposal = (Proposal) o;

		if (isAccepted != proposal.isAccepted) return false;
		if (id != null ? !id.equals(proposal.id) : proposal.id != null) return false;
		if (author != null ? !author.equals(proposal.author) : proposal.author != null) return false;
		if (comments != null ? !comments.equals(proposal.comments) : proposal.comments != null) return false;
		if (category != null ? !category.equals(proposal.category) : proposal.category != null) return false;
		if (title != null ? !title.equals(proposal.title) : proposal.title != null) return false;
		return content != null ? content.equals(proposal.content) : proposal.content == null;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (author != null ? author.hashCode() : 0);
		result = 31 * result + (comments != null ? comments.hashCode() : 0);
		result = 31 * result + (isAccepted ? 1 : 0);
		result = 31 * result + (category != null ? category.hashCode() : 0);
		result = 31 * result + (title != null ? title.hashCode() : 0);
		result = 31 * result + (content != null ? content.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Proposal{" +
				"id='" + id + '\'' +
				", author=" + author +
				", comments=" + comments +
				", isAccepted=" + isAccepted +
				", category='" + category + '\'' +
				", title='" + title + '\'' +
				", content='" + content + '\'' +
				'}';
	}
}
