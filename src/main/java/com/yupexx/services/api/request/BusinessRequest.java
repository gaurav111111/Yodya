package com.yupexx.services.api.request;

import java.util.List;

import javax.validation.constraints.NotNull;

public class BusinessRequest {
	@NotNull(message = "Category can not be null!")	
	private Integer catId;
	@NotNull(message = "Business Name can not be null!")
	private String businessName;
	@NotNull(message = "About Business can not be null!")
	private String aboutBusiness;
	@NotNull(message = "Banner Image can not be null!")
	private String bannerImage;
	@NotNull(message = "Country can not be null!")
	private String country;
	@NotNull(message = "PinCode can not be null!")
	private String pinCode;
	@NotNull(message = "State can not be null!")
	private String state;
	@NotNull(message = "City can not be null!")
	private String city;
	@NotNull(message = "Address Line 1 can not be null!")
	private String addressLine1;

	private String addressLine2;

	private String website;
	@NotNull(message = "Contact 1 can not be null!")
	private String contact1;

	private String contact2;
	@NotNull(message = "Whatsapp can not be null!")
	private String whatsapp;

	private String wechat;

	private String telegram;

	private String skype;

	private String facebook;

	private String linkedIn;

	private String twitter;
	@NotNull(message = "Email can not be null!")
	private String email;
	
	private List<Integer> facility;
	
	@NotNull(message = "Business Media can not be null!")
	private List<String> media;
	
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
	public List<Integer> getFacility() {
		return facility;
	}
	public void setFacility(List<Integer> facility) {
		this.facility = facility;
	}
	public List<String> getMedia() {
		return media;
	}
	public void setMedia(List<String> media) {
		this.media = media;
	}
	
}
