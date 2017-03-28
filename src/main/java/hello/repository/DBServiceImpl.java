package hello.repository;

import hello.model.Comment;
import hello.model.Proposal;
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
        proposal.getComments().add(comment);
        proposalRepository.save(proposal);
        return comment;
    }

    @Override
    public Comment updateComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment findCommentByID(String id) {
        return commentRepository.findById(id);
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
}
