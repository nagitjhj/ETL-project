package my.etl.sample.controller;

import lombok.RequiredArgsConstructor;
import my.etl.sample.entity.Users;
import my.etl.sample.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;

    @PostMapping
    public Users createUser(@RequestBody Users users) {
        return userRepository.save(users);
    }

    @GetMapping
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }
}
