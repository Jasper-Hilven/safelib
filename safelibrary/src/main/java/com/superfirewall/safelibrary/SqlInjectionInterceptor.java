package com.superfirewall.safelibrary;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class SqlInjectionInterceptor implements HandlerInterceptor {

    private static final Pattern PATH_VARIABLE_PATTERN = Pattern.compile("/user/(.*)");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            String requestUri = request.getRequestURI();
            Matcher matcher = PATH_VARIABLE_PATTERN.matcher(requestUri);
            if (matcher.matches()) {
                String id = matcher.group(1);
                if (id != null && id.contains("OR%201=1")) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid parameter");
                    return false;
                }
            }
        }
        return true;
    }
}
