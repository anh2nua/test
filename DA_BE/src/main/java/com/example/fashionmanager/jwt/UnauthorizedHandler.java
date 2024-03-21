package com.example.fashionmanager.jwt;

import com.example.fashionmanager.exception.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
@Log4j2
@AllArgsConstructor
public class UnauthorizedHandler implements AuthenticationEntryPoint {

    private ObjectMapper objectMapper;
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException e)
            throws IOException {
        ErrorResponse errorResponse = ErrorResponse
                .builder()
                .status(HttpStatus.UNAUTHORIZED)
                .message(e.getMessage())
                .build();

        String errorResponseString = objectMapper.writeValueAsString(errorResponse);

        response.addHeader("Access-Control-Allow-Headers","Origin, X-Requested-With, Content-Type, Accept");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(errorResponseString);
    }
}
