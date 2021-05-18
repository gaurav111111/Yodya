package com.yupexx.bazaar.api.model.dto;

public class AddPostDTO {

	private Integer id;
	private String expiryDate;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	@Override
	public String toString() {
		return "AddPostDTO [id=" + id + ", expiryDate=" + expiryDate + "]";
	}
	
}
