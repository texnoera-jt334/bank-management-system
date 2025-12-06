package az.banking.bankmanagementsystem.service;

import az.banking.bankmanagementsystem.dto.DepositeResponse;
import az.banking.bankmanagementsystem.dto.DepositeRequest;
import az.banking.bankmanagementsystem.dto.WithdrawalRequest;
import az.banking.bankmanagementsystem.dto.WithdrawalResponse;
import lombok.Value;


public interface TransactionService {

    DepositeResponse deposit(DepositeRequest depositeRequest);


    WithdrawalResponse withdraw(WithdrawalRequest withdrawalRequest);

}
