package az.banking.bankmanagementsystem.client.feignerror;
import az.banking.bankmanagementsystem.client.feignerror.Mdoel.TelegramAPIError;
import az.banking.bankmanagementsystem.error.exception.TelegramAPIAnyException;
import az.banking.bankmanagementsystem.error.exception.TelegramChat_idNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;


@Slf4j
@Configuration
public class ClientErrorDecoder implements ErrorDecoder {

    private final ObjectMapper objectMapper;

    public ClientErrorDecoder(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Exception decode(String methodKey, Response response) {
        try (InputStream body = response.body().asInputStream()) {
            TelegramAPIError responseBody = objectMapper.readValue(body,TelegramAPIError.class);
            switch (response.status()) {
                case 404 -> {
                    return new TelegramChat_idNotFoundException(responseBody.getMessage());
                }
                case 400 -> {
                    return new BadRequestException(responseBody.getMessage());
                }
                default -> {
                    return new TelegramAPIAnyException(responseBody.getMessage());
                }
            }
        } catch (Exception ex) {
            log.error("Exception occurs while parsing client error response: {}", response, ex);
            return new TelegramAPIAnyException("Internal error happened while parsing client error model");
        }
    }
}
