package az.banking.bankmanagementsystem.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/telegram")
public class TelegramController {

   @PostMapping("/data")
    public void telegramdata(@RequestBody String data){

       System.out.println("Teelgramdan datya geldi "+  data);

    }


}
