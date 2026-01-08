package az.banking.bankmanagementsystem.client.feignerror.Mdoel;

import lombok.Data;

@Data
public class TelegramAPIError {

    private Integer code;
    private String message;

}
