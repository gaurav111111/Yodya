package com.yupexx.bazaar.api.model.dto;

import java.sql.Timestamp;

import com.yupexx.bazaar.api.model.RoleModel;
import com.yupexx.bazaar.api.model.UserModel;

public class UserResponseDTO {

	private Long id;

	private String profilePic="-";
	private String fullName="-";
	private String email="-";
	private String dob="1970-01-01";
	private String countryCode="91";
	private Long phoneNumber=(long)0;
	private Integer roleType;
	private String address1;
	private String address2;
	private String zipCode;
	private String country;
	private String city;
	private String state;
	private String sourceLogin="API";
	private Integer signupSteps=0;
	private Boolean isEmailValid;
	private Boolean isMobileValid;
	private Boolean status;
	private Timestamp createdDate;
	private Timestamp updatedDate;
	private Integer nAds;
	private RoleModel userRole;
	private Boolean fbStatus;
	private Boolean gStatus;
	private String gId="";
	private String fId="";
	
	
	public String getgId() {
		return gId;
	}
	public void setgId(String gId) {
		this.gId = gId;
	}
	public String getfId() {
		return fId;
	}
	public void setfId(String fId) {
		this.fId = fId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Integer getRoleType() {
		return roleType;
	}
	public void setRoleType(Integer roleType) {
		this.roleType = roleType;
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
	public String getSourceLogin() {
		return sourceLogin;
	}
	public void setSourceLogin(String sourceLogin) {
		this.sourceLogin = sourceLogin;
	}
	public Integer getSignupSteps() {
		return signupSteps;
	}
	public void setSignupSteps(Integer signupSteps) {
		this.signupSteps = signupSteps;
	}
	public Boolean getIsEmailValid() {
		return isEmailValid;
	}
	public void setIsEmailValid(Boolean isEmailValid) {
		this.isEmailValid = isEmailValid;
	}
	public Boolean getIsMobileValid() {
		return isMobileValid;
	}
	public void setIsMobileValid(Boolean isMobileValid) {
		this.isMobileValid = isMobileValid;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
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
	public Integer getnAds() {
		return nAds;
	}
	public void setnAds(Integer nAds) {
		this.nAds = nAds;
	}
	public RoleModel getUserRole() {
		return userRole;
	}
	public void setUserRole(RoleModel userRole) {
		this.userRole = userRole;
	}
	public Boolean getFbStatus() {
		return fbStatus;
	}
	public void setFbStatus(Boolean fbStatus) {
		this.fbStatus = fbStatus;
	}
	public Boolean getgStatus() {
		return gStatus;
	}
	public void setgStatus(Boolean gStatus) {
		this.gStatus = gStatus;
	}
	@Override
	public String toString() {
		return "UserResponseDTO [id=" + id + ", profilePic=" + profilePic + ", fullName=" + fullName + ", email="
				+ email + ", dob=" + dob + ", countryCode=" + countryCode + ", phoneNumber=" + phoneNumber
				+ ", roleType=" + roleType + ", address1=" + address1 + ", address2=" + address2 + ", zipCode="
				+ zipCode + ", country=" + country + ", city=" + city + ", state=" + state + ", sourceLogin="
				+ sourceLogin + ", signupSteps=" + signupSteps + ", isEmailValid=" + isEmailValid + ", isMobileValid="
				+ isMobileValid + ", status=" + status + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate
				+ ", nAds=" + nAds + ", userRole=" + userRole + ", fbStatus=" + fbStatus + ", gStatus=" + gStatus
				+ ", gId=" + gId + ", fId=" + fId + "]";
	}
	
	
	
	
}
