package az.banking.bankmanagementsystem.mapper;

import az.banking.bankmanagementsystem.dao.entity.Transaction;
import az.banking.bankmanagementsystem.dto.DepositeRequest;
import az.banking.bankmanagementsystem.dto.DepositeResponse;
import az.banking.bankmanagementsystem.dto.WithdrawalRequest;
import az.banking.bankmanagementsystem.dto.WithdrawalResponse;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;

    @Mapper(componentModel = "spring")
    public interface TransactionMapper {
        TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);
        // Request  Transaction Entity
        @Mapping(target = "id", ignore = true)//avtomatik artir
        @Mapping(target = "account", ignore = true) // Service'də set olunur
        @Mapping(target = "transactionType", constant = "DEPOSIT")
        //@Mapping(target = "relatedAccount", constant = "null")
        @Mapping(target = "referenceNumber", ignore = true) // Service'də generate olunur
        @Mapping(target = "createdAt", ignore = true) // Service'də set olunur
        @Mapping(target = "description", source = "description")
        Transaction toTransactionEntity(DepositeRequest depositeRequest);


        @Mapping(target = "message", constant = "Deposit əməliyyatı uğurla tamamlandı")
        @Mapping(target = "transactionId", source = "transaction.id")
        @Mapping(target = "referenceNumber", source = "transaction.referenceNumber")
        @Mapping(target = "newBalance", source = "newBalance")
        @Mapping(target = "timestamp", expression = "java(java.time.LocalDateTime.now())")
        DepositeResponse toDepositeResponse(Transaction transaction, BigDecimal newBalance);



        @Mapping(target = "id", ignore = true)
        @Mapping(target = "account", ignore = true)
        @Mapping(target = "transactionType", constant = "WITHDRAWAL")
        @Mapping(target = "relatedAccount", ignore = true)
        @Mapping(target = "referenceNumber", ignore = true)
        @Mapping(target = "createdAt", ignore = true)
        Transaction toWithdrawalTransactionEntity(WithdrawalRequest withdrawalRequest);

        @Mapping(target = "message", constant = "Withdrawal əməliyyatı uğurla tamamlandı")
        @Mapping(target = "transactionId", source = "transaction.id")
        @Mapping(target = "referenceNumber", source = "transaction.referenceNumber")
        @Mapping(target = "newBalance", source = "newBalance")
        @Mapping(target = "timestamp", expression = "java(java.time.LocalDateTime.now())")
        WithdrawalResponse toWithdrawalResponse(Transaction transaction, BigDecimal newBalance);




    }
