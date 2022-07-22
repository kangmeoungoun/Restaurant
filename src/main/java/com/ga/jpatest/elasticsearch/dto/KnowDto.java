package com.ga.jpatest.elasticsearch.dto;

import com.ga.jpatest.elasticsearch.Know;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class KnowDto {
    private Long id;
    private String title;
    private String content;
    public static KnowDto from(Know know){
        return new KnowDto(know.getId(), know.getTitle(), know.getContent());
    }
}
