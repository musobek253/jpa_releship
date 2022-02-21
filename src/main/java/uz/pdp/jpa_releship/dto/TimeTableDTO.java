package uz.pdp.jpa_releship.dto;

import lombok.Data;

@Data
public class TimeTableDTO {
    private Integer groupsId;
    private Integer teacherId;
    private String startTime;
    private String endTime;

}


