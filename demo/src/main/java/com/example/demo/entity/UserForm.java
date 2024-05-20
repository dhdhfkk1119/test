package com.example.demo.entity;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserForm {
    @Size(min = 3 , max = 25)
    @NotEmpty(message = "사용자ID는 필수 항목입니다.")
    private String userid;

    @NotEmpty(message = "비밀번호는 필수 항목입니다.")
    private String password1;

    @NotEmpty(message = "비밀번호 확인 필수 항목입니다.")
    private String password2;

    @NotEmpty(message = "사용자이름은 필수 항목입니다.")
    private String username;


}
