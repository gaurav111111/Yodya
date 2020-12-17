package com.yupexx.services.api.model.business;

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

import com.yupexx.bazaar.api.model.UserModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yupexx.services.api.model.BusinessCategoryModel;

@Entity
@Table(name = "business_master")
public class BusinessJoinModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer catId;
	private String businessName;
	private String aboutBusiness;
	private String bannerImage;
	private String country;
	private String pinCode;
	private String state;
	private String city;
	private String addressLine1;
	private String addressLine2;
	private String website;
	private String contact1;
	private String contact2;
	private String whatsapp;
	private String wechat;
	private String telegram;
	private String skype;
	private String facebook;
	private String linkedIn;
	private String twitter;
	private String email;
	@Column(columnDefinition = "TINYINT")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean isVerified=false;
	@JsonIgnore
	@Column(columnDefinition = "TINYINT")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean status=false;
	@JsonIgnore
	private Long createdBy=(long)1;
	@JsonIgnore
	private Long updatedBy=(long)1;
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
	private BusinessCategoryModel category;

	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="businessId",referencedColumnName="id",insertable =  false, updatable = false)
	private List<BusinessFacilityModel> facility;

	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="businessId",referencedColumnName="id",insertable =  false, updatable = false)
	private List<BusinessMediaModel> media;

//	@OneToMany(cascade=CascadeType.ALL)
//	@JoinColumn(name="businessId",referencedColumnName="id",insertable =  false, updatable = false)
//	private List<BusinessReportModel> report;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="businessId",referencedColumnName="id",insertable =  false, updatable = false)
	private List<BusinessReviewJoinModel> review;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="businessId",referencedColumnName="id",insertable =  false, updatable = false)
	private List<BusinessAnalyticsModel> analytics;

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

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getAboutBusiness() {
		return aboutBusiness;
	}

	public void setAboutBusiness(String aboutBusiness) {
		this.aboutBusiness = aboutBusiness;
	}

	public String getBannerImage() {
		return bannerImage;
	}

	public void setBannerImage(String bannerImage) {
		this.bannerImage = bannerImage;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getContact1() {
		return contact1;
	}

	public void setContact1(String contact1) {
		this.contact1 = contact1;
	}

	public String getContact2() {
		return contact2;
	}

	public void setContact2(String contact2) {
		this.contact2 = contact2;
	}

	public String getWhatsapp() {
		return whatsapp;
	}

	public void setWhatsapp(String whatsapp) {
		this.whatsapp = whatsapp;
	}

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public String getTelegram() {
		return telegram;
	}

	public void setTelegram(String telegram) {
		this.telegram = telegram;
	}

	public String getSkype() {
		return skype;
	}

	public void setSkype(String skype) {
		this.skype = skype;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getLinkedIn() {
		return linkedIn;
	}

	public void setLinkedIn(String linkedIn) {
		this.linkedIn = linkedIn;
	}

	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getIsVerified() {
		return isVerified;
	}

	public void setIsVerified(Boolean isVerified) {
		this.isVerified = isVerified;
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

	public BusinessCategoryModel getCategory() {
		return category;
	}

	public void setCategory(BusinessCategoryModel category) {
		this.category = category;
	}

	public List<BusinessFacilityModel> getFacility() {
		return facility;
	}

	public void setFacility(List<BusinessFacilityModel> facility) {
		this.facility = facility;
	}

	public List<BusinessMediaModel> getMedia() {
		return media;
	}

	public void setMedia(List<BusinessMediaModel> media) {
		this.media = media;
	}

//	public List<BusinessReportModel> getReport() {
//		return report;
//	}
//
//	public void setReport(List<BusinessReportModel> report) {
//		this.report = report;
//	}

	public List<BusinessReviewJoinModel> getReview() {
		return review;
	}

	public void setReview(List<BusinessReviewJoinModel> review) {
		this.review = review;
	}

	public List<BusinessAnalyticsModel> getAnalytics() {
		return analytics;
	}

	public void setAnalytics(List<BusinessAnalyticsModel> analytics) {
		this.analytics = analytics;
	}

}
