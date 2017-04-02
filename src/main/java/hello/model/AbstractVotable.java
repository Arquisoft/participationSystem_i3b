package hello.model;

import java.util.HashMap;
import java.util.Map;

public class AbstractVotable implements Votable {

	protected Map<String, VoteType> votes;
	protected int positiveVotes;
	protected int negativeVotes;

	AbstractVotable() {
		this.votes = new HashMap<>();
		this.positiveVotes = 0;
		this.negativeVotes = 0;
	}

	@Override
	public int getVoteBalance() {
		return positiveVotes - negativeVotes;
	}

	@Override
	public void upvote(String userId) {

		VoteType vote = votes.get(userId);

		if (vote != null) {
			if (vote.equals(VoteType.NEGATIVE)) {
				negativeVotes--;
				positiveVotes++;
				votes.put(userId, VoteType.POSITIVE);
			}

		} else {
			votes.put(userId, VoteType.POSITIVE);
			positiveVotes++;
		}

	}

	@Override
	public void downvote(String userId) {
		VoteType vote = votes.get(userId);

		if (vote != null) {
			if (vote.equals(VoteType.POSITIVE)) {
				negativeVotes++;
				positiveVotes--;
				votes.put(userId, VoteType.NEGATIVE);
			}

		} else {
			votes.put(userId, VoteType.NEGATIVE);
			negativeVotes++;
		}
	}

	@Override
	public int getUpvotes() {
		return positiveVotes;
	}

	@Override
	public int getDownvotes() {
		return negativeVotes;
	}

}
