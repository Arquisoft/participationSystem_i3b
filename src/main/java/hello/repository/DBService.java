package hello.repository;

import hello.model.Comment;
import hello.model.Proposal;
import hello.model.User;

import java.util.List;

import org.apache.catalina.startup.UserDatabase;

/**
 * Created by ADRIÁN on 28/03/2017.
 */

public interface DBService {

	// Comment methods
	Comment insertComment(Comment comment, Proposal proposal);

	Comment updateComment(String proposalId, Comment comment);

	// Proposal methods
	Proposal insertProposal(Proposal proposal);

	Proposal updateProposal(Proposal proposal);

	Proposal findProposalById(String id);

	List<Proposal> findAllProposals();

	public void addUser(User user);

	public User getUser(String username);

	Comment findCommentByID(String proposalId, String id);

}
