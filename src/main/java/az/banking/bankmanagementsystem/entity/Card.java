package az.banking.bankmanagementsystem.entity;

import az.banking.bankmanagementsystem.enums.CardStatus;
import az.banking.bankmanagementsystem.enums.CardType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@Table(name = "cards")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Card {

    @Id
    @Column(name = "card_number", nullable = false, unique = true, length = 16)
    private String cardNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_number", referencedColumnName = "account_number", nullable = false,
            foreignKey = @ForeignKey(name = "fk_card_account"))
    private Account account;

    @Column(name = "expiry_date", nullable = false)
    private LocalDate expiryDate;

    @Column(length = 4, nullable = false)
    private String cvv;

    @Enumerated(EnumType.STRING)
    @Column(name = "card_type", nullable = false, length = 20)
    private CardType cardType;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private CardStatus status = CardStatus.ACTIVE;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private LocalDate createdAt;
}
