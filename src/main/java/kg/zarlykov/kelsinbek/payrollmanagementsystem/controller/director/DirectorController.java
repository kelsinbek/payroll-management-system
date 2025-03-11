package kg.zarlykov.kelsinbek.payrollmanagementsystem.controller.director;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController("/director")
public class DirectorController {

    @GetMapping()
    public String home() {
        return "director/home";
    }
}
