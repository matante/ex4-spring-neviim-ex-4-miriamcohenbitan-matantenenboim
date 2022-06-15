package hac.ex4spring.controllers;

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
    private PaymentRepository getPaymentRepo() {
        return paymentRepository;
    }

    @GetMapping("")
    public String main(Sweet sweet, Model model) {
        model.addAttribute("sweets", getSweetRepo().findAll());
        return "admin";
    }

    @GetMapping("addsweet")
    public String addSweetGet(Sweet sweet, Model model) {
        return "redirect:/admin";
    }


    /**
     * add a new sweet to the DB
     * @param sweet a Sweet
     * @param result BindingResult
     * @param model model
     * @return html
     */
    @PostMapping(value = "/addsweet")
    public String addSweet(@Valid Sweet sweet, BindingResult result, Model model) {
        if (result.hasErrors()) { // if validation failed
            model.addAttribute("sweets", getSweetRepo().findAll());
            return "admin";
        }
        if (Objects.equals(sweet.getImageLink(), "")) { // no link
            sweet.setImageLink("default-sweet.png");
        }
        getSweetRepo().save(sweet); // add
        model.addAttribute("sweets", getSweetRepo().findAll());
        return "redirect:/admin";

    }

    /**
     * a page to see all payments
     * @param model model
     * @return html
     */
    @GetMapping("payments")
    public String payments(Model model) {
        model.addAttribute("payments", getPaymentRepo().findByOrderByDatetimeDesc());
        Double amount = getPaymentRepo().sumByAmount(); // query may return null if no payments in db
        model.addAttribute("totalAmount", amount != null ? amount : 0);

        return "payments";
    }


    @GetMapping("delete")
    public String deleteSweetGet(Sweet sweet, Model model) {
        return "redirect:/admin";
    }

    /**
     * delete sweet from the db
     * @param id id
     * @param model model
     * @return html
     */
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

    /**
     * edit an existing sweet in the db
     * @param id id
     * @param model model
     * @return html
     */
    @PostMapping("/edit")
    public String editSweet(@RequestParam("id") long id, Model model) {

        Sweet sweet = getSweetRepo().findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid sweet Id:" + id));

        model.addAttribute("sweet", sweet);
        return "update-sweet";
    }

    @GetMapping("update/{id}")
    public String updateSweetGet(Sweet sweet, Model model) {
        return "redirect:/admin";
    }

    /**
     * after the admin finished editing the sweet, s/he need to update in the db
     * @param id id
     * @param sweet sweet to edit
     * @param result result
     * @param model model
     * @return html
     */
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
