package az.banking.bankmanagementsystem.repository;

import az.banking.bankmanagementsystem.dto.AccountSimpleResponse;
import az.banking.bankmanagementsystem.entity.Account;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

    //*Musteriye melumat mueyyen datalari  vermek ucun
    @Query("select new az.banking.bankmanagementsystem.dto.AccountSimpleResponse(" +
            "a.accountNumber,a.balance,a.accountType," +
            "a.currency,a.status)"+"FROM Account a where a.accountNumber=:accountnumber")
    Optional<AccountSimpleResponse> findByAccountNumberforUser(@Param("accountnumber") String accountNumber);


   @Query("SELECT new az.banking.bankmanagementsystem.dto.AccountSimpleResponse (" +
           "a.accountNumber,a.balance,a.accountType," +
           " a.currency,a.status)"+"FROM Account a where a.customer.finCode=:customerFincode")
   Optional <List<AccountSimpleResponse>> findByCustomerFinCode(@Param("customerFincode") String customerFinCode);

    @Query("SELECT a FROM Account a WHERE a.accountNumber = :accountNumber AND a.status = 'Active'")
    Optional<Account> findActiveAccountByNumber(@Param("accountNumber") String accountNumber);

    boolean existsByAccountNumber(
            @NotBlank(message = "Hesab nomresi daxil edin")
            @Size(min = 20,max = 20,message = "Yalniz 20 reqemli olmaldir.")
            String accountNumber);

    //Transaction ucun
    Optional<Account> findByAccountNumber( String accountNumber);


}