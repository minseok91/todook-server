package com.todook.crocodile.interceptor;

import com.todook.crocodile.config.CrocodileConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


import javax.servlet.http.Cookie;
import com.todook.crocodile.domain.auth.token.UserTokenService;
import com.todook.crocodile.domain.auth.user.dto.User;

@RequiredArgsConstructor
public class RefererCheckInterceptor implements HandlerInterceptor {
    private final CrocodileConfig crocodileConfig;
    private final UserTokenService userTokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
//		if(!isAllowedReferer(request)) {
//			throw new NotAllowedRefererException(request.getHeader("referer"));
//		}

        readUtkn(request);

        return true;
    }

    private boolean isAllowedReferer(HttpServletRequest request) {
        final List<String> allowedReferers = crocodileConfig.getAllowedReferers();
        if (CollectionUtils.isEmpty(allowedReferers)) {
            return true;
        }

        final String referer = request.getHeader("referer");
        if (!StringUtils.hasText(referer)) {
            return false;
        }

        return isCallBySwagger(referer) || allowedReferers.stream().anyMatch(referer::startsWith);
    }

    private boolean isCallBySwagger(String referer) {
        return referer.contains("/swagger-ui/index.html");
    }

    private void readUtkn(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length == 0) {
            return;
        }

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("utkn")) {
                String utkn = cookie.getValue();
                if (StringUtils.hasLength(utkn)) {
                    final User userDto = userTokenService.convertUtkn(utkn);
                    request.setAttribute("user", userDto);
                    return;
                }
            }
        }
    }
}
