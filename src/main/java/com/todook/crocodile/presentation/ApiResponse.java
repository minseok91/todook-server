package com.todook.crocodile.presentation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class ApiResponse<T> {
    @Builder.Default
    private ApiResponseStatus status = ApiResponseStatus.SUCCESS;
    private final T data;

    public String getMessage() {
        return status.getMessage();
    }

    @JsonIgnore
    public HttpStatus getHttpStatus() {
        return status.getHttpStatus();
    }
}
