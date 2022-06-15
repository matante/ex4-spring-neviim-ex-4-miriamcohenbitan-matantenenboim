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
    public static final String DEFAULT_IMAGE_LINK_PATH = "default-sweet.png";
    public static final String INVALID_ID_MSG = "Invalid sweet Id:";
    private final String OUT_OF_STOCK_MSG = " is out of stock";
    private final String INVALID_URL_MSG = "URL must be shorter than ";
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
            throw new IllegalArgumentException(getSweetName() + OUT_OF_STOCK_MSG);
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
            throw new IllegalArgumentException(INVALID_URL_MSG + MAX_URL_LENGTH);
        }
        this.imageLink = imageLink;
    }


}
