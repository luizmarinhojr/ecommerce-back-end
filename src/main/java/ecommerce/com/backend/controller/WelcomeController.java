package ecommerce.com.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @GetMapping
    public String welcomePage() {
        return """
                <h1>Welcome to Rest API e-commerce</h1>
                <p>Where many requisitions can discovery every thing</p>
                """;
    }
}
