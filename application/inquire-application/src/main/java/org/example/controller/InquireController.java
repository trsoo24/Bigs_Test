package org.example.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.dto.WeatherForecastDto;
import org.example.entity.Weather;
import org.example.service.InquireService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class InquireController {
    private final InquireService inquireService;

    @GetMapping("/weather")
    public ResponseEntity<List<Weather>> getWeatherList(@RequestBody @Valid WeatherForecastDto dto) {
        return ResponseEntity.ok(inquireService.getWeatherForecast(dto));
    }
}
