package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.WeatherForecastDto;
import org.example.entity.Weather;
import org.example.exception.CustomException;
import org.example.repository.WeatherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.example.exception.ErrorCode.NO_CONTENT_IN_DB;

@Service
@RequiredArgsConstructor
public class InquireService {
    private final WeatherRepository weatherRepository;

    public List<Weather> getWeatherForecast(WeatherForecastDto dto) {
        List<Weather> weatherList = weatherRepository.findAllByForecastDateAndNxAndNy
                (dto.getForecastDate(), dto.getNx(), dto.getNy());

        if (weatherList.isEmpty()) {
            throw new CustomException(NO_CONTENT_IN_DB);
        }

        return weatherList;
    }
}
