package org.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OpenApiRequestDto {
    @NotNull(message = "필수 입력 값입니다.")
    private Integer pageNo; // 페이지 번호
    @NotNull(message = "필수 입력 값입니다.")
    private Integer numOfRows; // 한 페이지 결과 수
    @NotBlank(message = "필수 입력 값입니다.")
    @Pattern(regexp = "\\d{8}", message = "20240501 형식으로 8자리를 맞춰 입력해주세요.")
    private String baseDate; // 발표 날짜
    @NotBlank(message = "필수 입력 값입니다.")
    @Pattern(regexp = "\\d{4}", message = "0500 형식으로 4자리를 맞춰 입력해주세요.")
    private String baseTime; // 발표 시각
    @NotNull(message = "필수 입력 값입니다. 경기도 의정부시 문충로74 는 62 입니다.")
    private Integer nx; // x 좌표
    @NotNull(message = "필수 입력 값입니다. 경기도 의정부시 문충로74 는 130 입니다.")
    private Integer ny; // y 좌표
}
