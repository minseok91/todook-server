package com.todook.crocodile.domain.auth.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todook.crocodile.domain.auth.oauth2.dto.OAuth2Provider;
import com.todook.crocodile.domain.auth.user.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByAuthorizedByAndUserId(OAuth2Provider provider, String userId);
}
