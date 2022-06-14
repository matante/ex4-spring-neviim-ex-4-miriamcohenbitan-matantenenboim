package hac.ex4spring.controllers;

import hac.ex4spring.repo.*;
import hac.ex4spring.services.SweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;


@Controller
public class MainController {

    private boolean purchaseSucceed = false;

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

    ;

    @Autowired
    private SweetService sweetService;

    @Resource(name = "sessionCart")
    private Cart cart;

    @GetMapping("/")
    public String main(Sweet sweet, Model model, HttpServletRequest request) {

        model.addAttribute("sweets", getSweetRepo().findAll());
        model.addAttribute("top5", getSweetRepo().findFirst5ByOrderByDiscountDesc());
        model.addAttribute("cart", cart);
        model.addAttribute("purchaseSucceed", purchaseSucceed);
        purchaseSucceed = false;

        return "homepage";
    }


    @GetMapping("/searchResults")
    public String searchResults(@RequestParam("name") String name, Model model) {
        System.out.println("in func");
        System.out.println(name);
        model.addAttribute("cart", cart);
        model.addAttribute("searchResults", getSweetRepo().findBySweetNameContains(name));

        return "search-results";


    }

    @PostMapping("/complete")
    public String complete(Model model) {

        try {
            sweetService.reduceSweetsQuantities();
            double amount = cart.getTotalPrice();

            if (amount <= 0) {
                throw new IllegalArgumentException("Sum can't be 0");
            }
            Date date = new Date();
            Payment payment = new Payment();
            payment.setDatetime(date);
            payment.setAmount(amount);

            paymentRepository.save(payment);

            cart.empty();

            purchaseSucceed = true;
        } catch (IllegalArgumentException e) {
            model.addAttribute("allSweetsInCart", cart.getSweetMap().keySet());
            model.addAttribute("cart", cart);
            model.addAttribute("error", e.getMessage());
            purchaseSucceed = false;
            return "cart";

        }finally{
            int k;
        }

        return "redirect:/";
    }


}
