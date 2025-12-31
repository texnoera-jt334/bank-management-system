package az.banking.bankmanagementsystem.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerCreatResponse{

        private String customerId;

        private String fullName;

        private boolean isActive;

        private String nextMessenger;      // "CREATE_ACCOUNT"

        private LocalDateTime createdAt;

}
