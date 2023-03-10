package com.ga.jpatest.lunch.web.api;

import com.ga.jpatest.lunch.domain.Heart;
import com.ga.jpatest.lunch.domain.Member;
import com.ga.jpatest.lunch.domain.Restaurant;
import com.ga.jpatest.lunch.repository.HeartRepository;
import com.ga.jpatest.lunch.repository.MemberRepository;
import com.ga.jpatest.lunch.repository.RestaurantRepository;
import com.ga.jpatest.lunch.service.HeartService;
import com.ga.jpatest.lunch.service.RestaurantService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/heart")
public class HeartRestController {

    private final MemberRepository memberRepository;
    private final RestaurantService restaurantService;
    private final HeartRepository heartRepository;
    private final HeartService heartService;

    @GetMapping("/restaurant/{id}")
    public HeartDto findHeartByUserAndRestaurant(@PathVariable Long id,
                                                 @CookieValue(name = "name", required = false) String name){
        Member member = memberRepository.findByName(name);
        Restaurant restaurant = restaurantService.findById(id);
        List<Heart> hearts = heartRepository.findByRestaurantAndDate(restaurant, LocalDate.now());
        boolean isMyHeart = hearts.stream()
                .anyMatch(h -> member.equals(h.getMember()));

        return new HeartDto(hearts.size(),isMyHeart);

    }
    @PostMapping("/restaurant/{id}")
    public void heartUp(@PathVariable Long id,
                        @CookieValue(name = "name", required = false) String name){
        heartService.heartUp(id, name);

    }
    @DeleteMapping("/restaurant/{id}")
    public void heartDown(@PathVariable Long id,
                        @CookieValue(name = "name", required = false) String name){
        heartService.heartDown(id, name);

    }
    @Getter
    @AllArgsConstructor
    static class HeartDto {
        private int count;
        private boolean isHeart;
    }
}
