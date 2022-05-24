package hac.ex4spring.repo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;

@Getter
@Setter
@Entity
public class Candy implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty(message = "Name is mandatory")
    private String candyName;

    @Value("${link:https://www.google.co.il/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png}")
    private String imageLink;

    @PositiveOrZero
    private int quantity = 1;

    @Positive
    private double price = 2;

    @PositiveOrZero
    private double discount = 0;

}
