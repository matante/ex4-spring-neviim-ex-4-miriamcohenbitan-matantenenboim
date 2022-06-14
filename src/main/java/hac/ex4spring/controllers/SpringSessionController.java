package hac.ex4spring.controllers;

import hac.ex4spring.repo.Cart;
import hac.ex4spring.repo.Sweet;

import hac.ex4spring.repo.SweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class SpringSessionController {

    @Autowired
    private SweetRepository repository;

    private SweetRepository getRepo() {
        return repository;
    }


    @Resource(name = "sessionCart")
    private Cart cart;

    @GetMapping("/cart")
    public String process(Model model) {

        model.addAttribute("allSweetsInCart", cart.getSweetMap().keySet());
        model.addAttribute("cart", cart);
        model.addAttribute("error", "");

        return "cart";
    }

    /* without SPRING injection  it would look like this:
    @PostMapping("/persistMessage")
    public String persistMessage(@RequestParam("msg") String msg, HttpServletRequest request) {
        List<String> messages = (List<String>) request.getSession().getAttribute("MY_SESSION_MESSAGES");
        if (messages == null) {
            messages = new ArrayList<>();
            request.getSession().setAttribute("MY_SESSION_MESSAGES", messages);
        }
        messages.add(msg);
        request.getSession().setAttribute("MY_SESSION_MESSAGES", messages);
        return "redirect:/session";
    }
    */


    @GetMapping("/cart/checkout")
    public String checkout(Model model) {


        model.addAttribute("allSweetsInCart", cart.getSweetMap().keySet());
        model.addAttribute("cart", cart);

        return "checkout";
    }
    @PostMapping("/cart/emptyCart")
    public String emptyCart() {
        cart.empty();
        return "redirect:/cart";
    }

    @PostMapping("/cart/delete")
    public String deleteSweet(@RequestParam("id") long id, Model model) {
        cart.removeItemFromCart(id);
        return "redirect:/cart";
    }

    @PostMapping("/cart/update")
    public String updateSweet(@RequestParam("id") long id, @RequestParam("quantity") Integer quantity, Model model) {

        if (quantity == 0) cart.removeItemFromCart(id);
        else cart.setItemAmount(id, quantity);

        return "redirect:/cart";
    }


    @PostMapping("/addToCart")
    public String addToCart(@RequestParam("id") long id) {

        Sweet sweet = getRepo().findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid sweet Id:" + id));

        cart.addToCart(sweet);

        return "redirect:/";
    }

    @PostMapping("/destroy")
    public String destroySession(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/cart";
    }
}