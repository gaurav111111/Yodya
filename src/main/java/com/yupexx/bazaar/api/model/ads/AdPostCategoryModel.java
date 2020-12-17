package com.yupexx.bazaar.api.model.ads;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.yupexx.bazaar.api.model.CategoryProductBrandModel;
import com.yupexx.bazaar.api.model.CategoryProductTypeModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ad_post_cat_product")
public class AdPostCategoryModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@JsonIgnore
	private Integer adId;
	private Integer catProductTypeId;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="catProductTypeId",referencedColumnName="id",insertable = false,updatable = false)
	private CategoryProductTypeModel productType;
	
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
	public Integer getCatProductTypeId() {
		return catProductTypeId;
	}
	public void setCatProductTypeId(Integer catProductTypeId) {
		this.catProductTypeId = catProductTypeId;
	}
	public CategoryProductTypeModel getProductType() {
		return productType;
	}
	public void setProductType(CategoryProductTypeModel productType) {
		this.productType = productType;
	}
	
	
}
