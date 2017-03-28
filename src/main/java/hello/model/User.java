package hello.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "VotingSystem")
public class User {
	
	// Log
	private static final Logger LOG = LoggerFactory.getLogger(User.class);
    @Id
    private String id;
    private String name;
    private Integer age;

    private User(){

    }

    public User(String name, Integer age) {
    	LOG.info("Creating user " + name + ". age: " + age);
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public String getId() {
        return id;
    }
}