package com.ga.jpatest.lunch.service;

import com.ga.jpatest.lunch.domain.Heart;
import com.ga.jpatest.lunch.domain.Member;
import com.ga.jpatest.lunch.domain.Restaurant;
import com.ga.jpatest.lunch.repository.HeartRepository;
import com.ga.jpatest.lunch.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class HeartService {
    private final HeartRepository heartRepository;
    private final MemberRepository memberRepository;
    private final RestaurantService restaurantService;

    @Transactional
    public void heartUp(Long id, String name) {
        Restaurant restaurant = restaurantService.findById(id);
        Member member = memberRepository.findByName(name);
        Heart heart = Heart.create(restaurant, member);
        heartRepository.save(heart);
    }

    @Transactional
    public void heartDown(Long id, String name) {
        Restaurant restaurant = restaurantService.findById(id);
        Member member = memberRepository.findByName(name);
        Heart heart = heartRepository.findByRestaurantAndMemberAndDate(restaurant, member, LocalDate.now());
        heartRepository.delete(heart);
    }
}
