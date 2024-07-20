package ecommerce.com.backend.controller;

import ecommerce.com.backend.domain.user.User;
import ecommerce.com.backend.domain.user.UserDtoInput;
import ecommerce.com.backend.domain.user.UserDtoOutput;
import ecommerce.com.backend.domain.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<UserDtoOutput> describeUser(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(new UserDtoOutput(user));
    }

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<UserDtoOutput> registerUser(@RequestBody @Valid UserDtoInput userInput) {
        var user = service.register(userInput);
        var uri = URI.create("/user");
        return ResponseEntity.created(uri).body(user);
    }
}
