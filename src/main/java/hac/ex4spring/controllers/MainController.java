package hac.ex4spring.controllers;

import hac.ex4spring.repo.Candy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class MainController {
    @GetMapping("/")
    public String main(Candy candy) {
        return "index";
    }

    @PostMapping("/addcandy")
    public String addCandy(@Valid Candy candy, Model model, BindingResult result) {
        if (result.hasErrors()){
            System.out.println("in error");
            return "index";
        }
        return "index";
    }
}
