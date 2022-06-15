package hac.ex4spring.repo;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;


@Component
@Entity
@Getter
@Setter
/**
 * we chose our product to be a Sweet (rather than a Book)
 * this class represents a Sweet
 */
public class Sweet implements Serializable {
    private final int MAX_URL_LENGTH = 255;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @NotBlank(message = "Name is mandatory")
    private String sweetName;

    @Length(message = "URL must be shorter than " + MAX_URL_LENGTH, max = MAX_URL_LENGTH) // more than 255, DB crashes
    private String imageLink;

    @PositiveOrZero(message = "Cannot have negative number of sweets")
    private int quantity;

    /**
     * validate quantity is not negative
     * @param quantity a number
     */
    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException(getSweetName() + " is out of stock");
        }
        this.quantity = quantity;
    }

    @Positive(message = "Must be greater than 0")
    private double price;

    @Range(message = "Discount must be in valid range 0..100", min = 0, max = 100)
    @PositiveOrZero(message = "Must be 0 or greater")
    private double discount;


    /**
     * validates the link is not too long
     *
     * @param imageLink a link
     */
    public void setImageLink(String imageLink) {
        if (imageLink.length() > MAX_URL_LENGTH) {
            throw new IllegalArgumentException("URL must be shorter than " + MAX_URL_LENGTH);
        }
        this.imageLink = imageLink;
    }


}
