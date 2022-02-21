package uz.pdp.jpa_releship.dto;

import lombok.Data;

import java.util.Date;

@Data
public class MarkDTO {

    private int studentId;

    private int teacherId;

    private String value;

    private String date;
}
