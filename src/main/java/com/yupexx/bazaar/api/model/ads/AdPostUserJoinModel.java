package com.yupexx.bazaar.api.model.ads;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import com.yupexx.bazaar.api.model.CategoryModel;
import com.yupexx.bazaar.api.model.UserModel;
import com.yupexx.bazaar.api.model.view.AnalyticsViewModel;
import com.yupexx.bazaar.api.repository.AnalyticsViewRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "ad_post_user")
public class AdPostUserJoinModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer catId;
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
	@JsonIgnore
	@Column(columnDefinition = "TINYINT")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean status = true;
	@JsonIgnore
	private Long createdBy=(long) -1;
	@JsonIgnore
	private Long updatedBy=(long) -1;
	@JsonIgnore
	@CreationTimestamp
	private Timestamp createdDate;
	@UpdateTimestamp
	private Timestamp updatedDate;

	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="createdBy",referencedColumnName="id",insertable =  false, updatable = false)
	@JsonIgnoreProperties(value = { "password","signupSteps","dob","roleType","address1","address2","zipCode","country","city","state","sourceLogin","isEmailValid","isMobileValid","status","updatedDate","userRole" })
	private UserModel user;

	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="catId",referencedColumnName="id",insertable =  false, updatable = false)
	private CategoryModel category;

	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="adId",referencedColumnName="id",insertable =  false, updatable = false)
	private List<AdPostCategoryModel> productType;

	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="adId",referencedColumnName="id",insertable =  false, updatable = false)
	private List<AdPostBrandModel> brand;

	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="adId",referencedColumnName="id",insertable =  false, updatable = false)
	private List<AdPostAccessoryModel> accessory;

	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="adId",referencedColumnName="id",insertable =  false, updatable = false)
	private List<AdPostTagModel> tag;

	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="adId",referencedColumnName="id",insertable =  false, updatable = false)
	private List<AdPostMediaModel> media;

	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="adId",referencedColumnName="id",insertable =  false, updatable = false)
	private List<AdPostUserSubscriptionsModel> subscription;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="adId",referencedColumnName="id",insertable =  false, updatable = false)
	private List<AnalyticsViewModel> analytics;

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

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	public CategoryModel getCategory() {
		return category;
	}

	public void setCategory(CategoryModel category) {
		this.category = category;
	}

	public List<AdPostCategoryModel> getProductType() {
		return productType;
	}

	public void setProductType(List<AdPostCategoryModel> productType) {
		this.productType = productType;
	}

	public List<AdPostBrandModel> getBrand() {
		return brand;
	}

	public void setBrand(List<AdPostBrandModel> brand) {
		this.brand = brand;
	}

	public List<AdPostAccessoryModel> getAccessory() {
		return accessory;
	}

	public void setAccessory(List<AdPostAccessoryModel> accessory) {
		this.accessory = accessory;
	}

	public List<AdPostTagModel> getTag() {
		return tag;
	}


	public List<AdPostMediaModel> getMedia() {
		return media;
	}

	public void setMedia(List<AdPostMediaModel> media) {
		this.media = media;
	}

	public void setTag(List<AdPostTagModel> tag) {
		this.tag = tag;
	}

	public List<AdPostUserSubscriptionsModel> getSubscription() {
		return subscription;
	}

	public void setSubscription(List<AdPostUserSubscriptionsModel> subscription) {
		this.subscription = subscription;
	}

	public List<AnalyticsViewModel> getAnalytics() {
		return analytics;
	}

	public void setAnalytics(List<AnalyticsViewModel> analytics) {
		this.analytics = analytics;
	}
	

}
