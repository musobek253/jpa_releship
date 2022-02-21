package uz.pdp.jpa_releship.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
@Entity
@Data
public class TimeTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    private Groups groups;

    @ManyToOne
    private Teacher teacher;

    private String startTime;

    private String endTime;
}
