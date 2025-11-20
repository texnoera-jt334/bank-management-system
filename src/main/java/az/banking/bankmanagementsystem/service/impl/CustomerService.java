package az.banking.bankmanagementsystem.service.impl;

import az.banking.bankmanagementsystem.entity.Customer;

import java.util.List;

public interface CustomerService {
    Customer createCustomer(Customer customer);

    Customer getCustomerByFinCode(String finCode);

    List<Customer> getAllCustomers();

    Customer updateCustomer(String finCode, Customer customer);

    void deleteCustomer(String finCode);
}
