package hello.repository;

import hello.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Oriol on 28/03/2017.
 */
@Repository
public interface ProposalRepository extends MongoRepository<User, String> {

    User insert(User user);

    User findByEmail(String email);
}
