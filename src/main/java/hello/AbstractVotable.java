package hello;

public class AbstractVotable implements Votable {
	
	int votes;
	
	AbstractVotable() {
		this.votes = 0;
	}

	@Override
	public int getVotes() {
		return votes;
	}

	@Override
	public void upvote() {
		votes++;
	}

	@Override
	public void downvote() {
		votes--;
	}

}
