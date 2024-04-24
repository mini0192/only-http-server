package com.example.demo.account;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Signup {
    @NotBlank(message = "아이디를 넣어주세요")
    private String username;

    @NotBlank(message = "비밀번호를 넣어주세요")
    private String password;

    @NotBlank(message = "이름을 넣어주세요")
    private String name;
}
