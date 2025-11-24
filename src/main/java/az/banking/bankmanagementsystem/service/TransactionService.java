package az.banking.bankmanagementsystem.service;

import az.banking.bankmanagementsystem.dto.DepositeResponse;
import az.banking.bankmanagementsystem.dto.DepositeRequest;


public interface TransactionService {

    DepositeResponse deposit(DepositeRequest depositeRequestDTO);

}
