package hac.ex4spring.controllers;

import hac.ex4spring.repo.Sweet;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {

    @GetMapping("/")
    public String main(Sweet sweet) {
        return "homepage";
    }


}
