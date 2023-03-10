package com.ga.jpatest.lunch.web.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class RestaurantInsertForm {
    @NotBlank(message = "상호명을 입력해주시기 바랍니다.")
    @Size(max = 20,message = "20글자를 넘길수 없습니다.")
    private String name;
    @NotBlank(message = "주소을 입력해주시기 바랍니다.")
    @Size(max = 100,message = "100글자를 넘길수 없습니다.")
    private String street;

    @NotBlank(message = "우편번호 를 입력해주시기 바랍니다.")
    private String zipcode;
    @NotBlank(message = "업종명을 입력해주시기 바랍니다.")
    @Size(max = 100,message = "100글자를 넘길수 없습니다.")
    private String sectors;
    @NotBlank(message = "전화번호를 입력해주시기 바랍니다.")
    @Pattern(regexp = "([0]\\d{1,3})([ -]?)(\\d{3,4})([ -]?)(\\d{4})",message = "000-0000-0000 형태로 입력해주시기 바랍니다.")
    private String phoneNumber;
    private String remark;
}
