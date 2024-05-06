package org.example.exception;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@Builder
@RequiredArgsConstructor
public class CustomExceptionResponse {
    private final HttpStatus status;
    private final String code;
    private final String message;

    public static ResponseEntity<CustomExceptionResponse> responseExceptionEntity(CustomException e) {
        return ResponseEntity.status(e.getErrorCode().getHttpStatus())
                .body(CustomExceptionResponse.builder()
                        .status(e.getErrorCode().getHttpStatus())
                        .code(e.getErrorCode().name())
                        .message(e.getErrorCode().getDescription())
                        .build());
    }
}
