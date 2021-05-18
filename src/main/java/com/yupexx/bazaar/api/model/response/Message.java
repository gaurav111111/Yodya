package com.yupexx.bazaar.api.model.response;

public class Message {
	private int code;

	private String message;

	private String description;
	
	private Object contentList;
	private String content;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Object getContentList() {
		return contentList;
	}
	public void setContentList(Object contentList) {
		this.contentList = contentList;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
