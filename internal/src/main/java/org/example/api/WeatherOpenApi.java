package org.example.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.OpenApiRequestDto;
import org.example.dto.OpenApiResponseDto;
import org.example.exception.CustomException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.example.exception.ErrorCode.*;

@Service
public class WeatherOpenApi {
    @Value("${data.api.key}")
    private String apiKey;
    @Value("${api.end.point}")
    private String endPoint;
    private static final String dataType = "JSON"; // 받는 결과값 JSON 고정

    public List<OpenApiResponseDto> useOpenApi(OpenApiRequestDto requestDto) {
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();

        String response = restTemplate.getForObject(generateUrl(requestDto), String.class);

        try {
            JsonNode root = objectMapper.readTree(response);
            JsonNode header = root.path("response").path("header");

            // 정상 처리됐는지 확인
            verifySuccess(header);

            List<OpenApiResponseDto> responseDtoList = new ArrayList<>();
            JsonNode itemList = root.path("response").path("body").path("items").path("item");

            for (JsonNode item : itemList) {
                responseDtoList.add(objectMapper.readValue(item.toString(), OpenApiResponseDto.class));
            }
            return responseDtoList;
        } catch (IOException e) {
            throw new CustomException(API_ERROR);
        }
    }

    String generateUrl(OpenApiRequestDto requestDto) { // API URL 생성
        UriComponents uriComponents = UriComponentsBuilder.
                fromHttpUrl(endPoint)
                .queryParam("serviceKey", apiKey)
                .queryParam("pageNo", requestDto.getPageNo())
                .queryParam("numOfRows", requestDto.getNumOfRows())
                .queryParam("dataType", dataType)
                .queryParam("base_date", requestDto.getBaseDate())
                .queryParam("base_time", requestDto.getBaseTime())
                .queryParam("nx", requestDto.getNx())
                .queryParam("ny", requestDto.getNy())
                .build();

        return String.valueOf(uriComponents);
    }

    private void verifySuccess (JsonNode header) { // 예외 처리
        String resultCode = header.path("resultCode").asText();
        if (!resultCode.equals("00")) {
            switch (resultCode) {
                case "01" : throw new CustomException(APPLICATION_ERROR);
                case "02" : throw new CustomException(DB_ERROR);
                case "03" : throw new CustomException(NO_DATA_ERROR);
                case "05" : throw new CustomException(SERVICE_TIME_OUT);
                case "10" : throw new CustomException(INVALID_REQUEST_PARAMETER_ERROR);
                case "20" : throw new CustomException(SERVICE_ACCESS_DENIED_ERROR);
                case "21" : throw new CustomException(DISABLE_SERVICE_KEY);
                case "30" : throw new CustomException(NOT_REGISTERED_SERVICE_KEY);
                case "31" : throw new CustomException(EXPIRED_KEY);
                default : throw new CustomException(UNKNOWN_ERROR);
            }
        }
    }
}