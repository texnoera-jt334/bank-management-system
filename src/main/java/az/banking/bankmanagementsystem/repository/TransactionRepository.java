package az.banking.bankmanagementsystem.repository;


import az.banking.bankmanagementsystem.dao.entity.Transaction;
import az.banking.bankmanagementsystem.enums.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    // Günlük withdrawal cəmini hesabla
    @Query("SELECT COALESCE(SUM(t.amount), 0) FROM Transaction t WHERE " +
            "t.account.accountNumber = :accountNumber AND " +
            "t.transactionType = az.banking.bankmanagementsystem.enums.TransactionType.WITHDRAWAL AND " +
            "t.createdAt BETWEEN :startTime AND :endTime")
    BigDecimal findWithdrawalTotalInPeriod(
            @Param("accountNumber") String accountNumber,
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime);


    // Hesab nömrəsinə görə bütün tranzaksiyalar
    List<Transaction> findByAccountAccountNumber(String accountNumber);

    // Referans nömrəsinə görə tap
    Optional<Transaction> findByReferenceNumber(String referenceNumber);

    // Deposit tipli tranzaksiyalar
    List<Transaction> findByAccountAccountNumberAndTransactionType(
            String accountNumber, TransactionType transactionType);



}

