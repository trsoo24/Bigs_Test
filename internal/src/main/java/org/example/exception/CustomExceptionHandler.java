package org.example.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity<CustomExceptionResponse> handleCustomException(CustomException e) {
        return CustomExceptionResponse.responseExceptionEntity(e);
    }

    // 유효성 검사
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> handleValidException(MethodArgumentNotValidException exception) {
        List<String> errors = new ArrayList<>();
        for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
            StringBuilder builder = new StringBuilder();
            builder.append(fieldError.getField());
            builder.append(" (은)는 ");
            builder.append(fieldError.getDefaultMessage());
            builder.append(" 입력된 값: ");
            builder.append(fieldError.getRejectedValue());
            errors.add(builder.toString());
        }

        return ResponseEntity.status(400).body(errors);
    }
}
