package com.ga.jpatest.lunch.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/comment")
public class CommentController {

    @GetMapping("/restaurant/{id}")
    public String Comment(@PathVariable Long id) {
        log.info("id = {}", id);
        return "comment";
    }
}
