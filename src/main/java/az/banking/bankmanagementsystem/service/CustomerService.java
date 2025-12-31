package az.banking.bankmanagementsystem.service;

import az.banking.bankmanagementsystem.dao.entity.Customer;
import az.banking.bankmanagementsystem.dto.CustomerCreatRequest;
import az.banking.bankmanagementsystem.dto.CustomerCreatResponse;

import java.util.List;

public interface CustomerService {

    CustomerCreatResponse createCustomer(CustomerCreatRequest customerCreatRequest);

    Customer getCustomerByFinCode(String finCode);

    List<Customer> getAllCustomers();

    Customer updateCustomer(String finCode, Customer customer);

    void deleteCustomer(String finCode);
}
