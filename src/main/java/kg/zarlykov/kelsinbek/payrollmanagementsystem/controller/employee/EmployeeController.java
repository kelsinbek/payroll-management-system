package kg.zarlykov.kelsinbek.payrollmanagementsystem.controller.employee;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController("/employee")
public class EmployeeController {

    @GetMapping("home")
    public String home() {
        return "employee/home";
    }
}
