package hac.ex4spring.repo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.io.Serializable;
import java.util.Hashtable;

@Component
@Getter
@Setter
public class Cart implements Serializable {
    Hashtable<Sweet, Integer> sweetMap;

    public Cart() {
        this.sweetMap = new Hashtable<Sweet, Integer>();
    }

    public void addToCart(Sweet other) {
        boolean found = false;
        for (Sweet sweet : sweetMap.keySet())
            if (sweet.getId() == other.getId()) {
                sweetMap.put(sweet, sweetMap.get(sweet) + 1);

                found = true;
                break;
            }
        if (!found)
            sweetMap.put(other, 1);

    }

    public void setItemAmount(long id, Integer amount){
        for (Sweet sweet : sweetMap.keySet())
            if (sweet.getId() == id) {
                sweetMap.put(sweet, amount);
                break;
            }

    }

    public Integer getItemAmount(long id){
        for (Sweet sweet : sweetMap.keySet())
            if (sweet.getId() == id) {
                return sweetMap.get(sweet);
            }
        return 0;
    }

    public void removeItemFromCart(long id) {
        for (Sweet sweet : sweetMap.keySet())
            if (sweet.getId() == id) {
                sweetMap.remove(sweet);
                break;
            }
    }

    public double getTotalPrice() {
        double result = 0;

        for (Sweet sweet : sweetMap.keySet()) {
            double price = sweet.getPrice() * sweetMap.get(sweet);
            double discount = sweet.getDiscount();

            if (discount > 0) result += price - price * discount / 100;
            else result += price;
//            result += discount > 0 ? price - price * discount / 100 : price;
        }

        return Math.floor(result * 100 ) / 100;
    }

    /**
     * calculate how many items overall in the cart (here, 5x banana + 3x hubba bubba = 8)
     * @return
     */
    public Integer getNumOfItems(){
        Integer result = 0;
        for (Sweet sweet : sweetMap.keySet()) {
            result += sweetMap.get(sweet);
        }
        return result;
    }

    /**
     * return how many unique items in the cart (here, 5x banana + 3x hubba bubba = 2)
     * @return
     */
    public Integer getNumOfUniqueItems(){
        return sweetMap.size();
    }

    public void empty() {
        this.sweetMap = new Hashtable<Sweet, Integer>();
    }

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public Cart sessionCart() {
        return new Cart();
    }


}
