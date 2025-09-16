package za.co.simplitate.springsecurity.exceptions;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;
import java.time.LocalDateTime;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    public static final String AUTHORISATION_FAILED = "Authorisation failed";

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                                    AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setHeader("eazybank-error-reason", AUTHORISATION_FAILED);
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().write(createJsonResponse(request, accessDeniedException));
    }

    private static String createJsonResponse(HttpServletRequest request, AccessDeniedException accessDeniedException) {
        return String.format("{\"timestamp\": \"%s\", \"status\": %d, \"error\": \"%s\", \"message\": \"%s\", \"path\": \"%s\"}",
                LocalDateTime.now(),
                HttpStatus.FORBIDDEN.value(),
                HttpStatus.FORBIDDEN.getReasonPhrase(),
                (accessDeniedException != null && accessDeniedException.getMessage() != null) ? accessDeniedException.getMessage(): AUTHORISATION_FAILED,
                request.getRequestURI());
    }
}
