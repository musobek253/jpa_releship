package uz.pdp.jpa_releship.repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.jpa_releship.entity.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Integer> {
    boolean existsByName(String name);
}
