package hac.ex4spring.repo;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;

@Component
@Getter
@Setter
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

    @Positive(message = "Must be greater than 0")
    private double price;

    @Range(message = "Discount must be in valid range 0..100", min = 0, max = 100)
    @PositiveOrZero(message = "Must be 0 or greater")
    private double discount;

}
