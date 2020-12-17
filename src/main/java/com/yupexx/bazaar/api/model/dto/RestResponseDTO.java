package com.yupexx.bazaar.api.model.dto;


public class RestResponseDTO<T> {

    private String status = "success";
    private int code;
    private T errorMessage = null;
    private String message = null;

    private T data;
   
    
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public T getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(T errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	
    
}