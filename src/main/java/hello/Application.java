package hello;

import hello.producers.KafkaTopicCreation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		/*
		try {

			KafkaTopicCreation.createTopic("upvotedComment", 1, 1);
			KafkaTopicCreation.createTopic("downvotedComment", 1, 1);
			KafkaTopicCreation.createTopic("upvotedProposal", 1, 1);
			KafkaTopicCreation.createTopic("downvotedProposal", 1, 1);
			KafkaTopicCreation.createTopic("createdComment", 1, 1);
			KafkaTopicCreation.createTopic("createdProposal", 1, 1);

		} catch (Exception e) {
			e.printStackTrace();
		}*/
		SpringApplication.run(Application.class, args);
	}
}