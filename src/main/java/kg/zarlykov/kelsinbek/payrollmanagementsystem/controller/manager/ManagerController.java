package kg.zarlykov.kelsinbek.payrollmanagementsystem.controller.manager;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController("/manager")
public class ManagerController {

    @GetMapping("/home")
    public String home() {
        return "manager/home";
    }
}
