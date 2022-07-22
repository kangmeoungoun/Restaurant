package com.ga.jpatest.elasticsearch.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class KnowUpdateForm {
    private Long id;
    private String title;
    private String content;
}
