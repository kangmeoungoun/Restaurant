package com.ga.jpatest.lunch.domain;

import com.ga.jpatest.lunch.domain.embedded.Address;
import com.ga.jpatest.lunch.web.dto.RestaurantInsertForm;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SequenceGenerator(name = "RESTURANT_SEQ",sequenceName = "RESTURANT_SEQ")
public class Restaurant extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "RESTURANT_SEQ")
    @Column(name = "RESTAURANT_ID")
    private Long id;
    private String name;
    @Embedded
    private Address address;
    private String sectors;
    private String phoneNumber;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    private List<Comment> comments = new ArrayList<>();
    private String remark;

    public Restaurant(String name, Address address, String sectors, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.sectors = sectors;
        this.phoneNumber = phoneNumber;
    }


    public static Restaurant createRestaurant(RestaurantInsertForm form) {
        Restaurant restaurant = new Restaurant();
        restaurant.name = form.getName();
        restaurant.phoneNumber = form.getPhoneNumber();
        restaurant.remark = form.getRemark();
        restaurant.sectors = form.getSectors();
        restaurant.address = new Address(form.getStreet(), form.getZipcode());
        return restaurant;
    }
}
