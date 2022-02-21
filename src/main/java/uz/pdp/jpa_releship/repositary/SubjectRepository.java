package uz.pdp.jpa_releship.repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.jpa_releship.entity.Subject;

import java.util.List;


@Repository
public interface SubjectRepository extends JpaRepository<Subject,Integer> {
    boolean existsByName(String name);

    boolean existsByIdIn(List<Integer> ids); // buni vazifasi siz bergan subjectlarni idlari aynan bizni bazada bor yo'q
    // ligini tekshiradi
    List<Subject> findAllByIdIn(List<Integer> ids); // bu vazifasi berilgan idlar listigaga mos subjectlarni listinin beradi

}