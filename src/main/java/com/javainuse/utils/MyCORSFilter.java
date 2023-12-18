package com.javainuse.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.javainuse.model.PublicComplain;
import com.javainuse.model.PublicLogin;



@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MyCORSFilter implements Filter {

	@Autowired
	UserContextUsage contextUsage;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MyCORSFilter.class);

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	        throws IOException, ServletException {
	    HttpServletRequest requests = (HttpServletRequest) request;
	    HttpServletResponse responses = (HttpServletResponse) response;

	    // Get client's IP address
	    PublicLogin userDetails=contextUsage.getLoginUSER();
	    String userd=userDetails!=null? userDetails.getEmail()+"::userid:"+userDetails.getPublicid():"Publics";
	    String clientIP = getClientIP(requests);

	    LOGGER.info("Calling EndPoint: " + requests.getRequestURI() + " from IP: " + clientIP+" "+userd);

	    responses.setHeader("Access-Control-Allow-Origin", requests.getHeader("Origin"));
	    responses.setHeader("Access-Control-Allow-Credentials", "true");
	    responses.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
	    responses.setHeader("Access-Control-Max-Age", "3600");
	    responses.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With");

	    chain.doFilter(requests, responses);
	}

	private String getClientIP(HttpServletRequest request) {
	    String clientIP = request.getHeader("X-Forwarded-For");
	    if (clientIP == null || clientIP.isEmpty() || "unknown".equalsIgnoreCase(clientIP)) {
	        clientIP = request.getHeader("Proxy-Client-IP");
	    }
	    if (clientIP == null || clientIP.isEmpty() || "unknown".equalsIgnoreCase(clientIP)) {
	        clientIP = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if (clientIP == null || clientIP.isEmpty() || "unknown".equalsIgnoreCase(clientIP)) {
	        clientIP = request.getHeader("HTTP_X_FORWARDED_FOR");
	    }
	    if (clientIP == null || clientIP.isEmpty() || "unknown".equalsIgnoreCase(clientIP)) {
	        clientIP = request.getHeader("HTTP_X_FORWARDED");
	    }
	    if (clientIP == null || clientIP.isEmpty() || "unknown".equalsIgnoreCase(clientIP)) {
	        clientIP = request.getHeader("HTTP_X_CLUSTER_CLIENT_IP");
	    }
	    if (clientIP == null || clientIP.isEmpty() || "unknown".equalsIgnoreCase(clientIP)) {
	        clientIP = request.getHeader("HTTP_CLIENT_IP");
	    }
	    if (clientIP == null || clientIP.isEmpty() || "unknown".equalsIgnoreCase(clientIP)) {
	        clientIP = request.getHeader("HTTP_FORWARDED_FOR");
	    }
	    if (clientIP == null || clientIP.isEmpty() || "unknown".equalsIgnoreCase(clientIP)) {
	        clientIP = request.getHeader("HTTP_FORWARDED");
	    }
	    if (clientIP == null || clientIP.isEmpty() || "unknown".equalsIgnoreCase(clientIP)) {
	        clientIP = request.getHeader("REMOTE_ADDR");
	    }
	    if (clientIP == null || clientIP.isEmpty() || "unknown".equalsIgnoreCase(clientIP)) {
	        clientIP = request.getRemoteAddr();
	    }

	    return clientIP;
	}


	
}
