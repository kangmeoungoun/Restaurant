package com.ga.jpatest.lunch.web;

import com.ga.jpatest.lunch.repository.RestaurantRepository;
import com.ga.jpatest.lunch.service.RestaurantService;
import com.ga.jpatest.lunch.web.dto.RestaurantInsertForm;
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

    private final RestaurantService restaurantService;
    private final RestaurantRepository restaurantRepository;

    @GetMapping("/restaurant/new")
    public String Restaurant(Model model){
        model.addAttribute("form", new RestaurantInsertForm());
        return "restaurant/createRestaurantForm";
    }
    @PostMapping("/restaurant/new")
    public String RestaurantCreate(@ModelAttribute(name = "form") @Valid RestaurantInsertForm form,
                                   BindingResult bindingResult){
        if(bindingResult.hasErrors()){
           return "restaurant/createRestaurantForm";
        }
        restaurantService.save(form);
        return "redirect:/";
    }

    @GetMapping("/restaurants")
    public String Restaurants(Model model){
        model.addAttribute("resturants", restaurantRepository.findAll());
        return "restaurant/restaurantList";
    }
}
