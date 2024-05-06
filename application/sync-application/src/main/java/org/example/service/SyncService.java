package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.api.WeatherOpenApi;
import org.example.dto.OpenApiRequestDto;
import org.example.dto.OpenApiResponseDto;
import org.example.entity.Weather;
import org.example.repository.WeatherRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SyncService {
    private final WeatherOpenApi weatherOpenApi;
    private final WeatherRepository weatherRepository;

    @Transactional
    public List<Weather> saveWeather(OpenApiRequestDto requestDto) {
        List<OpenApiResponseDto> responseDtoList = weatherOpenApi.useOpenApi(requestDto);

        // 중복된 데이터 우선 제거
        deleteOldData(responseDtoList);
        List<Weather> weatherList = new ArrayList<>();

        for (OpenApiResponseDto dto : responseDtoList) {

            Weather weather = Weather.builder()
                    .baseDate(dto.getBaseDate())
                    .baseTime(dto.getBaseTime())
                    .category(dto.getCategory())
                    .forecastDate(dto.getFcstDate())
                    .forecastTime(dto.getFcstTime())
                    .forecastValue(dto.getFcstValue())
                    .nx(dto.getNx())
                    .ny(dto.getNy())
                    .build();

            weatherRepository.save(weather);
            weatherList.add(weather);
        }
        return weatherList;
    }
    private void deleteOldData(List<OpenApiResponseDto> dtoList) {
        for (OpenApiResponseDto dto : dtoList) {
            if (existData(dto)) {
                weatherRepository.deleteAllByForecastDateAndForecastTime(dto.getFcstDate(), dto.getFcstTime());
            }
        }
    }

    private boolean existData(OpenApiResponseDto dto) {
        return weatherRepository.existsByForecastDateAndForecastTime(dto.getFcstDate(), dto.getFcstTime());
    }
}