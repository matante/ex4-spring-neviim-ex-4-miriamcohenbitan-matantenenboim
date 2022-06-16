package hac.ex4spring.repo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Hashtable;

@Component
@Getter
@Setter
/**
 * this class represents a cart of products (sweets)
 */
public class Cart implements Serializable {
    private Hashtable<Sweet, Integer> sweetsTable; //hash table to store Sweet as key and amount as value

    /**
     * constructor
     */
    public Cart() {
        this.sweetsTable = new Hashtable<Sweet, Integer>();
    }

    /**
     * gets a Sweet, check if already in the table. if yes, adds 1 to counter, else (new Sweet) adds to table
     *
     * @param other a Sweet that may be added to the table
     */
    public void addToCart(Sweet other) {
        boolean found = false; // by default
        for (Sweet sweet : sweetsTable.keySet())
            if (sweet.getId() == other.getId()) { // exists
                sweetsTable.put(sweet, sweetsTable.get(sweet) + 1); // add 1
                found = true; // exists
                break; // no need to check others
            }
        if (!found) // a new Sweet
            sweetsTable.put(other, 1); // add to table with value of 1
    }

    /**
     * searches for an item in the table, and updates its amount
     *
     * @param id     id of the sweet
     * @param amount new amount
     */
    public void setItemAmount(long id, Integer amount) {
        for (Sweet sweet : sweetsTable.keySet())
            if (sweet.getId() == id) {
                sweetsTable.put(sweet, amount);
                break; // found
            }
    }

    /**
     * returns the amount of a sweet in the cart
     *
     * @param id id of the sweet
     * @return amount
     */
    public Integer getItemAmount(long id) {
        for (Sweet sweet : sweetsTable.keySet())
            if (sweet.getId() == id) {
                return sweetsTable.get(sweet);
            }
        return 0;
    }

    /**
     * as name described
     *
     * @param id id of the sweet
     */
    public void removeItemFromCart(long id) {
        for (Sweet sweet : sweetsTable.keySet())
            if (sweet.getId() == id) {
                sweetsTable.remove(sweet);
                break;
            }
    }

    /**
     * calculate the total price of all the items in the cart
     *
     * @return as explained
     */
    public double getTotalPrice() {
        double result = 0;

        for (Sweet sweet : sweetsTable.keySet()) {
            result += getDiscountedPrice(sweet.getId());
        }
        return result;
    }

    /**
     * gets a sweet's id, calculate it's discounted price, returns multiplied by numbers of sweet in the cart
     * @param id id
     * @return as described
     */
    public double getDiscountedPrice(long id){
        double result = 0;
        for (Sweet sweet : sweetsTable.keySet())
            if (sweet.getId() == id) {
                result = sweet.getDiscountedPrice() * sweetsTable.get(sweet);
                break;
            }
        return result;
    }



        /**
         * calculate how many items overall in the cart (here, 5x banana + 3x hubba bubba = 8)
         *
         * @return
         */
    public Integer getNumOfItems() {
        Integer result = 0;
        for (Sweet sweet : sweetsTable.keySet()) {
            result += sweetsTable.get(sweet);
        }
        return result;
    }

    /**
     * return how many unique items in the cart (here, 5x banana + 3x hubba bubba = 2)
     *
     * @return
     */
    public Integer getNumOfUniqueItems() {
        return sweetsTable.size();
    }

    /**
     * empties the cart
     */
    public void empty() {
        this.sweetsTable = new Hashtable<Sweet, Integer>();
    }

    @Bean
    /**
     * for session
     */
    @Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public Cart sessionCart() {
        return new Cart();
    }


}
