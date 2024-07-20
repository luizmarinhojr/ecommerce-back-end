package ecommerce.com.backend.controller;

import ecommerce.com.backend.domain.user.User;
import ecommerce.com.backend.domain.user.UserAuthenticationDto;
import ecommerce.com.backend.infra.security.TokenJwtDto;
import ecommerce.com.backend.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenJwtDto> login(@RequestBody @Valid UserAuthenticationDto userInput) {
        var token = new UsernamePasswordAuthenticationToken(userInput.email(), userInput.password());
        var authentication = manager.authenticate(token);
        var tokenJwt = tokenService.generateToken((User) authentication.getPrincipal());
        return ResponseEntity.ok(new TokenJwtDto(LocalDateTime.now(), tokenJwt));
    }
}
