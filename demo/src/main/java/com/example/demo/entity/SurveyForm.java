package com.example.demo.entity;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class SurveyForm {
    @NotNull(message = "q 항목을 체크 해주십시오.")
    private Long q;

    @NotNull(message = "w 항목을 체크 해주십시오.")
    private Long w;

    @NotNull(message = "e 항목을 체크 해주십시오.")
    private Long e;

    @NotNull(message = "r 항목을 체크 해주십시오.")
    private Long r;

    @NotNull(message = "a 항목을 체크 해주십시오.")
    private Long a;

    @NotNull(message = "s 항목을 체크 해주십시오.")
    private Long s;

    @NotNull(message = "d 항목을 체크 해주십시오.")
    private Long d;

    @NotNull(message = "f 항목을 체크 해주십시오.")
    private Long f;


}
