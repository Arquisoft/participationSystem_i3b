package hello.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hello.model.CreateUser;
import hello.model.User;

@Service
public class RegistrationService {

	@Autowired
	UserDataService userDataService;

	public boolean registrate(CreateUser data) {
		User newUser = new User();
		newUser.setName(data.getUsername());
		newUser.setPassword(data.getPassword());

		userDataService.addUserData(newUser);
		return true;
	}

}
