package hac.ex4spring.controllers;

import hac.ex4spring.repo.Sweet;

import hac.ex4spring.beans.Messages;
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

    // injection by ctor: match by name
    @Resource(name = "sessionBeanExample")
    private Messages sessionMessages;



    @GetMapping("/cart")
    public String process(Model model) {
        model.addAttribute("sessionMessages", sessionMessages.getMessages());
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

    @PostMapping("/persistMessage")
    public String persistMessage(@RequestParam("id") long id) {

        Sweet sweet = getRepo()
                .findById(id)
                .orElseThrow(
                        () -> new IllegalArgumentException("Invalid sweet Id:" + id)
                );

        System.out.println(sweet.getSweetName());
        sessionMessages.add(sweet.getSweetName());

        return "redirect:/";
    }

    @PostMapping("/destroy")
    public String destroySession(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/cart";
    }
}