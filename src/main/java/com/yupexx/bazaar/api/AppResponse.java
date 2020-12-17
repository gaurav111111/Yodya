package com.yupexx.bazaar.api;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.yupexx.bazaar.api.model.dto.RestResponseDTO;

@ControllerAdvice
public class AppResponse implements ResponseBodyAdvice<Object> {
	
	public static String ERROR_MESSAGE = null;
	public static String MESSAGE = null;

	@Autowired
	private Environment env;
	
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
        	if(code!=HttpStatus.OK.value()) {
        		status = "failure";
        		if(ERROR_MESSAGE!=null) {
        			output.setErrorMessage(ERROR_MESSAGE);
        		}else {
        			output.setErrorMessage(body);
        		}
        		
        	}else {
        		output.setData(body);
        	}
        }
       
        output.setCode(code);
        output.setStatus(status);
        return output;
    }
}