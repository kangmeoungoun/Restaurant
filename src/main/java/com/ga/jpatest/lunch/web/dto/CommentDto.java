package com.ga.jpatest.lunch.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class CommentDto{
    private Long id;
    @NotBlank(message = "내용을 입력해주시기 바랍니다.")
    private String content;
}
