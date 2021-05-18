package com.yupexx.bazaar.api.model.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

public class CategoryDetailsDTO {
	
	
	private Integer id;
	private Integer labelId;
	private List<String> value;
	
	public Integer getLabelId() {
		return labelId;
	}
	public void setLabelId(Integer labelId) {
		this.labelId = labelId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public List<String> getValue() {
		return value;
	}
	public void setValue(List<String> value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "CategoryDetailsDTO [id=" + id + ", value=" + value + ", getId()=" + getId() + ", getValue()="
				+ getValue() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
	

}
