package com.ga.jpatest.lunch.service;

import com.ga.jpatest.lunch.domain.Restaurant;
import com.ga.jpatest.lunch.parser.CSVParser;
import com.ga.jpatest.lunch.repository.RestaurantRepository;
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

    @PostConstruct
    @Transactional
    public void init() throws IOException {
        final String delimiter = "\\|";
        CSVParser csvParser = new CSVParser("static/Restaurants.csv");
        csvParser.parse()
                .forEach(str->{
                    String[] split = str.split(delimiter);
                    restaurantRepository.save(new Restaurant(split[0], split[1], split[2], split[3]));
                });
    }

}
