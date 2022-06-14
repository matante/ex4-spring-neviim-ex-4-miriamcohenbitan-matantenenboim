package hac.ex4spring.controllers;

import hac.ex4spring.repo.Payment;
import hac.ex4spring.repo.PaymentRepository;
import hac.ex4spring.repo.Sweet;
import hac.ex4spring.repo.SweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

@Controller
@RequestMapping("/admin")
public class SweetController {
    @Autowired
    private SweetRepository sweetRepository;
    private SweetRepository getSweetRepo() {
        return sweetRepository;
    }


    @Autowired
    private PaymentRepository paymentRepository;
    private PaymentRepository getPaymentRepo() {return paymentRepository;};


    @GetMapping("")
    public String main(Sweet sweet, Model model) {
        model.addAttribute("sweets", getSweetRepo().findAll());
        System.out.println("about to print payments");
        for (Payment p : getPaymentRepo().findAll()){
            System.out.println(p.getId());
            System.out.println(p.getAmount());
            System.out.println(p.getDatetime());
        }
        System.out.println("done");
        return "index";
    }

    @GetMapping("addsweet")
    public String addSweetGet(Sweet sweet, Model model) {
        return "redirect:/admin";
    }


    @PostMapping(value = "/addsweet")
    public String addSweet(@Valid Sweet sweet, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("sweets", getSweetRepo().findAll());
            return "index";
        }
        if (Objects.equals(sweet.getImageLink(), "")) {
            sweet.setImageLink("default-sweet.png");
        }
        getSweetRepo().save(sweet);
        model.addAttribute("sweets", getSweetRepo().findAll());
        return "redirect:/admin";

        // return "index";
    }

    @GetMapping("payments")
    public String payments(Model model) {
        model.addAttribute("payments", getPaymentRepo().findByOrderByDatetimeDesc());
        model.addAttribute("totalAmount", getPaymentRepo().sumByAmount());


        return "payments";
    }


    @GetMapping("delete")
    public String deleteSweetGet(Sweet sweet, Model model) {
        return "redirect:/admin";
    }

    @PostMapping("/delete")
    public String deleteSweet(@RequestParam("id") long id, Model model) {

        Sweet sweet = getSweetRepo().findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid sweet Id:" + id));
        getSweetRepo().delete(sweet);
        model.addAttribute("sweets", getSweetRepo().findAll());
        return "redirect:/admin";
    }

    @GetMapping("edit")
    public String editSweetGet(Sweet sweet, Model model) {
        return "redirect:/admin";
    }

    @PostMapping("/edit")
    public String editSweet(@RequestParam("id") long id, Model model) {

        Sweet sweet = getSweetRepo().findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid sweet Id:" + id));

        // the name "user"  is bound to the VIEW
        model.addAttribute("sweet", sweet);
        return "update-sweet";
    }

    @GetMapping("update/{id}")
    public String updateSweetGet(Sweet sweet, Model model) {
        return "redirect:/admin";
    }

    @PostMapping("/update/{id}")
    public String updateSweet(@PathVariable("id") long id, @Valid Sweet sweet, BindingResult result, Model model) {
        if (result.hasErrors()) {
            sweet.setId(id);
            return "update-sweet";
        }

        getSweetRepo().save(sweet);
        model.addAttribute("sweets", getSweetRepo().findAll());
        return "redirect:/admin";
    }



}
