package uz.pdp.jpa_releship.repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.jpa_releship.entity.Groups;

@Repository
public interface GroupsRepository extends JpaRepository<Groups,Integer> {
    boolean existsByName(String name);
}
