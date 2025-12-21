package az.banking.bankmanagementsystem.MVC.Controller;


import az.banking.bankmanagementsystem.dto.DepositeRequest;
import az.banking.bankmanagementsystem.dto.DepositeResponse;
import az.banking.bankmanagementsystem.service.impl.TransactionServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/xidmetler")
@RequiredArgsConstructor
public class XidmetlerController {

    private final TransactionServiceImpl transactionServiceImpl;

    // Deposit səhifəsini açır
    @GetMapping("/deposit")
    public String depositPage(Model model) {
        model.addAttribute("depositeRequest", new DepositeRequest());
        return "deposit";
    }

    // Form submit
    @PostMapping("/deposit")
    public String depositSubmit(
            @Valid @ModelAttribute("depositeRequest") DepositeRequest request,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            return "deposit"; // validation error → eyni səhifə
        }

        DepositeResponse response = transactionServiceImpl.deposit(request);
        model.addAttribute("response", response);

        return "deposit-result";
    }
}
