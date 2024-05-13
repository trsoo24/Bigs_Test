package org.example.controller;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.example.entity.Weather;
import org.example.service.InquireService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class InquireController {
    private final InquireService inquireService;

    @GetMapping("/weather")
    public ResponseEntity<List<Weather>> getWeatherList(@RequestParam @Pattern(regexp = "\\d{8}", message = "20240501 형식으로 8자리를 맞춰 입력해주세요.") String forecastDate,
                                                        @RequestParam @NotNull(message = "필수 입력 값입니다. 경기도 의정부시 문충로74 는 62 입니다.") Integer nx,
                                                        @RequestParam @NotNull(message = "필수 입력 값입니다. 경기도 의정부시 문충로74 는 130 입니다.") Integer ny) {
        return ResponseEntity.ok(inquireService.getWeatherForecast(forecastDate, nx, ny));
    }
}
