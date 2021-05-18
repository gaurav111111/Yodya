package com.yupexx.bazaar.api.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.UrlPathHelper;

import com.yupexx.bazaar.api.AppResponse;
import com.yupexx.bazaar.api.service.JwtUserDetailsService;
import com.yupexx.bazaar.console.cron.BazaarConsole;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JwtRequestFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException,ExpiredJwtException {
	
		
		  
		  String path = request.getRequestURI().substring(request.getContextPath().length());
		  //value
		  System.out.println("path"+path);
		  String value ="/bazaar/authenticate/frontend";
		  String value3 ="/bazaar/authenticate/backend";
		  String value1 ="/bazaar/social/google/login";
		  String value2 ="/bazaar/register";
		response.setHeader("Access-Control-Allow-Origin", "https://www.yoyda.com");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, PUT, PATCH, GET, OPTIONS, DELETE, HEAD");
		response.setHeader("Access-Control-Max-Age", "360000");
		response.setHeader("Access-Control-Allow-Headers", "*");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
			response.setStatus(HttpServletResponse.SC_OK);
			return ;
		}
		
		
		
		if(path.equalsIgnoreCase(value) ||  path.equalsIgnoreCase(value3) || path.equalsIgnoreCase(value1) || path.equalsIgnoreCase(value2))
		{
			System.out.println("we are going outside");
			LOGGER.debug("this is path"+path);
		}
		else {
			System.out.println("we are going insde");
		final String requestTokenHeader = request.getHeader("Authorization");
		
		System.out.println("requestTokenHeader"+requestTokenHeader);
		String username = null;
		String jwtToken = null;
		// JWT Token is in the form "Bearer token". Remove Bearer word and get only the Token
//		if (requestTokenHeader != null && requestTokenHeader !="" && requestTokenHeader.startsWith("Bearer")) 
		if (requestTokenHeader != null  && requestTokenHeader !="" && requestTokenHeader.startsWith("Bearer")) {
			jwtToken = requestTokenHeader.substring(7);
			try {
				username = jwtTokenUtil.getUsernameFromToken(jwtToken);
				System.out.println(username+"from token");
			} catch (IllegalArgumentException e) {
				System.out.println("Unable to get JWT Token");
				AppResponse.ERROR_MESSAGE = "Unable to get JWT Token";
				throw new ExpiredJwtException(null, null, AppResponse.ERROR_MESSAGE);
			} catch (ExpiredJwtException e) {
				System.out.println("JWT Token has expired");
				//response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token expired (" + e.getMessage() + ")");
				//response.setStatus(440);
				AppResponse.ERROR_MESSAGE = "JWT Token has expired";
				LOGGER.error("Error in API Response setting JWT Token has expired Error");
				throw new ExpiredJwtException(null, null, AppResponse.ERROR_MESSAGE);
			}
		} else {
			logger.warn("JWT Token does not begin with Bearer String");
		}
		//Once we get the token validate it.
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            System.out.println("username:-"+username);
			UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);
			System.out.println("userDetails:-"+userDetails.toString());
			// if token is valid configure Spring Security to manually set authentication
			if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				// After setting the Authentication in the context, we specify
				// that the current user is authenticated. So it passes the Spring Security Configurations successfully.
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		
	}
		chain.doFilter(request, response);
	}

}
