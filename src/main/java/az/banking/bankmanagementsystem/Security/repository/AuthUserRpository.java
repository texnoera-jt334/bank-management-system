package az.banking.bankmanagementsystem.Security.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface AuthUserRpository extends JpaRepository<AuthUserEntity, String> {


    Optional<AuthUserEntity> findByCustomer_FinCode(String customerFinCode);

    Optional<AuthUserEntity>findById(Long id);

    @Modifying
    @Transactional
    @Query("""
        UPDATE AuthUserEntity a
        SET a.telegramChatId = :chatId
        WHERE a.id = :authId
    """)
    void updateTelegramChatId(
            @Param("authId") Long authId,
            @Param("chatId") String chatId
    );
}


