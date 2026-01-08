package az.banking.bankmanagementsystem.controller;

import az.banking.bankmanagementsystem.client.FeignTelegramClient;
import az.banking.bankmanagementsystem.client.dto.TelegramRequestDTO;
import az.banking.bankmanagementsystem.client.dto.TelegramResponseDTO;
import az.banking.bankmanagementsystem.dao.entity.Customer;
import az.banking.bankmanagementsystem.dto.CustomerCreatRequest;
import az.banking.bankmanagementsystem.dto.CustomerCreatResponse;
import az.banking.bankmanagementsystem.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;
    private  final FeignTelegramClient feignClient;
    @Value("${telegram.bot.welcome-message}")
    private String welcome;

    @PostMapping
    public ResponseEntity<CustomerCreatResponse> createCustomer(@Valid @RequestBody CustomerCreatRequest customerCreateRequest) {
        CustomerCreatResponse createdCustomer = customerService.createCustomer(customerCreateRequest);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }

    @GetMapping("/{finCode}")
    public ResponseEntity<Customer> getCustomer(@PathVariable String finCode) {
        Customer customer = customerService.getCustomerByFinCode(finCode);
        return ResponseEntity.ok(customer);
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/telegramMessengTest")
    public ResponseEntity<TelegramResponseDTO> SpringTelegramMesseng() {
        TelegramRequestDTO requestDTO =
                TelegramRequestDTO.builder()
                        .chatId("1775046847")
                        .text(welcome)
                        .parseMode("HTML")
                        .build();

        //telegrama Messenger gonderirem.

        TelegramResponseDTO responseDTO = feignClient.sendMessage(requestDTO);

        log.info(" Telegram mesajı göndərildi | chatId={} | ok={}",
                requestDTO.getChatId(), responseDTO.isOk());

        return ResponseEntity.ok(responseDTO);
    }
    @PutMapping("/{finCode}")
    public ResponseEntity<Customer> updateCustomer(
            @PathVariable String finCode,
            @RequestBody Customer updatedCustomer) {

        Customer existingCustomer = customerService.getCustomerByFinCode(finCode);

        if (updatedCustomer.getName() != null) {
            existingCustomer.setName(updatedCustomer.getName());
        }

        if (updatedCustomer.getSurname() != null) {
            existingCustomer.setSurname(updatedCustomer.getSurname());
        }

        if (updatedCustomer.getEmail() != null) {
            existingCustomer.setEmail(updatedCustomer.getEmail());
        }

        if (updatedCustomer.getPhoneNumber() != null) {
            existingCustomer.setPhoneNumber(updatedCustomer.getPhoneNumber());
        }

        if (updatedCustomer.getAddress() != null) {
            existingCustomer.setAddress(updatedCustomer.getAddress());
        }

        if (updatedCustomer.getDateOfBirth() != null) {
            existingCustomer.setDateOfBirth(updatedCustomer.getDateOfBirth());
        }

        existingCustomer.setActive(updatedCustomer.isActive());

        Customer savedCustomer = customerService.updateCustomer(finCode, existingCustomer);
        return ResponseEntity.ok(savedCustomer);
    }

    @DeleteMapping("/{finCode}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable String finCode) {
        customerService.deleteCustomer(finCode);
        return ResponseEntity.noContent().build();
    }
}

