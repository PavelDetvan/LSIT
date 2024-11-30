package lsit.Repositories;

import java.util.*;

import org.springframework.stereotype.Repository;

import lsit.Models.Customer;

@Repository
public class CustomerRepository {
    static HashMap<UUID, Customer> customers = new HashMap<>();

    public void add(Customer customer) {
        customers.put(customer.id, customer);
    }

    public Customer get(UUID id) {
        return customers.get(id);
    }

    public void remove(UUID id) {
        customers.remove(id);
    }

    public void update(Customer customer) {
        Customer existingCustomer = customers.get(customer.id);
        if (existingCustomer != null) {
            existingCustomer.name = customer.name;
            existingCustomer.email = customer.email;
        }
    }

    public List<Customer> list() {
        return new ArrayList<>(customers.values());
    }
}
