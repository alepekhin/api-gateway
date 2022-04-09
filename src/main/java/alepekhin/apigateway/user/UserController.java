package alepekhin.apigateway.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @GetMapping("/users")
    public List<User> getUsers() {
        return List.of(
                User.builder().name("Alex").build(),
                User.builder().name("Bob").build()
        );
    }
}
