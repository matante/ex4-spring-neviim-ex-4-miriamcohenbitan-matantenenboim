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
public class Payment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Positive(message = "Must be greater than 0")
    @Column(nullable = false)
    private double amount;

    @CreationTimestamp
    private Date datetime;



}
