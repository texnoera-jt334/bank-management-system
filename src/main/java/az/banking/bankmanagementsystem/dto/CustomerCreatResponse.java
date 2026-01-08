package az.banking.bankmanagementsystem.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerCreatResponse{

        private String customerId;
        private String fullName;
        private boolean isActive;
        private String nextMessenger;      // "CREATE_ACCOUNT"
        private LocalDateTime createdAt;
        private String telegramLink;     // https://t.me/mybank_bot?start=123456
        private String instructions;     // Linkə klik edin

}
