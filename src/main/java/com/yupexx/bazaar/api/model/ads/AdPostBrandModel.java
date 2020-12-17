package com.yupexx.bazaar.api.model.ads;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.yupexx.bazaar.api.model.CategoryAccessoryModel;
import com.yupexx.bazaar.api.model.CategoryProductBrandModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ad_post_brand")
public class AdPostBrandModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@JsonIgnore
	private Integer adId;
	private Integer brandId;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="brandId",referencedColumnName="id",insertable = false,updatable = false)
	private CategoryProductBrandModel brand;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAdId() {
		return adId;
	}
	public void setAdId(Integer adId) {
		this.adId = adId;
	}
	public Integer getBrandId() {
		return brandId;
	}
	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}
	public CategoryProductBrandModel getBrand() {
		return brand;
	}
	public void setBrand(CategoryProductBrandModel brand) {
		this.brand = brand;
	}
	
	
}
