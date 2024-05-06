package org.example.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.dto.OpenApiRequestDto;
import org.example.entity.Weather;
import org.example.service.SyncService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SyncController {
    private final SyncService syncService;

    @PostMapping("/sync")
    public ResponseEntity<List<Weather>> saveWeather(@RequestBody @Valid OpenApiRequestDto requestDto) {
        return ResponseEntity.ok(syncService.saveWeather(requestDto));
    }
}
