package com.yupexx.bazaar.api.model.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AdPostFilterDTO {

	@NotNull(message = "Category can not be null!")
	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	private List<Integer> catId;

	public List<Integer> getCatId() {
		return catId;
	}

	public void setCatId(List<Integer> catId) {
		this.catId = catId;
	}
	
}
