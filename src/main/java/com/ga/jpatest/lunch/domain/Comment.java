package com.ga.jpatest.lunch.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "comment")
@Getter
@NoArgsConstructor
public class Comment extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "COMMENT_ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "RESTAURANT_ID")
    private Restaurant restaurant;

    private String contents;

    public Comment(Restaurant restaurant, String contents) {
        this.restaurant = restaurant;
        this.contents = contents;
    }
}