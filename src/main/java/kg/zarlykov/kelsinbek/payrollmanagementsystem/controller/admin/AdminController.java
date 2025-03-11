package kg.zarlykov.kelsinbek.payrollmanagementsystem.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController("/admin")
public class AdminController {

    @GetMapping("/home")
    public String home() {
        return "accounter/home";
    }
}
