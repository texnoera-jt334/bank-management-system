package az.banking.bankmanagementsystem.repository;

import az.banking.bankmanagementsystem.entity.Transaction;
import az.banking.bankmanagementsystem.enums.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    // Hesab nömrəsinə görə bütün tranzaksiyalar
    List<Transaction> findByAccountAccountNumber(String accountNumber);

    // Referans nömrəsinə görə tap
    Optional<Transaction> findByReferenceNumber(String referenceNumber);

    // Deposit tipli tranzaksiyalar
    List<Transaction> findByAccountAccountNumberAndTransactionType(
            String accountNumber, TransactionType transactionType);



}

