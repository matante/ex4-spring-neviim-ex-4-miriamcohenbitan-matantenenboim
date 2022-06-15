package hac.ex4spring.controllers;

import hac.ex4spring.repo.Cart;
import hac.ex4spring.repo.Sweet;

import hac.ex4spring.repo.SweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private SweetRepository repository;

    private SweetRepository getRepo() {
        return repository;
    }

    @Resource(name = "sessionCart")
    private Cart cart;

    @GetMapping({"", "/delete", "/update", "/addToCart"})
    public String process(Model model) {

        model.addAttribute("allSweetsInCart", cart.getSweetsTable().keySet());
        model.addAttribute("cart", cart);
        model.addAttribute("error", "");
        model.addAttribute("top5", getRepo().findFirst5ByOrderByDiscountDesc());

        return "cart";
    }


    @GetMapping("/checkout")
    public String checkout(Model model) {

        model.addAttribute("allSweetsInCart", cart.getSweetsTable().keySet());
        model.addAttribute("cart", cart);

        return "checkout";
    }

    @GetMapping("/emptyCart")
    public String emptyCart() {
        cart.empty();
        return "redirect:/cart";
    }

    /**
     * deletes sweet with corresponding id from the db
     *
     * @param id    id
     * @param model model
     * @return html
     */
    @PostMapping("/delete")
    public String deleteSweet(@RequestParam("id") long id, Model model) {
        cart.removeItemFromCart(id);
        return "redirect:/cart";
    }

    /**
     * updates a sweet's quantity
     *
     * @param id       id
     * @param quantity new quantity
     * @param model    model
     * @return html
     */
    @PostMapping("/update")
    public String updateSweet(@RequestParam("id") long id, @RequestParam("quantity") Integer quantity, Model model) {

        if (quantity == 0) cart.removeItemFromCart(id);
        else cart.setItemAmount(id, quantity);

        return "redirect:/cart";
    }


    /**
     * gets an id, grabs the corresponding sweet from the db, adds it to cart
     *
     * @param id id
     * @return html
     */
    @PostMapping("/addToCart")
    public String addToCart(@RequestParam("id") long id, @RequestParam("source") String source) {

        Sweet sweet = getRepo().findById(id).orElseThrow(() -> new IllegalArgumentException(Sweet.INVALID_ID_MSG + id));
        cart.addToCart(sweet);
        if (source == null || source.equals("web"))
            return "redirect:/";

        return "redirect:/cart";
    }

//    @GetMapping("/destroy")
//    public String destroySession(HttpServletRequest request) {
//        request.getSession().invalidate();
//        return "redirect:/cart";
//    }
}