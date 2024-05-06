package org.example.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // open-api 에러 코드
    APPLICATION_ERROR(HttpStatus.BAD_GATEWAY, "서버 어플리케이션 오류입니다."), // 01
    DB_ERROR(HttpStatus.BAD_GATEWAY, "데이터 베이스 오류입니다."), // 02
    NO_DATA_ERROR(HttpStatus.BAD_REQUEST, "표시할 데이터가 없습니다."), // 03
    SERVICE_TIME_OUT(HttpStatus.REQUEST_TIMEOUT, "서비스 연결에 실패했습니다."), // 05
    INVALID_REQUEST_PARAMETER_ERROR(HttpStatus.BAD_REQUEST, "잘못된 요청 값입니다."), // 10
    SERVICE_ACCESS_DENIED_ERROR(HttpStatus.FORBIDDEN, "서비스 접근이 거부되었습니다."), // 20
    DISABLE_SERVICE_KEY(HttpStatus.BAD_REQUEST, "일시적으로 사용할 수 없는 서비스 키 입니다."), // 21
    NOT_REGISTERED_SERVICE_KEY(HttpStatus.FORBIDDEN, "등록되지 않은 서비스 키 입니다."), // 30
    EXPIRED_KEY(HttpStatus.FORBIDDEN, "기한 만료된 서비스 키입니다."), // 31
    UNKNOWN_ERROR(HttpStatus.BAD_REQUEST, "알 수 없는 에러입니다. 잠시 후 다시 시도해주세요."), // 99

    // open-api 로직 에러
    API_ERROR(HttpStatus.BAD_REQUEST, "로직 수행 중 에러가 발생했습니다. API 키 오류일 수 있으니 다시 시도해주세요."),

    // 로컬 서버 DB 조회 No data
    NO_CONTENT_IN_DB(HttpStatus.NO_CONTENT, "서버 데이터베이스에 해당 날짜 조회 기록이 없습니다.")
    ;

    private final HttpStatus httpStatus;
    private final String description;
}
