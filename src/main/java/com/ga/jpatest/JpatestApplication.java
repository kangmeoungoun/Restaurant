package com.ga.jpatest;

import com.ga.jpatest.elasticsearch.repository.search.KnowSearchRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(excludeFilters = @ComponentScan.Filter(
		type = FilterType.ASSIGNABLE_TYPE,
		classes = KnowSearchRepository.class))
@SpringBootApplication
@EnableJpaAuditing
public class JpatestApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpatestApplication.class, args);
	}

}
