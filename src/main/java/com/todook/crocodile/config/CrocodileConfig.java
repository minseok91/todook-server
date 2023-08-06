package com.todook.crocodile.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Getter
@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties
@ConfigurationProperties("crocodile")
public class CrocodileConfig {
    private final List<String> allowedReferers;
}
