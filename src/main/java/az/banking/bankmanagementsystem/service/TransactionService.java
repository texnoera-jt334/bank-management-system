package az.banking.bankmanagementsystem.service;

import az.banking.bankmanagementsystem.DTO.DepositeResponse;
import az.banking.bankmanagementsystem.DTO.DepositeRequest;
import org.springframework.stereotype.Service;

@Service
public interface TransactionService {

    DepositeResponse deposit(DepositeRequest depositeRequestDTO);


}
