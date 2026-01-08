package az.banking.bankmanagementsystem.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TelegramRequestDTO {

    @JsonProperty("chat_id")
    private String chatId;

    private String text;

    @JsonProperty("parse_mode")
    private String parseMode;
}
