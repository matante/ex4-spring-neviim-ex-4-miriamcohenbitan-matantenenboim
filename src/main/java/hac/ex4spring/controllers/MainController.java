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
import java.security.Principal;
import java.util.Date;


@Controller
public class MainController {

    //***********
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

    @Autowired
    private SweetService sweetService;

    //***********
    @Resource(name = "sessionCart")
    private Cart cart;
    //***********

    @GetMapping({"/", "/complete"})
    public String main(Model model) {

        model.addAttribute("sweets", getSweetRepo().findAll());
        model.addAttribute("top5", getSweetRepo().findFirst5ByOrderByDiscountDesc());
        model.addAttribute("cart", cart);
        model.addAttribute("purchaseSucceed", purchaseSucceed);
        purchaseSucceed = false; // after sent, reset back to false

        return "homepage";
    }


    /**
     * handle redirections after log in according to role (if regular user, according to cart)
     * @param request request
     * @return html
     */
    @GetMapping("/logged")
    public String logged(HttpServletRequest request) {

        if (request.isUserInRole("ADMIN")) {
            return "redirect:/admin/";
        }
        if (cart.getNumOfItems() == 0){ // go add items!! make us rich ☻
            return "redirect:/";
        }
        return "redirect:/cart/checkout";
    }

    /**
     * for each sweet in the db which name contains substring matching to the name, show it in the page
     *
     * @param name  the name to search for in the DB
     * @param model model
     * @return html page with results
     */
    @GetMapping("/searchResults")
    public String searchResults(@RequestParam("name") String name, Model model) {
        model.addAttribute("cart", cart);
        model.addAttribute("searchResults", getSweetRepo().findBySweetNameContains(name));

        return "search-results";
    }


    /**
     * we reach here after the user press Pay, we make sure the quantities in the sweets db matching to the order.
     * we add a new payment to the db
     * if something fails, go back to the cart and show the error
     *
     * @param model model
     * @return html page according to the calculation
     */
    @PostMapping("/complete")
    public String complete(Model model, Principal principal) {

        try {
            sweetService.reduceSweetsQuantities(); // may throw an exception
            double amount = cart.getTotalPrice();

            Date date = new Date(); // current date
            Payment payment = new Payment();
            payment.setDatetime(date);

            payment.setAmount(amount); // may throw exception if <= 0

            payment.setUsername(principal.getName());

            getPaymentRepo().save(payment);

            cart.empty();

            purchaseSucceed = true;
        } catch (IllegalArgumentException e) {
            model.addAttribute("allSweetsInCart", cart.getSweetsTable().keySet());
            model.addAttribute("cart", cart);
            model.addAttribute("top5", getSweetRepo().findFirst5ByOrderByDiscountDesc());
            model.addAttribute("error", e.getMessage());
            purchaseSucceed = false;
            return "cart";
        }

        return "redirect:/"; // succeed, go back to homepage with purchaseSucceed = true
    }

}
