package com.ga.jpatest.lunch.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(
        uniqueConstraints={
                @UniqueConstraint(
                        name="HEART_UNIQUE",
                        columnNames={"RESTAURANT_ID", "MEMBER_ID","date"}
                )
        }
)
@Getter
public class Heart extends BaseEntity{
    @Id
    @GeneratedValue
    @Column(name = "HEART_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RESTAURANT_ID")
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    private LocalDate date;

    public static Heart create(Restaurant restaurant,Member member){
        Heart heart = new Heart();
        heart.restaurant = restaurant;
        heart.member = member;
        heart.date = LocalDate.now();
        return heart;
    }

}
