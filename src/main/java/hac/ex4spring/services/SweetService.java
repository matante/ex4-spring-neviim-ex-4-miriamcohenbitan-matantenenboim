package hac.ex4spring.services;

import hac.ex4spring.repo.Cart;
import hac.ex4spring.repo.Sweet;
import hac.ex4spring.repo.SweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
public class SweetService {

    @Autowired
    private SweetRepository repository;
    private SweetRepository getRepo() {
        return repository;
    }

    @Resource(name = "sessionCart")
    private Cart cart;

    @Transactional
    public void reduceSweetsQuantities() {

        for (Sweet sweetInCart : cart.getSweetsTable().keySet()) {
            Sweet sweetFromDB = getRepo().findById(sweetInCart.getId()).orElseThrow(() -> new IllegalArgumentException("Invalid sweet Id:" + sweetInCart.getId()));
            int wantedAmount = cart.getSweetsTable().get(sweetInCart);
            int availableAmount = sweetFromDB.getQuantity();
            int newQuantity = availableAmount - wantedAmount;


            sweetFromDB.setQuantity(newQuantity); // may throw exception
            getRepo().save(sweetFromDB);

        }
    }

}

