package com.todook.crocodile.domain.auth.user.dto;

import com.todook.crocodile.domain.auth.oauth2.dto.OAuth2Provider;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String userId;
    private OAuth2Provider authorizedBy;
    private String nickname;
    private String profileImageUrl;
    private String profileThumbnailUrl;
}

