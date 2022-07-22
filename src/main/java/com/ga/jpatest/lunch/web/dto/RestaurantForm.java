package com.ga.jpatest.lunch.web.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class RestaurantForm {
    private Long id;
    @NotBlank(message = "상호명을 입력해주시기 바랍니다.")
    @Size(max = 20,message = "20글자를 넘길수 없습니다.")
    private String name;
    @NotBlank(message = "장소을 입력해주시기 바랍니다.")
    @Size(max = 100,message = "100글자를 넘길수 없습니다.")
    private String place;
    @NotBlank(message = "업종명을 입력해주시기 바랍니다.")
    @Size(max = 100,message = "100글자를 넘길수 없습니다.")
    private String sectors;
    private String phoneNumber;
    private String remark;
}
