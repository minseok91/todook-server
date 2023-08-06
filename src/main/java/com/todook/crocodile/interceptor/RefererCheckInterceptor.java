package com.todook.crocodile.interceptor;

import com.todook.crocodile.config.CrocodileConfig;
import com.todook.crocodile.exception.NotAllowedRefererException;
import lombok.RequiredArgsConstructor;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RequiredArgsConstructor
public class RefererCheckInterceptor implements HandlerInterceptor {
    private final CrocodileConfig crocodileConfig;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if(!isAllowedReferer(request)) {
            throw new NotAllowedRefererException(request.getHeader("referer"));
        }
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

    private boolean isCallBySwagger(String referer) { return referer.contains("/swagger-ui/index.html");}
}
