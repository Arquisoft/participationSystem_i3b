package hello.repository;

import hello.model.Comment;
import hello.model.Proposal;
import hello.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ADRIÁN on 28/03/2017.
 */

@Service
public class DBServiceImpl implements DBService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private ProposalRepository proposalRepository;

	@Override
	public Proposal insertProposal(Proposal proposal) {
		proposalRepository.insert(proposal);
		return proposal;
	}

	@Override
	public Comment insertComment(Comment comment, Proposal proposal) {
		proposal.getComments().put(comment.getId(), comment);
		proposalRepository.save(proposal);
		return comment;
	}

	@Override
	public Comment updateComment(String proposalId, Comment comment) {
		Proposal prop = findProposalById(proposalId);
		prop.getComments().put(comment.getId(), comment);
		proposalRepository.save(prop);
		return comment;
	}

	@Override
	public Comment findCommentByID(String proposalId, String id) {
		Proposal proposal = proposalRepository.findById(proposalId);
		return proposal.getComments().get(id);

	}

	@Override
	public Proposal updateProposal(Proposal proposal) {
		return proposalRepository.save(proposal);
	}

	@Override
	public Proposal findProposalById(String id) {
		return proposalRepository.findById(id);
	}

	@Override
	public List<Proposal> findAllProposals() {
		return proposalRepository.findAll();
	}

	@Override
	public void addUser(User user) {
		userRepository.insert(user);

	}

	@Override
	public User getUser(String username) {
		return userRepository.findByName(username);
	}

	@Override
	public void deleteProposalById(String proposal) {
		Proposal toDelete = findProposalById(proposal);
		proposalRepository.delete(toDelete);

	}
}
