package com.todook.crocodile.handler;

import com.todook.crocodile.exception.NotAllowedRefererException;
import com.todook.crocodile.presentation.ApiResponse;
import com.todook.crocodile.presentation.ApiResponseStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

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

    @ExceptionHandler(NotAllowedRefererException.class)
    public ResponseEntity<Object> handleNotAllowedReferer(Exception e) {
        log.warn("[FORBIDDEN] {}", e.getMessage());

        final ApiResponse<Object> apiResponse = ApiResponse.builder()
                .status(ApiResponseStatus.FORBIDDEN)
                .build();
        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Object> handleNoHandlerFound(Exception e) {
        log.warn("[NOT_FOUND] {}", e.getMessage());

        final ApiResponse<Object> apiResponse = ApiResponse.builder()
                .status(ApiResponseStatus.NOT_FOUND)
                .build();

        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }
}
