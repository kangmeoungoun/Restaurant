package com.ga.jpatest.lunch.web;

import com.ga.jpatest.lunch.domain.Restaurant;
import com.ga.jpatest.lunch.repository.CommentRepository;
import com.ga.jpatest.lunch.service.CommentService;
import com.ga.jpatest.lunch.service.RestaurantService;
import com.ga.jpatest.lunch.web.dto.CommentDto;
import com.ga.jpatest.lunch.web.dto.CommentInsertForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@Slf4j
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final RestaurantService restaurantService;
    private final CommentService commentService;
    private final CommentRepository commentRepository;
    @GetMapping("/restaurant/{id}")
    public String commentList(@PathVariable Long id,
                              Model model,
                              Pageable pageable) {
        log.info("id = {}", id);
        model.addAttribute("comments", getComments(id, pageable));
        model.addAttribute("restaurantId", id);
        model.addAttribute("form", new CommentInsertForm());
        return "restaurant/comment";
    }

    @PostMapping("/restaurant/{id}")
    public String createComment(@PathVariable Long id,
                                Model model,
                                @ModelAttribute("form") @Valid CommentInsertForm commentInsertForm,
                                BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "id"));
            model.addAttribute("comments", getComments(id, pageRequest));
            return "restaurant/comment";
        }
        commentService.save(id, commentInsertForm);
        String redirectFormat = String.format("redirect:/comment/restaurant/%d", id);
        return redirectFormat;
    }

    private Page<CommentDto> getComments(Long id, Pageable pageable) {
        Restaurant restaurant = getRestaurant(id);
        return commentRepository.findByRestaurant(restaurant, pageable);

    }

    private Restaurant getRestaurant(Long id) {
        return restaurantService.findById(id);
    }

}
