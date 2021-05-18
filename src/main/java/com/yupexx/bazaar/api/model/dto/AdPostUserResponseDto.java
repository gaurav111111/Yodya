package com.yupexx.bazaar.api.model.dto;

import java.sql.Timestamp;
import java.util.List;

import com.yupexx.bazaar.api.model.ads.AdPostMediaModel;



public class AdPostUserResponseDto {

	private Integer id;
	private Integer catId;
	private Integer catMediaId;
	
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

	private Boolean status=true;
	private Long createdBy=(long)1;
	private Long updatedBy=(long)1;
	
	private Timestamp createdDate;
	
	private Timestamp updatedDate;
	
	List<BazaarLabelDto> labeList;
	
	List<AdPostMediaModel> media;
	

	public List<AdPostMediaModel> getMedia() {
		return media;
	}

	public void setMedia(List<AdPostMediaModel> media) {
		this.media = media;
	}

	public List<BazaarLabelDto> getLabeList() {
		return labeList;
	}

	public void setLabeList(List<BazaarLabelDto> labeList) {
		this.labeList = labeList;
	}

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

	public Integer getCatMediaId() {
		return catMediaId;
	}

	public void setCatMediaId(Integer catMediaId) {
		this.catMediaId = catMediaId;
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

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
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
	
	
}
