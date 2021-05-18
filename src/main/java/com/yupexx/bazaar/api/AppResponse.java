package com.yupexx.bazaar.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.core.MethodParameter;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.yupexx.bazaar.api.config.JwtRequestFilter;
import com.yupexx.bazaar.api.model.dto.RestResponseDTO;
import com.yupexx.bazaar.api.model.response.Message;

@ControllerAdvice
public class AppResponse implements ResponseBodyAdvice<Object> {
	
	public static String ERROR_MESSAGE = null;
	public static String MESSAGE = null;

	@Autowired
	private Environment env;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JwtRequestFilter.class);
	
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
            Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
        ServerHttpResponse response) {
    	String URI = String.valueOf(request.getURI());
    	if(URI.contains(env.getProperty("springdoc.api-docs.path"))) {
    		return body;
    	}
    	
    	 // TODO Auto-generated method stub
        final RestResponseDTO<Object> output = new RestResponseDTO<>();
        int code = HttpStatus.OK.value();
    	String status = "success";
        if (response instanceof ServletServerHttpResponse) {
        	code = ((ServletServerHttpResponse) response).getServletResponse().getStatus();
        	
        	System.out.println("code"+code);
        	System.out.println("HttpStatus.OK.value()"+HttpStatus.OK.value());
        	if(code!=HttpStatus.OK.value()) {
        		status = "failure";
        		output.setStatus(status);
        		if(ERROR_MESSAGE!=null) {
        			output.setErrorMessage(ERROR_MESSAGE);
        			LOGGER.debug("Setting Error message from filer and adding method body");
        			output.setData(body);
        		}else {
        			output.setErrorMessage(body);
        			System.out.println("Setting error body");
        			LOGGER.debug("No Error message sent from filer and adding method body");
        			output.setData(body);
        		}
        	}else {
        		
        		output.setData(body);
        	}
        }
        output.setCode(code);
        return output;
    }
}