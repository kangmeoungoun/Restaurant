package com.ga.jpatest.lunch.repository;

import com.ga.jpatest.lunch.domain.Comment;
import com.ga.jpatest.lunch.domain.Restaurant;
import com.ga.jpatest.lunch.web.dto.CommentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("select new com.ga.jpatest.lunch.web.dto.CommentDto(c.id,c.contents) from Comment c where c.restaurant = :restaurant")
    Page<CommentDto> findByRestaurant(@Param("restaurant") Restaurant restaurant, Pageable pageable);
}
