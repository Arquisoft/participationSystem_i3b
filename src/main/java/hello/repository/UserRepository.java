package hello.repository;

import hello.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Oriol on 28/03/2017.
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {

    //User findByEmail(String email);
}
