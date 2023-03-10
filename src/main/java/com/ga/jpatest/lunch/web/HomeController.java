package com.ga.jpatest.lunch.web;

import com.ga.jpatest.lunch.domain.Member;
import com.ga.jpatest.lunch.repository.MemberRepository;
import com.ga.jpatest.lunch.repository.RestaurantRepository;
import com.ga.jpatest.lunch.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    private final MemberService memberService;

    @GetMapping("/")
    public String home(@CookieValue(name = "name", required = false) String name,
                       HttpServletResponse response){
        if(StringUtils.isEmpty(name)){
            name = UUID.randomUUID().toString();
            Cookie cookie = new Cookie("name", name);
            response.addCookie(cookie);
            memberService.save(name);

        }
        return "index";
    }

}
