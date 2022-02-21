package uz.pdp.jpa_releship.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Teacher teacher;

    @Column(nullable = false)
    private String value;

    @Column(nullable = false)
    private String date;
}
