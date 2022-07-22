package com.ga.jpatest.lunch.web;

import com.ga.jpatest.lunch.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    private final RestaurantRepository restaurantRepository;

    @GetMapping("/")
    public String home(){
        return "index";
    }

}
