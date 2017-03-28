package hello.repository;

import hello.model.Proposal;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Oriol on 28/03/2017.
 */
@Repository
public interface ProposalRepository extends MongoRepository<Proposal, String> {

    Proposal insert(Proposal proposal);

    Proposal save(Proposal proposal);

    Proposal findById(String id);

    List<Proposal> findAll();
}
