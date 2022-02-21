package uz.pdp.jpa_releship.repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.jpa_releship.entity.Student;

@Repository
public interface StudentRepozitary extends JpaRepository<Student,Integer> {
    boolean existsByPhoneNumber(String PhoneNumber);
}
