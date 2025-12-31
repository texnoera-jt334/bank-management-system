package az.banking.bankmanagementsystem.repository;

import az.banking.bankmanagementsystem.dao.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByEmail(String email);


}
