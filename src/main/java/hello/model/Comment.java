package hello.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "VotingSystem")
public class Comment extends AbstractVotable {
	@Id
	private String id;
	private User user;
	private String content;
	
	

	public Comment() {

	}

	public Comment(User user, String content) {
		super();
		this.user = user;
		this.content = content;
	}

	public User getUser() {
		return user;
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

		Comment comment = (Comment) o;

		if (id != null ? !id.equals(comment.id) : comment.id != null) return false;
		if (user != null ? !user.equals(comment.user) : comment.user != null) return false;
		return content != null ? content.equals(comment.content) : comment.content == null;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (user != null ? user.hashCode() : 0);
		result = 31 * result + (content != null ? content.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Comment{" +
				"id='" + id + '\'' +
				", user=" + user +
				", content='" + content + '\'' +
				'}';
	}
}
