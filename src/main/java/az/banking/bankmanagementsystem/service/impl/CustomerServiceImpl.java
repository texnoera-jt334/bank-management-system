package az.banking.bankmanagementsystem.service.impl;

import az.banking.bankmanagementsystem.entity.Customer;
import az.banking.bankmanagementsystem.exception.CustomerAlreadyExistsException;
import az.banking.bankmanagementsystem.exception.CustomerNotFoundException;
import az.banking.bankmanagementsystem.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        if (customerRepository.existsById(customer.getFinCode())) {
            throw new CustomerAlreadyExistsException("Bu FIN kodu ilə müştəri artıq mövcuddur: " + customer.getFinCode());
        }

        return customerRepository.save(customer);
    }

    @Override
    public Customer getCustomerByFinCode(String finCode) {
        return customerRepository.findById(finCode)
                .orElseThrow(() -> new CustomerNotFoundException("Müştəri tapılmadı. FIN: " + finCode));
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer updateCustomer(String finCode, Customer updatedCustomer) {
        Customer existingCustomer = customerRepository.findById(finCode)
                .orElseThrow(() -> new CustomerNotFoundException("Müştəri tapılmadı. FIN: " + finCode));

        existingCustomer.setName(updatedCustomer.getName());
        existingCustomer.setSurname(updatedCustomer.getSurname());
        existingCustomer.setEmail(updatedCustomer.getEmail());
        existingCustomer.setPhoneNumber(updatedCustomer.getPhoneNumber());
        existingCustomer.setAddress(updatedCustomer.getAddress());
        existingCustomer.setDateOfBirth(updatedCustomer.getDateOfBirth());
        existingCustomer.setActive(updatedCustomer.isActive());
        existingCustomer.setPasswordHash(updatedCustomer.getPasswordHash());

        return customerRepository.save(existingCustomer);
    }

    @Override
    public void deleteCustomer(String finCode) {
        customerRepository.deleteById(finCode);
    }
}
