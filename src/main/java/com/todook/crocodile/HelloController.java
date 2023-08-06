package com.todook.crocodile;

import com.todook.crocodile.presentation.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HelloController {
    @GetMapping("/hello")
    public ApiResponse<String> hello() {
        return ApiResponse.<String>builder()
                .data("hello")
                .build();
    }

    @GetMapping("/hello/error")
    public ApiResponse<String> helloError() throws Exception {
        throw new Exception("임시로 만든 에러");
    }
}
