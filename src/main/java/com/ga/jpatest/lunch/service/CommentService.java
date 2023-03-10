package com.ga.jpatest.lunch.service;

import com.ga.jpatest.lunch.domain.Comment;
import com.ga.jpatest.lunch.domain.Restaurant;
import com.ga.jpatest.lunch.repository.CommentRepository;
import com.ga.jpatest.lunch.web.dto.CommentInsertForm;
import com.ga.jpatest.lunch.web.dto.RestaurantInsertForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class CommentService {
    private final CommentRepository commentRepository;

    private final RestaurantService restaurantService;

    @Transactional
    public void save(Long id, CommentInsertForm commentInsertForm) {
        Restaurant restaurant = restaurantService.findById(id);
        Comment comment = new Comment(restaurant, commentInsertForm.getContents());
        commentRepository.save(comment);
    }
}
