package com.learn.Learn1.controllers;

// controller/UserController.java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.learn.Learn1.models.User;
import java.util.UUID;
import com.learn.Learn1.services.UserService;
import java.util.List;
import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/users")
public class UserController {
    static class UserDTO {
        public UUID id;
        public String name;
        public String email;
        public UserDTO(User user) {
            this.id = user.getId();
            this.name = user.getName();
            this.email = user.getEmail();
        }
    }
    @Autowired
    private UserService userService;

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

@GetMapping("/me")
public ResponseEntity<?> getCurrentUser(Authentication authentication) {
    User user = (User) authentication.getPrincipal();  // principal is User
    System.out.println("Auth email: " + user.getEmail());

    return ResponseEntity.ok(new UserDTO(user));
}

}
