package hac.ex4spring.repo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CandyRepository extends JpaRepository<Candy, Long> {
}
