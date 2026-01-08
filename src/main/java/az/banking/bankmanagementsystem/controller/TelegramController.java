package az.banking.bankmanagementsystem.controller;

import az.banking.bankmanagementsystem.Security.repository.AuthUserRpository;
import az.banking.bankmanagementsystem.error.exception.telegramWebhookException;
import az.banking.bankmanagementsystem.service.impl.TelegramService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static java.awt.SystemColor.text;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/telegram")
public class TelegramController {

    TelegramService telegramService;

    //istifadeci linke basanda telgram bu endpointe sorgu gondercek.
   @PostMapping("/webhook")
    public void telegramdata(@RequestBody Map<String, Object> update) {

       telegramService.sendMessage(update);


}
}
