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
import java.util.Objects;

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
    public String addSweetGet(Sweet sweet, Model model){
        return "redirect:/admin";
    }


    @PostMapping(value = "/addsweet")
    public String addSweet(@Valid Sweet sweet, BindingResult result, Model model) {
        if (result.hasErrors()){
            model.addAttribute("sweets", getRepo().findAll());
            return "index";
        }
        if (Objects.equals(sweet.getImageLink(), "")){
            sweet.setImageLink("default-sweet.png");
        }
        getRepo().save(sweet);
        model.addAttribute("sweets", getRepo().findAll());
        return "redirect:/admin";

       // return "index";
    }

    @GetMapping("delete")
    public String deleteSweetGet(Sweet sweet, Model model){
        return "redirect:/admin";
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

    @GetMapping("edit")
    public String editSweetGet(Sweet sweet, Model model){
        return "redirect:/admin";
    }
    @PostMapping("/edit")
    public String editSweet(@RequestParam("id") long id, Model model) {

        Sweet sweet = getRepo().findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid sweet Id:" + id));

        // the name "user"  is bound to the VIEW
        model.addAttribute("sweet", sweet);
        return "update-sweet";
    }

    @GetMapping("update/{id}")
    public String updateSweetGet(Sweet sweet, Model model){
        return "redirect:/admin";
    }
    @PostMapping("/update/{id}")
    public String updateSweet(@PathVariable("id") long id, @Valid Sweet sweet, BindingResult result, Model model) {
        if (result.hasErrors()) {
            sweet.setId(id);
            return "update-sweet";
        }

        getRepo().save(sweet);
        model.addAttribute("sweets", getRepo().findAll());
        return "redirect:/admin";
    }

}
