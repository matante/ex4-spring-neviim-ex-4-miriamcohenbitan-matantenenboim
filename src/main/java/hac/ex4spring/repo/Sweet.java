package hac.ex4spring.repo;


import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;


@Component
@Entity
public class Sweet implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @NotBlank(message = "Name is mandatory")
    private String sweetName;

    @Length(message = "URL must be shorter than 255", max = 255)
    private String imageLink;

    @PositiveOrZero(message = "Cannot have negative number of sweets")
    private int quantity;
    public void setQuantity(int amount){
        if (amount < 0){
            throw new IllegalArgumentException(getSweetName() + " is out of stock");
        }
        quantity = amount;
    }

    @Positive(message = "Must be greater than 0")
    private double price;

    @Range(message = "Discount must be in valid range 0..100", min = 0, max = 100)
    @PositiveOrZero(message = "Must be 0 or greater")
    private double discount;

    public String getSweetName() {
        return sweetName;
    }

    public void setSweetName(String sweetName) {
        this.sweetName = sweetName;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
