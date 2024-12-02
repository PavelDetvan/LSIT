package lsit.Models;

import java.util.UUID;

public class Customer {
    public UUID id;
    public String name;
    public String email;

    public Customer(String name, String email) {
        validateEmpty(name);
        validateEmpty(email);
        this.id = UUID.randomUUID();
        this.name = name;
        this.email = email;
    }

    private void validateEmpty(String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be empty");
        }
    }
}
