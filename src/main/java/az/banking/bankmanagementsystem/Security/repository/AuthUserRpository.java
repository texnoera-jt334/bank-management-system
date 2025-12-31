package az.banking.bankmanagementsystem.Security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface AuthUserRpository extends JpaRepository<AuthUserEntity, String> {


    Optional<AuthUserEntity> findByCustomer_FinCode(String customerFinCode);

}
