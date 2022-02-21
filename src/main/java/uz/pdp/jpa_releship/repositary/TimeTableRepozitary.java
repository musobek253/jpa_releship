package uz.pdp.jpa_releship.repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.jpa_releship.entity.TimeTable;
@Repository
public interface TimeTableRepozitary extends JpaRepository<TimeTable,Integer> {

}
