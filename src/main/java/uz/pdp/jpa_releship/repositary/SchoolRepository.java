package uz.pdp.jpa_releship.repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.jpa_releship.entity.School;

@Repository
public interface SchoolRepository extends JpaRepository<School,Integer> {
    boolean existsByName(String name);
}
