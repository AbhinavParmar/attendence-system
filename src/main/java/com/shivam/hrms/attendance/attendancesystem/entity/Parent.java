package com.shivam.hrms.attendance.attendancesystem.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "tbl_parent")
public class Parent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long id;

    private String fathersName;
    private String mothersName;
    private String mothersMaidenName;

    public Parent(String fathersName, String mothersName, String mothersMaidenName) {
        this.fathersName = fathersName;
        this.mothersName = mothersName;
        this.mothersMaidenName = mothersMaidenName;
    }
}
