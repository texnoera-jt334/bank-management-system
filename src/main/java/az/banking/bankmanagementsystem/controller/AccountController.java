package az.banking.bankmanagementsystem.controller;


import az.banking.bankmanagementsystem.DTO.Acount.AccountRequest;
import az.banking.bankmanagementsystem.DTO.Acount.AccountResponse;
import az.banking.bankmanagementsystem.entity.Account;
import az.banking.bankmanagementsystem.service.Account.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
    @RequestMapping("/api/accounts")
    @RequiredArgsConstructor
    public class AccountController {

        private final AccountService accountService;

        // new Hesab

        @PostMapping()
        public ResponseEntity<AccountResponse> createAccount(
                @RequestBody AccountRequest accountRequestDTO) {
             AccountResponse account = accountService.creatAccount(accountRequestDTO);
            return new ResponseEntity<>( account,HttpStatus.CREATED);
        }

       // hesab melumati

       /* @GetMapping("/{accountNumber}")
        public ResponseEntity<AccountResponse> getAccount(@PathVariable String accountNumber) {
            Account account = accountService.getAccountByNumber(accountNumber);
            return ResponseEntity.ok(mapToResponse(account));*/


        @GetMapping("/{accountNumber}")
        public ResponseEntity< Account> getAccount(@PathVariable String accountNumber){
         Account account=accountService.getAccountByNumber(accountNumber);
         return ResponseEntity.ok(account);


        }}



