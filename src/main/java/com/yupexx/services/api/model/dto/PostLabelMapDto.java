package com.yupexx.services.api.model.dto;

import java.util.List;

public class PostLabelMapDto {
	
	private int labelId;
	private List<Integer> catDetailsId;
	public int getLabelId() {
		return labelId;
	}
	public void setLabelId(int labelId) {
		this.labelId = labelId;
	}
	public List<Integer> getCatDetailsId() {
		return catDetailsId;
	}
	public void setCatDetailsId(List<Integer> catDetailsId) {
		this.catDetailsId = catDetailsId;
	}
}
