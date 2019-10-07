package com.sentinel;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class CorsAwareAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    static final String ORIGIN = "http://localhost:4200/";

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response){
        if (request.getHeader(ORIGIN) != null) {
            String origin = request.getHeader(ORIGIN);
            response.addHeader("Access-Control-Allow-Origin", "*");
            response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
            response.addHeader("Access-Control-Allow-Credentials", "true");
            response.addHeader("Access-Control-Allow-Headers",
                    request.getHeader("Access-Control-Request-Headers"));
        }
        
        if (request.getMethod() == "OPTIONS") {
            try {
				response.getWriter().println("OK");
				response.getWriter().flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            
        }
        return super.attemptAuthentication(request, response);
    }
}