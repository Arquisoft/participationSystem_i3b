package hello.repository;

import hello.model.Comment;
import hello.model.Proposal;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Oriol on 28/03/2017.
 */
@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {

    Comment findById(String id);

    Comment save(Comment comment);
    
}
