package com.example.excercise.exception;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
       
    	String errorCode = "unknownError";
    	//BadCredentialsException이 뜨면?
    	if(exception instanceof BadCredentialsException){
    		 errorCode = "badCredentials";
    	}
    	
    	HttpSession session = request.getSession(true);
    	
    	session.setAttribute("loginMessage", errorCode);
    	session.setAttribute("messageType", "error");
    	
    	getRedirectStrategy().sendRedirect(request, response, "/user/login?error=true");
    	
    }
}
