package hello;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hello.model.User;

@RestController
public class APIController {

    @RequestMapping("/user")
    public User user() {
        return new User("pepe",0);
    }

}