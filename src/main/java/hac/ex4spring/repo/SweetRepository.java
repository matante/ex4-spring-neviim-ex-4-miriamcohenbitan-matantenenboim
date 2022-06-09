package hac.ex4spring.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SweetRepository extends JpaRepository<Sweet, Long> {
//    List<Sweet> findFirst5ByDiscountDesc();
    List<Sweet>findBySweetNameContains(String sweetName);
    List<Sweet>findFirst5ByOrderByDiscountDesc();


}
