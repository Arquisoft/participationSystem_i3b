package hello.model;

public class AbstractVotable implements Votable {
	
	int upvotes;
	int downvotes;
	
	AbstractVotable() {
		this.upvotes = 0;
		this.downvotes = 0;
	}

	@Override
	public int getVoteBalance() {
		return upvotes-downvotes;
	}

	@Override
	public void upvote() {
		upvotes++;
	}

	@Override
	public void downvote() {
		downvotes++;
	}

	@Override
	public int getUpvotes() {
		return upvotes;
	}

	@Override
	public int getDownvotes() {
		return downvotes;
	}

}
