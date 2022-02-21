package uz.pdp.jpa_releship.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String frist_name;

    @Column(nullable = false)
    private  String lastName;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @ManyToOne
    private Groups groups;

}
