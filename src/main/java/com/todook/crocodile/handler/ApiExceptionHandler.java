package com.todook.crocodile.handler;

import com.todook.crocodile.presentation.ApiResponse;
import com.todook.crocodile.presentation.ApiResponseStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handlerServerError(Exception e) {
        log.error("[SERVER_ERROR] {}", e.getMessage(), e);

        final ApiResponse<Object> apiResponse = ApiResponse.builder()
                .status(ApiResponseStatus.INTERNAL_SERVER_ERROR)
                .build();

        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }
}
