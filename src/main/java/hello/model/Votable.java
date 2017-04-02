package hello.model;

public interface Votable {
	public int getVoteBalance();
	public int getUpvotes();
	public int getDownvotes();
	public void upvote(String userId);
	public void downvote(String userId);
}
