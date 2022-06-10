//package hac.ex4spring.controllers;
//
//import hac.ex4spring.repo.Sweet;
//import hac.ex4spring.repo.SweetRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//import java.util.Objects;
//
//@Controller
//@RequestMapping("/cart")
//public class CartController {
//    @Autowired
//    private SweetRepository repository;
//    private SweetRepository getRepo() {
//        return repository;
//    }
//
//    @GetMapping("")
//    public String main(Sweet sweet, Model model) {
//        //model.addAttribute("sweets", getRepo().findAll());
//        return "cart";
//    }
//
//}
