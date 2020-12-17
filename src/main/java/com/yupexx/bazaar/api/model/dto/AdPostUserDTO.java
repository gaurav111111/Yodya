package com.yupexx.bazaar.api.model.dto;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AdPostUserDTO {


	private Integer id;
	private Integer catId;
	private Integer adSubscriptionId;
	@NotNull(message = "Category can not be null!")
	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	private List<Integer> catProductTypeId;
	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	private List<String> catMediaId;
	@NotNull(message = "Brand can not be null!")
	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	private List<Integer> brandId;
	@NotNull(message = "Accessory can not be null!")
	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	private List<Integer> accessoryId;
	@NotNull(message = "Tag can not be null!")
	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	private List<Integer> tagId;
	private String adTitle;
	private String adDescription;	
	private float adPrice;
	private String adCurrency;
	private String location;
	private String latitude;
	private String longitude;
	private Integer productCondition;
	private Integer manufacturedYear;
	private String productModelName;
	
	private Integer termsC;
	private Integer cdetails;
	private Integer pmail;
	
	@CreationTimestamp
	private Timestamp createdDate;
	@UpdateTimestamp
	private Timestamp updatedDate;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCatId() {
		return catId;
	}
	public void setCatId(Integer catId) {
		this.catId = catId;
	}
	public Integer getAdSubscriptionId() {
		return adSubscriptionId;
	}
	public void setAdSubscriptionId(Integer adSubscriptionId) {
		this.adSubscriptionId = adSubscriptionId;
	}
	public List<Integer> getCatProductTypeId() {
		return catProductTypeId;
	}
	public void setCatProductTypeId(List<Integer> catProductTypeId) {
		this.catProductTypeId = catProductTypeId;
	}
	public List<String> getCatMediaId() {
		return catMediaId;
	}
	public void setCatMediaId(List<String> catMediaId) {
		this.catMediaId = catMediaId;
	}
	public List<Integer> getBrandId() {
		return brandId;
	}
	public void setBrandId(List<Integer> brandId) {
		this.brandId = brandId;
	}
	public List<Integer> getAccessoryId() {
		return accessoryId;
	}
	public void setAccessoryId(List<Integer> accessoryId) {
		this.accessoryId = accessoryId;
	}
	public List<Integer> getTagId() {
		return tagId;
	}
	public void setTagId(List<Integer> tagId) {
		this.tagId = tagId;
	}
	public String getAdTitle() {
		return adTitle;
	}
	public void setAdTitle(String adTitle) {
		this.adTitle = adTitle;
	}
	public String getAdDescription() {
		return adDescription;
	}
	public void setAdDescription(String adDescription) {
		this.adDescription = adDescription;
	}
	public float getAdPrice() {
		return adPrice;
	}
	public void setAdPrice(float adPrice) {
		this.adPrice = adPrice;
	}
	public String getAdCurrency() {
		return adCurrency;
	}
	public void setAdCurrency(String adCurrency) {
		this.adCurrency = adCurrency;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public Integer getProductCondition() {
		return productCondition;
	}
	public void setProductCondition(Integer productCondition) {
		this.productCondition = productCondition;
	}
	public Integer getManufacturedYear() {
		return manufacturedYear;
	}
	public void setManufacturedYear(Integer manufacturedYear) {
		this.manufacturedYear = manufacturedYear;
	}
	public String getProductModelName() {
		return productModelName;
	}
	public void setProductModelName(String productModelName) {
		this.productModelName = productModelName;
	}
	public Timestamp getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	public Timestamp getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}
	public Integer getTermsC() {
		return termsC;
	}
	public void setTermsC(Integer termsC) {
		this.termsC = termsC;
	}
	public Integer getCdetails() {
		return cdetails;
	}
	public void setCdetails(Integer cdetails) {
		this.cdetails = cdetails;
	}
	public Integer getPmail() {
		return pmail;
	}
	public void setPmail(Integer pmail) {
		this.pmail = pmail;
	}
	

}
