package com.example.sharing.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginForm {

    @NotBlank
    @Size(min = 2, max = 10, message = "이름은 2글자 이상, 10글자 이하여야 합니다.")
    private String username;

    @NotBlank
    @Size(min = 4, max = 12, message = "비밀번호는 4글자 이상, 12글자 이하여야 합니다.")
    private String password;

    @NotBlank
    @Size(min = 4, max = 12, message = "비밀번호는 4글자 이상, 12글자 이하여야 합니다.")
    private String password2;

    @NotBlank
    @Size(min = 3, max = 12, message = "사용자 이름은 3글자 이상, 12글자 이하여야 합니다")
    private String nickname;

    @NotEmpty(message = "이메일 규격에 맞춰 작성해주세요.")
    @Email
    private String email;
}
