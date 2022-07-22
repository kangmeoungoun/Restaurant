package com.ga.jpatest.lunch.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SequenceGenerator(name = "RESTURANT_SEQ",sequenceName = "RESTURANT_SEQ")
public class Restaurant extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "RESTURANT_SEQ")
    private Long id;
    private String name;
    private String place;
    private String sectors;
    private String phoneNumber;

    private String remark;

    public Restaurant(String name, String place, String sectors, String phoneNumber) {
        this.name = name;
        this.place = place;
        this.sectors = sectors;
        this.phoneNumber = phoneNumber;
    }
}
