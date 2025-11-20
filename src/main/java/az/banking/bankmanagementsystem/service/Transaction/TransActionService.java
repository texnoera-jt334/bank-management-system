package az.banking.bankmanagementsystem.service.Transaction;

import az.banking.bankmanagementsystem.DTO.Transaction.DepositeResponse;
import az.banking.bankmanagementsystem.DTO.Transaction.DepositeRequest;
import org.springframework.stereotype.Service;

@Service
public interface TransActionService {

    DepositeResponse deposit(DepositeRequest depositeRequestDTO);


}
