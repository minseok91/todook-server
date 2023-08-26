package com.todook.crocodile.domain.auth.oauth2.dto.naver;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class NaverAccount {
    private String id;
    private String age;
    private String name;
    private String email;
    private String mobile;
    private String gender;
    private String nickname;
    private String birthday;
    private String birthyear;
    private String profileImage;
}
