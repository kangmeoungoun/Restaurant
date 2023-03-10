package com.ga.jpatest.lunch.service;

import com.ga.jpatest.lunch.domain.Restaurant;
import com.ga.jpatest.lunch.domain.embedded.Address;
import com.ga.jpatest.lunch.parser.CSVParser;
import com.ga.jpatest.lunch.repository.RestaurantRepository;
import com.ga.jpatest.lunch.web.dto.RestaurantInsertForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;


    @Transactional
    public void save(RestaurantInsertForm form) {
        Restaurant restaurant = Restaurant.createRestaurant(form);
        restaurantRepository.save(restaurant);
    }
    public Restaurant findById(Long id){
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 음식점을 찾을수가 없습니다."));
        return restaurant;
    }

}
