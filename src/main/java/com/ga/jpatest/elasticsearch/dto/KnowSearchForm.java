package com.ga.jpatest.elasticsearch.dto;

import lombok.Data;

@Data
public class KnowSearchForm {
    private Long id;
    private String searchInput;
    private Search search;
}
