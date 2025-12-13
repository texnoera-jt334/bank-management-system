package az.banking.bankmanagementsystem.service;

import az.banking.bankmanagementsystem.dao.entity.Customer;

import java.util.List;

public interface CustomerService {
    Customer createCustomer(Customer customer);

    Customer getCustomerByFinCode(String finCode);

    List<Customer> getAllCustomers();

    Customer updateCustomer(String finCode, Customer customer);

    void deleteCustomer(String finCode);
}
