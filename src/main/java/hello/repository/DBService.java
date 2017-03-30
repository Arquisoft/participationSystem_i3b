package hello.repository;

import hello.model.Comment;
import hello.model.Proposal;
import hello.model.User;

import java.util.List;

/**
 * Created by ADRIÁN on 28/03/2017.
 */

public interface DBService {

    //Comment methods
    Comment insertComment(Comment comment, Proposal proposal);
    Comment updateComment(Comment comment);
    Comment findCommentByID(String id);
    //Proposal methods
    Proposal insertProposal(Proposal proposal);
    Proposal updateProposal(Proposal proposal);
    Proposal findProposalById(String id);
    List<Proposal> findAllProposals();

}
