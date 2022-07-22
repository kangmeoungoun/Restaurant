package com.ga.jpatest.lunch.web;

import com.ga.jpatest.lunch.domain.Restaurant;
import com.ga.jpatest.lunch.repository.RestaurantRepository;
import com.ga.jpatest.lunch.web.dto.RestaurantForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Slf4j
public class RestaurantController {

    private final RestaurantRepository restaurantRepository;

    @GetMapping("/restaurant/new")
    public String Restaurant(Model model){
        model.addAttribute("form", new RestaurantForm());
        return "restaurant/createRestaurantForm";
    }
    @PostMapping("/restaurant/new")
    public String RestaurantCreate(@ModelAttribute(name = "form") @Valid RestaurantForm form, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
           return "restaurant/createRestaurantForm";
        }
        return "redirect:/";
    }

    @GetMapping("/restaurants")
    public String Restaurants(Model model){
        model.addAttribute("resturants", restaurantRepository.findAll());
        return "restaurant/restaurantList";
    }
}
