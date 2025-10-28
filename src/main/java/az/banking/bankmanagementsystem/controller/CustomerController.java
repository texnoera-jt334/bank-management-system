package az.banking.bankmanagementsystem.controller;

import az.banking.bankmanagementsystem.entity.Customer;
import az.banking.bankmanagementsystem.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer createdCustomer = customerService.createCustomer(customer);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }

    @GetMapping("/{finCode}")
    public ResponseEntity<Customer> getCustomer(@PathVariable String finCode) {
        Customer customer = customerService.getCustomerByFinCode(finCode);
        return ResponseEntity.ok(customer);
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    @PutMapping("/{finCode}")
    public ResponseEntity<Customer> updateCustomer(
            @PathVariable String finCode,
            @RequestBody Customer updatedCustomer) {

        Customer existingCustomer = customerService.getCustomerByFinCode(finCode);

        if (updatedCustomer.getName() != null) {
            existingCustomer.setName(updatedCustomer.getName());
        }

        if (updatedCustomer.getSurname() != null) {
            existingCustomer.setSurname(updatedCustomer.getSurname());
        }

        if (updatedCustomer.getEmail() != null) {
            existingCustomer.setEmail(updatedCustomer.getEmail());
        }

        if (updatedCustomer.getPhoneNumber() != null) {
            existingCustomer.setPhoneNumber(updatedCustomer.getPhoneNumber());
        }

        if (updatedCustomer.getAddress() != null) {
            existingCustomer.setAddress(updatedCustomer.getAddress());
        }

        if (updatedCustomer.getDateOfBirth() != null) {
            existingCustomer.setDateOfBirth(updatedCustomer.getDateOfBirth());
        }

        existingCustomer.setActive(updatedCustomer.isActive());

        if (updatedCustomer.getPasswordHash() != null) {
            existingCustomer.setPasswordHash(updatedCustomer.getPasswordHash());
        }

        Customer savedCustomer = customerService.updateCustomer(finCode, existingCustomer);
        return ResponseEntity.ok(savedCustomer);
    }

    @DeleteMapping("/{finCode}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable String finCode) {
        customerService.deleteCustomer(finCode);
        return ResponseEntity.noContent().build();
    }
}
