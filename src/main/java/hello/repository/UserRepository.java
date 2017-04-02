package hello.repository;

import hello.model.User;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Oriol on 28/03/2017.
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {

	// User findByEmail(String email);
	public User findByName(String username);

	User insert(User user);

	List<User> findAll();
}
