package hello;

public class Comment extends AbstractVotable {
	private UserInfo user;
	private String content;
	
	public Comment(UserInfo user, String content) {
		super();
		this.user = user;
		this.content = content;
	}

	public UserInfo getUser() {
		return user;
	}

	public String getContent() {
		return content;
	}
	
}
