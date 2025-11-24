package az.banking.bankmanagementsystem.controller;
import az.banking.bankmanagementsystem.dto.AccountRequest;
import az.banking.bankmanagementsystem.dto.AccountResponse;
import az.banking.bankmanagementsystem.dto.AccountSimpleResponse;
import az.banking.bankmanagementsystem.service.impl.AccountServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
    @RequestMapping("/api/accounts")
    @RequiredArgsConstructor
    public class AccountController {

        private final AccountServiceImpl accountService;

        // new Hesab

        @PostMapping()
        public ResponseEntity<AccountResponse> createAccount(
                @RequestBody AccountRequest accountRequestDTO) {
             AccountResponse account = accountService.creatAccount(accountRequestDTO);
            return new ResponseEntity<>( account,HttpStatus.CREATED);
        }

       // hesab melumati
       @GetMapping("/{accountNumber}")
       public ResponseEntity<AccountSimpleResponse> getAccount(@PathVariable String accountNumber){
           AccountSimpleResponse account=accountService.getAccountByNumber(accountNumber);
           return ResponseEntity.ok(account);
       }

        //isitfadecinin butun hesablari
        @GetMapping("/customer/{finCode}")
        public ResponseEntity<List<AccountSimpleResponse>> getcustomerfin(@PathVariable String finCode) {
            List<AccountSimpleResponse>account = accountService.getCustomerAccounts(finCode);
            return ResponseEntity.ok(account);

        }





}



