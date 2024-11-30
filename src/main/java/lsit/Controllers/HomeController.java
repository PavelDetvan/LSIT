package lsit.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    
    @GetMapping("/")
    public ResponseEntity get(){
        return ResponseEntity.ok("Welcome to the Car Rental Service!");
    }

    @GetMapping("/user")
    public String getUser(OAuth2AuthenticationToken authentication){
        var userAttributes = authentication.getPrincipal().getAttributes();

        return "<pre>\n" +
            userAttributes.entrySet().parallelStream().collect(
                StringBuilder::new,
                (s, e) -> s.append(e.getKey()).append(": ").append(e.getValue()).append("\n"),
                StringBuilder::append
            ).toString() +
            "</pre>";
    }
}
