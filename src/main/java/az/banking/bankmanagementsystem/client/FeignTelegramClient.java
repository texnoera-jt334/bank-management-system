package az.banking.bankmanagementsystem.client;

import az.banking.bankmanagementsystem.client.dto.TelegramRequestDTO;
import az.banking.bankmanagementsystem.client.dto.TelegramResponseDTO;
import az.banking.bankmanagementsystem.client.feignerror.ClientErrorDecoder;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.codec.ErrorDecoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "telegramClient",
        url = "https://api.telegram.org/bot${telegram.bot.token}"
)
    public interface FeignTelegramClient {

    //burada men sorgu gonerirem . Telegram API

    @PostMapping("/sendMessage")
    TelegramResponseDTO sendMessage(@RequestBody TelegramRequestDTO request);

    class FeignConfiguration {
        @Bean
        public ErrorDecoder errorDecoder(ObjectMapper objectMapper) {
            return new ClientErrorDecoder(objectMapper);
        }
    }
}