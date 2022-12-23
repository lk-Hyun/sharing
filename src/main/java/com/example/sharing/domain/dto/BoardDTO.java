package com.example.sharing.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BoardDTO {

    @NotBlank
    @Size(min = 2, message = "제목은 최소 2글자 이상 입력해야 합니다")
    private String title;

    @NotEmpty
    @Size(min = 2, message = "글 내용은 최소 2글자 이상 입력해야 합니다")
    private String content;
}
