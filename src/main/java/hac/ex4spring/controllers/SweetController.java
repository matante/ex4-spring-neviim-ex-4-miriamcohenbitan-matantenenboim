package hac.ex4spring.controllers;

import hac.ex4spring.repo.Sweet;
import hac.ex4spring.repo.SweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class SweetController {
    @Autowired
    private SweetRepository repository;
    private SweetRepository getRepo() {
        return repository;
    }

    @GetMapping("")
    public String main(Sweet sweet, Model model) {
        model.addAttribute("sweets", getRepo().findAll());
        return "index";
    }

    @GetMapping("addsweet")
    public String addSweetGet( Model model){
//        RedirectView redirectView = new RedirectView();
//        redirectView.setUrl("/");
//        return redirectView;
        return "redirect:/admin";
    }


    @PostMapping("/addsweet")
    public String addSweet(@Valid Sweet sweet, Model model, BindingResult result) {
        if (result.hasErrors()){
            model.addAttribute("sweets", getRepo().findAll());
            System.out.println("in error");
            return "index";
        }
        getRepo().save(sweet);
        model.addAttribute("sweets", getRepo().findAll());

        return "index";
    }

    @PostMapping("/delete")
    public String deleteSweet(@RequestParam("id") long id, Model model) {

        Sweet sweet = getRepo()
                .findById(id)
                .orElseThrow(
                        () -> new IllegalArgumentException("Invalid sweet Id:" + id)
                );
        getRepo().delete(sweet);
        model.addAttribute("sweets", getRepo().findAll());
        return "redirect:/admin";
    }
}
