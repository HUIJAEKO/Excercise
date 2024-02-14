package com.example.excercise.exception;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String errorMessage = "아이디나 비밀번호가 일치하지 않습니다";

        setDefaultFailureUrl("/login?error=true&message=" + java.net.URLEncoder.encode(errorMessage, "UTF-8"));

        super.onAuthenticationFailure(request, response, exception);
    }
}
