package hac.ex4spring.repo;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.Date;

@Component
@Getter
@Setter
@Entity
/**
 * a class to represent a payment
 */
public class Payment implements Serializable {
    private final String ILLEGAL_PRICE_MSG = "Price to pay must be greater than 0";
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Positive(message = "Must be greater than 0")
    @Column(nullable = false)
    private double amount;

    @CreationTimestamp
    private Date datetime;

    private String username;

    /**
     * validate the amount is > 0
     * @param amount the amount to set in the payment
     */
    public void setAmount(double amount) {
        if (amount <= 0){
            throw new IllegalArgumentException(ILLEGAL_PRICE_MSG);
        }
        this.amount = amount;
    }
}
