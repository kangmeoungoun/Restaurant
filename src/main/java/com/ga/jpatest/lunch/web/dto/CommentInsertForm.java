package com.ga.jpatest.lunch.web.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class CommentInsertForm {
    @NotBlank(message = "코멘트를 입력해주시기 바랍니다.")
    private String contents;
}
