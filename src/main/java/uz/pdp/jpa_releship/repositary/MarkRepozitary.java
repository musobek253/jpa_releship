package uz.pdp.jpa_releship.repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.jpa_releship.entity.Mark;

@Repository
public interface MarkRepozitary extends JpaRepository<Mark,Integer> {
}
