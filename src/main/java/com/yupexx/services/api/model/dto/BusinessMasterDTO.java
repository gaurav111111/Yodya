package com.yupexx.services.api.model.dto;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yupexx.services.api.model.BusinessDetailMapping;
import com.yupexx.services.api.model.business.BusinessMediaModel;
import com.yupexx.services.api.model.business.BusinessReviewJoinModel;

public class BusinessMasterDTO {

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
	private Boolean isVerified=false;
	private Boolean status=true;
	private Long createdBy=(long)1;
	private Long updatedBy=(long)1;
	private Timestamp createdDate;
	private Timestamp updatedDate;
	private List<BusinessMediaModel> media;
	private List<BusinessMappingDTO> mapping;
	private List<BusinessCatLabelDTO> label;
	
	public List<BusinessCatLabelDTO> getLabel() {
		return label;
	}
	public void setLabel(List<BusinessCatLabelDTO> label) {
		this.label = label;
	}
	List<BusinessReviewJoinModel> review;
	
	
	public List<BusinessReviewJoinModel> getReview() {
		return review;
	}
	public void setReview(List<BusinessReviewJoinModel> review) {
		this.review = review;
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
	public List<BusinessMediaModel> getMedia() {
		return media;
	}
	public void setMedia(List<BusinessMediaModel> media) {
		this.media = media;
	}
	public List<BusinessMappingDTO> getMapping() {
		return mapping;
	}
	public void setMapping(List<BusinessMappingDTO> mapping) {
		this.mapping = mapping;
	}
		
}


