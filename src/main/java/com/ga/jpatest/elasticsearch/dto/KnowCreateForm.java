package com.ga.jpatest.elasticsearch.dto;

import lombok.Data;

@Data
public class KnowCreateForm {
    private Long id;
    private String title;
    private String content;
}
