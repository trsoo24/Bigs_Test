package org.example.repository;

import org.example.entity.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WeatherRepository extends JpaRepository<Weather, Long> {
    List<Weather> findAllByForecastDateAndNxAndNy(String forecastDate, Integer nx, Integer ny);
    boolean existsByForecastDateAndForecastTime(String forecastDate, String forecastTime);
    @Modifying
    @Query("delete from Weather w where w.forecastDate = :forecastDate and w.forecastTime = :forecastTime")
    void deleteAllByForecastDateAndForecastTime(@Param("forecastDate") String forecastDate,
                                                @Param("forecastTime") String forecastTime);
}
