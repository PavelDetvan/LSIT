package lsit.Controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import lsit.Models.Customer;
import lsit.Repositories.CustomerRepository;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")  // Only Admins and Employees can view customer list
    public List<Customer> list() {
        return customerRepository.list();
    }

    @GetMapping("/{id}")
    @PreAuthorize("#id == authentication.principal.attributes['sub'] or hasRole('ADMIN') or hasRole('EMPLOYEE')")  // Customers can only view their own profile, Admins and Employees can view any profile
    public Customer get(@PathVariable("id") UUID id) {
        return customerRepository.get(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")  // Only Admins can add new customers manually
    public Customer add(@RequestBody Customer customer) {
        validateCustomer(customer);
        customerRepository.add(customer);
        return customer;
    }

    @PutMapping("/{id}")
    @PreAuthorize("#id == authentication.principal.attributes['sub'] or hasRole('ADMIN')")  // Customers can update their own profile, Admins can update any customer
    public Customer update(@PathVariable("id") UUID id, @RequestBody Customer customer) {
        validateCustomer(customer);
        customer.id = id;
        customerRepository.update(customer);
        return customer;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")  // Only Admins can delete customers
    public void delete(@PathVariable("id") UUID id) {
        customerRepository.remove(id);
    }

    private void validateCustomer(Customer customer) {
        if (customer.name == null || customer.name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        if (customer.email == null || customer.email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty.");
        }
    }
}
