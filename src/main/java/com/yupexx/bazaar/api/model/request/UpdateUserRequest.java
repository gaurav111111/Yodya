package com.yupexx.bazaar.api.model.request;

import org.springframework.beans.factory.annotation.Value;

public class UpdateUserRequest {
	private long id =0;
	private Boolean status= true;
	private String profilePic="-";
	private String fullName="-";
	private String email="-";
	private String gender="-";
	private String dob="-";
	private String countryCode="-";
	private Long phoneNumber=(long)0;
	private String address1;
	private String address2;
	private String zipCode;
	private String country;
	private String city;
	private String state;
	private Integer signupSteps;
	private String fbId="-";
	private String gId="-";
	private String roleType="-";

	
	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}



	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getFbId() {
		return fbId;
	}

	public void setFbId(String fbId) {
		this.fbId = fbId;
	}

	public String getgId() {
		return gId;
	}

	public void setgId(String gId) {
		this.gId = gId;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getSignupSteps() {
		return signupSteps;
	}

	public void setSignupSteps(Integer signupSteps) {
		this.signupSteps = signupSteps;
	}

	@Override
	public String toString() {
		return "UpdateUserRequest [profilePic=" + profilePic + ", fullName=" + fullName + ", email=" + email
				+ ", gender=" + gender + ", dob=" + dob + ", countryCode=" + countryCode + ", phoneNumber="
				+ phoneNumber + ", address1=" + address1 + ", address2=" + address2 + ", zipCode=" + zipCode
				+ ", country=" + country + ", city=" + city + ", state=" + state + ", signupSteps=" + signupSteps
				+ ", fbId=" + fbId + ", gId=" + gId + "]";
	}

}
