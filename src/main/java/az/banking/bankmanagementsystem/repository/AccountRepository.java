package az.banking.bankmanagementsystem.repository;

import az.banking.bankmanagementsystem.entity.Account;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

    Optional<Account> findByAccountNumber(String accountNumber);

    @Query("SELECT a FROM Account a WHERE a.accountNumber = :accountNumber AND a.status = 'Active'")
    Optional<Account> findActiveAccountByNumber(@Param("accountNumber") String accountNumber);


    boolean existsByAccountNumber(String accountNumber);

}