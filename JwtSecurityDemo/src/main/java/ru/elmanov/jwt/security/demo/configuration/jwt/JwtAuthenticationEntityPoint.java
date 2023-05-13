package ru.elmanov.jwt.security.demo.configuration.jwt;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@Component
@EnableWebSecurity
public class JwtAuthenticationEntityPoint implements AuthenticationEntryPoint {

    final static String ERROR_MESSAGE = "Unauthorized";

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
//        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        var writer = response.getWriter();
        writer.println(ERROR_MESSAGE);
        writer.flush();
    }
}
