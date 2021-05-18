package com.yupexx.services.api.model.dto;

public class ServiceCategoryLabelDTO {
	
	private Integer id;
	private String label;
	@Override
	public String toString() {
		return "ServiceCategoryLabelDTO [id=" + id + ", label=" + label + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
	
	

}
