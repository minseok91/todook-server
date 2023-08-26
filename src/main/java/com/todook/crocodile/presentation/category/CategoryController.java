package com.todook.crocodile.presentation.category;

import com.todook.crocodile.exception.BadRequestException;
import com.todook.crocodile.exception.NotFoundException;
import com.todook.crocodile.presentation.ApiResponse;
import com.todook.crocodile.presentation.document.ErrorResponse400_403_404_500;
import com.todook.crocodile.presentation.document.ErrorResponse400_404_500;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Api
@RequestMapping("/api")
@RestController
public class CategoryController {
    @GetMapping("/categories/{id}")
    @ErrorResponse400_404_500
    @ApiOperation(value = "카테고리 단건 조회")
    public ApiResponse<CategoryView> getCategory(@PathVariable Long id) {
        return ApiResponse.<CategoryView>builder()
                .data(CategoryView.builder()
                        .id(id)
                        .name("카테고리 이름")
                        .description("카테고리 설명")
                        .ownerUserId("blue")
                        .createdAt(LocalDateTime.of(2021, 9, 14, 15, 30))
                        .modifiedAt(LocalDateTime.of(2021, 9, 14, 15, 30))
                        .build())
                .build();
    }

    @GetMapping("/categories")
    @ErrorResponse400_404_500
    @ApiOperation(value = "내 카테고리 목록 조회")
    public ApiResponse<CategoryListView> getCategories(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "20") Integer pageSize) {
        List<CategoryView> categoryViews = new ArrayList<>();
        for (int i = 0; i < pageSize; i++) {
            categoryViews.add(CategoryView.builder()
                    .id((long)i)
                    .name("카테로기 이름" + (i + 1))
                    .description("카테고리 설명")
                    .ownerUserId("blue")
                    .createdAt(LocalDateTime.of(2021, 9, 14,15,30))
                    .modifiedAt(LocalDateTime.of(2021, 9, 14,15,30))
                    .build());
        }

        return ApiResponse.<CategoryListView>builder()
                .data(CategoryListView.builder()
                        .page(page)
                        .pageSize(10)
                        .totalCount(200)
                        .categories(categoryViews)
                        .build())
                .build();
    }

    @PostMapping("/categories")
    @ErrorResponse400_403_404_500
    @ApiOperation(value = "카테고리 등록")
    public ApiResponse<CategoryView> postCategory(@RequestBody CategoryRequest category) {
        return ApiResponse.<CategoryView>builder()
                .data(CategoryView.builder()
            .id(99L)
            .name(category.getName())
            .description(category.getDescription())
            .ownerUserId("blue")
            .createdAt(LocalDateTime.now())
            .modifiedAt(LocalDateTime.now())
            .build())
            .build();
    }

    @PostMapping("/categories/{id}")
    @ErrorResponse400_403_404_500
    @ApiOperation(value = "카테고리 수정")
    public ApiResponse<CategoryView> putCategory(@PathVariable Long id, @RequestBody CategoryRequest category) {
        if (id == null || id == 0) {
            throw new BadRequestException();
        }

        if (id == 78L) {
            throw new NotFoundException();
        }

        return ApiResponse.<CategoryView>builder()
                .data(CategoryView.builder()
                        .id(99L)
                        .name(category.getName())
                        .description(category.getDescription())
                        .ownerUserId("blue")
                        .createdAt(LocalDateTime.of(2021, 9, 14, 15, 30))
                        .modifiedAt(LocalDateTime.of(2021, 9, 14, 15, 30))
                        .build())
                .build();
    }


}
