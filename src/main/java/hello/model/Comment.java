package hello.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "VotingSystem")
public class Comment extends AbstractVotable {
	@Id
	private String id;
	private User user;
	private String content;

	private Comment() {

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
}
