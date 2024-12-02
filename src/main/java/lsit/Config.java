package lsit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.http.HttpMethod;

@Configuration
@EnableWebSecurity
public class Config {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .oauth2Login(withDefaults())
            .authorizeHttpRequests(auth -> auth
                // Cars Endpoint Access
                .requestMatchers(HttpMethod.GET, "/cars/**").hasAnyRole("ADMIN", "EMPLOYEE", "CUSTOMER")  // All roles can view cars
                .requestMatchers(HttpMethod.POST, "/cars/**").hasRole("ADMIN")  // Only Admins can add cars
                .requestMatchers(HttpMethod.PUT, "/cars/**").hasAnyRole("ADMIN", "EMPLOYEE")  // Admins and Employees can update cars
                .requestMatchers(HttpMethod.DELETE, "/cars/**").hasRole("ADMIN")  // Only Admins can delete cars
                
                // Customers Endpoint Access
                .requestMatchers(HttpMethod.GET, "/customers/**").hasAnyRole("ADMIN", "EMPLOYEE")  // Only Admins and Employees can view customer list
                .requestMatchers(HttpMethod.POST, "/customers/**").hasRole("ADMIN")  // Only Admins can add new customers
                .requestMatchers(HttpMethod.PUT, "/customers/**").authenticated()  // Any authenticated user can attempt to update a customer profile (specific logic in controller)
                .requestMatchers(HttpMethod.DELETE, "/customers/**").hasRole("ADMIN")  // Only Admins can delete customers

                // Bookings Endpoint Access
                .requestMatchers(HttpMethod.GET, "/bookings/**").hasAnyRole("ADMIN", "EMPLOYEE", "CUSTOMER")  // All roles can view bookings (custom validation in BookingController to restrict viewing to own bookings for customers)
                .requestMatchers(HttpMethod.POST, "/bookings/**").hasAnyRole("ADMIN", "EMPLOYEE", "CUSTOMER")  // All roles can create booking
                .requestMatchers(HttpMethod.PUT, "/bookings/**").hasAnyRole("ADMIN", "EMPLOYEE")  // Admins and Employees can update bookings
                .requestMatchers(HttpMethod.DELETE, "/bookings/**").hasRole("ADMIN")  // Only Admins can delete bookings

                // Home Endpoint Access
                .requestMatchers("/user").authenticated()  // Only authenticated users can access user info
                .anyRequest().permitAll()  // All other requests are publicly accessible
            );


        return http.build();
    }
}
