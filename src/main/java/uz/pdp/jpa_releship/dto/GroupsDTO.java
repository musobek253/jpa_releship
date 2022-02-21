package uz.pdp.jpa_releship.dto;

import lombok.Data;
import uz.pdp.jpa_releship.entity.Subject;

import java.util.List;

@Data
public class GroupsDTO {
    private String name;
    private Integer schoolId;
    private Integer teacherId;
    private List<Integer> subjectsIds;// siz shu yerda Subjectlarni idlarini ushlab olishiz siga ham frontchiga ham qulay
    // va logicheskiy ham maqsadaga muvofi  q1qqqq
}
