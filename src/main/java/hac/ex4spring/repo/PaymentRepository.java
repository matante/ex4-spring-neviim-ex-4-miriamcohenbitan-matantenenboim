package hac.ex4spring.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * handles the SQL requests
 */
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByOrderByDatetimeDesc();

    @Query("SELECT SUM(p.amount) from Payment p") // sum all payments in the table
    Double sumByAmount();
}
