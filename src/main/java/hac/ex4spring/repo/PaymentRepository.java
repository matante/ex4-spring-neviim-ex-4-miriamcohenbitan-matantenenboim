package hac.ex4spring.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByOrderByDatetimeDesc();
//    double sumByAmount();

    @Query("SELECT SUM(p.amount) from Payment p")
    Double sumByAmount();



}
