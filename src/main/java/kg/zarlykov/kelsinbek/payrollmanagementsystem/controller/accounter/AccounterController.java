package kg.zarlykov.kelsinbek.payrollmanagementsystem.controller.accounter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController("/accounter")
public class AccounterController {

    @GetMapping("/home")
    public String home() {
        return "accounter/home";
    }
}
