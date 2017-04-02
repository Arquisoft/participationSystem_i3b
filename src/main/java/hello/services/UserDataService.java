package hello.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import hello.model.User;
import hello.repository.DBServiceImpl;

@Service
public class UserDataService implements UserDetailsService {

	@Autowired
	private DBServiceImpl dbService;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User user = dbService.getUser(username);
		if(user!=null){
			return user;
		}
		 throw new UsernameNotFoundException("Username not found");
	}
	
	public void addUserData(User user){
		dbService.addUser(user);
	}


}
