package com.ga.jpatest.elasticsearch.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class BatchService {

    //@Scheduled(initialDelay = 1000 * 30, fixedDelay = 1000 * 10)
    public void test(){
        WebClient webClient = WebClient.create("http://localhost:3000");
        webClient.get()
                .uri("/know/test")
                .retrieve()
                .bodyToMono(String.class)
                .subscribe(str -> System.out.println(str));

    }
}
