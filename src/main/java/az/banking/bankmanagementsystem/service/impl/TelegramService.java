package az.banking.bankmanagementsystem.service.impl;

import az.banking.bankmanagementsystem.Security.repository.AuthUserRpository;
import az.banking.bankmanagementsystem.client.FeignTelegramClient;
import az.banking.bankmanagementsystem.client.dto.TelegramRequestDTO;
import az.banking.bankmanagementsystem.client.dto.TelegramResponseDTO;
import az.banking.bankmanagementsystem.dao.TelegramTokenCasheRepository;
import az.banking.bankmanagementsystem.error.exception.telegramWebhookException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class TelegramService {

    FeignTelegramClient feignClient;
    AuthUserRpository authUserRpository;
    TelegramTokenCasheRepository telegramTokenCasheRepository;
    @Value("${telegram.bot.welcome-message}")
    private String welcome;


    public void     sendMessage(Map<String, Object> update ) {

        try {
            // Telegram mesajını al
            log.info("telegramdan data geldi --> {}",update );

            Map<String, Object> message = (Map<String, Object>) update.get("message");
            if (message == null) {
                throw  new telegramWebhookException("mesaj null gele bilmez");

            }
            //token Authentication
            String payload = (String) message.get("text");

            Long authId = telegramTokenCasheRepository.consume(payload);

            if (authId == null) {
                log.warn("Token expired or invalid");
                throw  new telegramWebhookException("Token expired or invalid !!!");
            }

            //  Chat ID al
            Map<String, Object> chat = (Map<String, Object>) message.get("chat");
            if (chat == null || chat.get("id") == null) {
                throw  new telegramWebhookException("Chat or chatId null, ignore update");
            }
            String chatId = String.valueOf(chat.get("id"));

            authUserRpository.updateTelegramChatId(authId,chatId);

            log.info("telegram_chat_id DB yazildi.");

            TelegramRequestDTO requestDTO =
                    TelegramRequestDTO.builder()
                            .chatId(chatId)
                            .text(welcome)
                            .parseMode("HTML")
                            .build();

            //telegrama Messenger gonderirem.

            TelegramResponseDTO responseDTO = feignClient.sendMessage(requestDTO);

            log.info(" Telegram mesajı göndərildi | chatId={} | ok={}",
                    requestDTO.getChatId(), responseDTO.isOk());

        } catch (telegramWebhookException e){
            System.out.println("Telegram Prossesing Error");
        }



    }


}
