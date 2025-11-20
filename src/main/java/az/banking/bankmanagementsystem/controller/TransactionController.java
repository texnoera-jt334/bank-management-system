package az.banking.bankmanagementsystem.controller;

import az.banking.bankmanagementsystem.DTO.Transaction.DepositeRequest;
import az.banking.bankmanagementsystem.DTO.Transaction.DepositeResponse;
import az.banking.bankmanagementsystem.service.Transaction.TransactionDepositeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bank/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionDepositeService transactionDepositeService;
    @PostMapping("/deposit")//---> input qebul edirik
    public ResponseEntity<DepositeResponse> deposit(
            @Validated @RequestBody DepositeRequest depositRequest) {

        DepositeResponse response = transactionDepositeService.deposit(depositRequest);
        return ResponseEntity.ok(response);//input---> class-a oturmek
    }

}
