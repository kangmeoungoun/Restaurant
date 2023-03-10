package com.ga.jpatest.lunch.repository;

import com.ga.jpatest.lunch.domain.Heart;
import com.ga.jpatest.lunch.domain.Member;
import com.ga.jpatest.lunch.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface HeartRepository extends JpaRepository<Heart,Long> {

    List<Heart> findByRestaurantAndDate(@Param("restaurant") Restaurant restaurant,
                                        @Param("date") LocalDate date);
    Heart findByRestaurantAndMemberAndDate(@Param("restaurant") Restaurant restaurant,
                                        @Param("member") Member member,
                                        @Param("date") LocalDate date);
}
