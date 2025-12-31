package az.banking.bankmanagementsystem.service.impl;

import az.banking.bankmanagementsystem.Security.model.Enum.UserRole;
import az.banking.bankmanagementsystem.Security.repository.AuthUserEntity;
import az.banking.bankmanagementsystem.Security.repository.AuthUserRpository;
import az.banking.bankmanagementsystem.dao.entity.Customer;
import az.banking.bankmanagementsystem.dto.CustomerCreatRequest;
import az.banking.bankmanagementsystem.dto.CustomerCreatResponse;
import az.banking.bankmanagementsystem.error.exception.CustomerAlreadyExistsException;
import az.banking.bankmanagementsystem.error.exception.CustomerNotFoundException;
import az.banking.bankmanagementsystem.mapper.CustomerMapper;
import az.banking.bankmanagementsystem.repository.CustomerRepository;
import az.banking.bankmanagementsystem.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private  final CustomerMapper customerMapper;
     private  final AuthUserRpository  authUserRpository;




    @Override
    public CustomerCreatResponse createCustomer(CustomerCreatRequest customerCreatRequest) {
        if (customerRepository.existsById(customerCreatRequest.getFinCode())) {
            throw new CustomerAlreadyExistsException("Bu FIN kodu ilə müştəri artıq mövcuddur: " + customerCreatRequest.getFinCode());
        }
        if (customerRepository.existsByPhoneNumber((customerCreatRequest.getPhoneNumber()))) {
            throw new RuntimeException("Bu telefon nömrəsi artıq qeydiyyatdan keçib");
        }
        if (customerRepository.existsByEmail((customerCreatRequest.getEmail()))){
            throw new RuntimeException("Bu Email artıq mövcuddur");
        }

         Customer customer= customerMapper.toCustomerEntity(customerCreatRequest);

        customerRepository.save(customer);

        AuthUserEntity authUserEntity= createAuthUser(customer,customerCreatRequest);

        authUserRpository.save(authUserEntity);

        return customerMapper.toCustomerCreatResponse(customer);

    }

    private AuthUserEntity createAuthUser(Customer customer,
                                          CustomerCreatRequest customerCreatRequest) {
        AuthUserEntity authUser = new AuthUserEntity();
        authUser.setCustomer(customer);
        authUser.setPassword(passwordEncoder.encode(customerCreatRequest.getPassword()));
        authUser.setEmail(customerCreatRequest.getEmail());
        authUser.setRole(UserRole.USER);

        return authUser;
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

        return customerRepository.save(existingCustomer);
    }

    @Override
    public void deleteCustomer(String finCode) {
        customerRepository.deleteById(finCode);
    }
}
