package com.todook.crocodile;

import com.todook.crocodile.domain.Hello;
import com.todook.crocodile.infrastructure.HelloMapper;
import com.todook.crocodile.presentation.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HelloController {
    private final HelloMapper helloMapper;

    @GetMapping("/hello")
    @ResponseBody
    public ApiResponse<List<Hello>> hello() {
        return ApiResponse.<List<Hello>>builder()
                .data(helloMapper.findAll())
                .build();
    }

    @GetMapping("/hello/error")
    @ResponseBody
    public ApiResponse<String> helloError() throws Exception {
        throw new Exception("임시로 만든 에러");
    }
}
