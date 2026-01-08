package az.banking.bankmanagementsystem.service.impl;

import az.banking.bankmanagementsystem.Security.model.Enum.UserRole;
import az.banking.bankmanagementsystem.Security.repository.AuthUserEntity;
import az.banking.bankmanagementsystem.Security.repository.AuthUserRpository;
import az.banking.bankmanagementsystem.dao.TelegramTokenCasheRepository;
import az.banking.bankmanagementsystem.dao.entity.Customer;
import az.banking.bankmanagementsystem.dto.CustomerCreatRequest;
import az.banking.bankmanagementsystem.dto.CustomerCreatResponse;
import az.banking.bankmanagementsystem.error.exception.CustomerAlreadyExistsException;
import az.banking.bankmanagementsystem.error.exception.CustomerNotFoundException;
import az.banking.bankmanagementsystem.mapper.CustomerMapper;
import az.banking.bankmanagementsystem.repository.CustomerRepository;
import az.banking.bankmanagementsystem.service.CustomerService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private  final CustomerMapper customerMapper;
     private  final AuthUserRpository  authUserRpository;
     private final TelegramTokenCasheRepository telegramTokenCasheRepository;
    private static final SecureRandom secureRandom =new SecureRandom();
    @Value("${telegram.bot.username}")
    private String botUsername;

    @Override
    public CustomerCreatResponse createCustomer(CustomerCreatRequest customerCreatRequest) {
         log.info(" Yeni istifadəçi yaradılır: {}",customerCreatRequest.getFinCode());

        if (customerRepository.existsById(customerCreatRequest.getFinCode())) {
            throw new CustomerAlreadyExistsException("Bu FIN kodu ilə müştəri artıq mövcuddur: " + customerCreatRequest.getFinCode());
        }
        if (customerRepository.existsByPhoneNumber((customerCreatRequest.getPhoneNumber()))) {
            throw new RuntimeException("Bu telefon nömrəsi artıq qeydiyyatdan keçib");
        }
        if (customerRepository.existsByEmail((customerCreatRequest.getEmail()))){
            throw new RuntimeException("Bu Email artıq mövcuddur");
        }

         Customer customer = customerMapper.toCustomerEntity(customerCreatRequest);

        customerRepository.save(customer);
        log.info(" Customer hesab DB yazildi : {}",customerCreatRequest.getFinCode());

        AuthUserEntity authUserEntity = createAuthUser(customer, customerCreatRequest);

        authUserRpository.save(authUserEntity);

        log.info(" AuthUserEnity DB yazildi : {}",authUserEntity.getId());

        //Response da linki otrecik ve isitfadeci linke daxil olanda biz isitfadecinin telegramID sini alaciq:
        String telegramLink = generateTelegramLink(authUserEntity.getId());
        CustomerCreatResponse customerCreatResponse = customerMapper.toCustomerCreatResponse(customer);
        customerCreatResponse.setTelegramLink(telegramLink) ;
        customerCreatResponse.setInstructions(" Linkə klik edin\\n2. Telegram bot sizinlə əlaqə quracaq");
        return customerCreatResponse;
    }

    private AuthUserEntity createAuthUser(Customer customer,
                                          CustomerCreatRequest customerCreatRequest) {
        AuthUserEntity authUser = new AuthUserEntity();
        authUser.setCustomer(customer);
        authUser.setPassword(passwordEncoder.encode(customerCreatRequest.getPassword()));
        authUser.setEmail(customerCreatRequest.getEmail());
        authUser.setRole(UserRole.USER);

        return authUser;
    }

        public static String generateToken() {
            byte[] randomBytes = new byte[30]; // ~40 simvol Base64
            secureRandom.nextBytes(randomBytes);
            return Base64.getUrlEncoder()
                    .withoutPadding()
                    .encodeToString(randomBytes);
        }



    private String generateTelegramLink(Long authid) {
        String token = generateToken();
        telegramTokenCasheRepository.save(generateToken(),authid);
        return "https://t.me/" + botUsername + "?start=" + token;
    }

    @Override
    public Customer getCustomerByFinCode(String finCode) {
        return customerRepository.findById(finCode)
                .orElseThrow(() -> new CustomerNotFoundException("Müştəri tapılmadı. FIN: " + finCode));
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer updateCustomer(String finCode, Customer updatedCustomer) {
        Customer existingCustomer = customerRepository.findById(finCode)
                .orElseThrow(() -> new CustomerNotFoundException("Müştəri tapılmadı. FIN: " + finCode));

        existingCustomer.setName(updatedCustomer.getName());
        existingCustomer.setSurname(updatedCustomer.getSurname());
        existingCustomer.setEmail(updatedCustomer.getEmail());
        existingCustomer.setPhoneNumber(updatedCustomer.getPhoneNumber());
        existingCustomer.setAddress(updatedCustomer.getAddress());
        existingCustomer.setDateOfBirth(updatedCustomer.getDateOfBirth());
        existingCustomer.setActive(updatedCustomer.isActive());

        return customerRepository.save(existingCustomer);
    }

    @Override
    public void deleteCustomer(String finCode) {
        customerRepository.deleteById(finCode);
    }
}
