package com.ga.jpatest.lunch;

import com.ga.jpatest.lunch.domain.Comment;
import com.ga.jpatest.lunch.domain.Restaurant;
import com.ga.jpatest.lunch.domain.embedded.Address;
import com.ga.jpatest.lunch.parser.CSVParser;
import com.ga.jpatest.lunch.repository.CommentRepository;
import com.ga.jpatest.lunch.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @EventListener(ApplicationReadyEvent.class)
    public void init(){
        try {
            initService.initRestaurant();
            initService.initComment();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }



    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService{
        private final RestaurantRepository restaurantRepository;
        private final CommentRepository commentRepository;
        public void initRestaurant() throws IOException {
            final String delimiter = "\\|";
            CSVParser csvParser = new CSVParser("static/Restaurants.csv");
            csvParser.parse()
                    .forEach(str->{
                        String[] split = str.split(delimiter);
                        restaurantRepository.save(new Restaurant(split[0], new Address(split[1],split[2]), split[3], split[4]));
                    });
        }
        public void initComment() throws IOException {
            List<Restaurant> restaurants = restaurantRepository.findAll();
            restaurants.forEach(r->{
                for (int i = 0; i < 150; i++) {
                    Comment comment = new Comment(r,"코멘트"+i);
                    commentRepository.save(comment);
                }
            });
        }
    }
}
