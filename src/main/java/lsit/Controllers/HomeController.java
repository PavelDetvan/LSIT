package lsit.Controllers;

import java.util.List;

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
    public String getUser(OAuth2AuthenticationToken authentication) throws Exception {
        // Retrieve the GitLab groups the user belongs to
        var groups = (List<String>) authentication.getPrincipal().getAttribute("https://gitlab.com/claims/groups");

        // Check if user belongs to the 'admins' group for ADMIN role
        if (groups.contains("lsit-ken3239/roles/carrental/admins")) {
            return "<pre>Welcome Admin!\n" + userAttributesToString(authentication) + "</pre>";
        }
        // Check if user belongs to the 'employees' group for EMPLOYEE role
        if (groups.contains("lsit-ken3239/roles/carrental/employees")) {
            return "<pre>Welcome Employee!\n" + userAttributesToString(authentication) + "</pre>";
        }
        // Check if user belongs to the 'customers' group for CUSTOMER role
        if (groups.contains("lsit-ken3239/roles/carrental/customers")) {
            return "<pre>Welcome Customer!\n" + userAttributesToString(authentication) + "</pre>";
        }

        // If user does not belong to any group, restrict access
        throw new Exception("Access Denied...");
    }

    // Helper method to convert user attributes into a readable format
    private String userAttributesToString(OAuth2AuthenticationToken authentication) {
        var userAttributes = authentication.getPrincipal().getAttributes();
        return userAttributes.entrySet().parallelStream().collect(
            StringBuilder::new,
            (s, e) -> s.append(e.getKey()).append(": ").append(e.getValue()).append("\n"),
            StringBuilder::append
        ).toString();
    }
}
