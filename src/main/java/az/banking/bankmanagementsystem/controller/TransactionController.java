package az.banking.bankmanagementsystem.controller;

import az.banking.bankmanagementsystem.dto.DepositeRequest;
import az.banking.bankmanagementsystem.dto.DepositeResponse;
import az.banking.bankmanagementsystem.dto.WithdrawalRequest;
import az.banking.bankmanagementsystem.dto.WithdrawalResponse;
import az.banking.bankmanagementsystem.service.impl.TransactionServiceImpl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bank/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionServiceImpl transactionServiceImpl;

    @PostMapping("/deposit")//---> input qebul edirik
    public ResponseEntity<DepositeResponse> deposit(
            @Valid @RequestBody DepositeRequest depositRequest) {

        DepositeResponse response = transactionServiceImpl.deposit(depositRequest);
        return ResponseEntity.ok(response);//input---> class-a oturmek
    }

    @PostMapping("/withdraw")
    public ResponseEntity<WithdrawalResponse> withdraw(@Valid @RequestBody WithdrawalRequest withdrawalRequest) {
        WithdrawalResponse response = transactionServiceImpl.withdraw(withdrawalRequest);
        return ResponseEntity.ok(response);
    }





}