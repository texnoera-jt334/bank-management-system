package az.banking.bankmanagementsystem.entity;

import az.banking.bankmanagementsystem.enums.EmployeeRole;
import az.banking.bankmanagementsystem.enums.LoanStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "loans")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "customer_fin_code",
            referencedColumnName = "fin_code",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_loan_customer")
    )
    private Customer customer;

    @Column(nullable = false, precision = 14, scale = 2)
    private BigDecimal principal;

    @Column(name = "interest_rate", nullable = false, precision = 5, scale = 2)
    private BigDecimal interestRate;

    @Column(name = "total_amount", precision = 14, scale = 2)
    private BigDecimal totalAmount;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private LoanStatus status = LoanStatus.ACTIVE;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private LocalDate createdAt;

    @PrePersist
    public void calculateTotalAmount()
    {
        if (principal != null && interestRate != null){
            this.totalAmount = principal.add(
                    principal.multiply(interestRate).divide(BigDecimal.valueOf(100))
            );
        }
    }
}
