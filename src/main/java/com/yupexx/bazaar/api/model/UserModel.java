package com.yupexx.bazaar.api.model;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user_master")
public class UserModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonIgnore
	private String fbId="";
	@JsonIgnore
	private String gId="";
	private String profilePic;
	private String fullName;

	private String email;
	private String password;
	@JsonIgnore
	private String passCode;

	private enum gender {
		male, female
	};

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
	@Column(columnDefinition = "TINYINT")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean isEmailValid=false;
	@Column(columnDefinition = "TINYINT")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean isMobileValid=false;
	@Column(columnDefinition = "TINYINT")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean status=false;
	@JsonIgnore
	private Long createdBy=(long)1;
	@JsonIgnore
	private Long updatedBy=(long)1;
	@CreationTimestamp
	private Timestamp createdDate;
	@UpdateTimestamp
	private Timestamp updatedDate;
	
	@Transient
	@Column(name="nAds",nullable = true)
	private Integer nAds;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "roleType", insertable =  false, updatable = false)
    private RoleModel userRole;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassCode() {
		return passCode;
	}

	public void setPassCode(String passCode) {
		this.passCode = passCode;
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

	public RoleModel getUserRole() {
		return userRole;
	}

	public void setUserRole(RoleModel userRole) {
		this.userRole = userRole;
	}

	public Integer getnAds() {
		return nAds;
	}

	public void setnAds(Integer nAds) {
		this.nAds = nAds;
	}

	@Override
	public String toString() {
		return "UserModel [id=" + id + ", fbId=" + fbId + ", gId=" + gId + ", profilePic=" + profilePic + ", fullName="
				+ fullName + ", email=" + email + ", password=" + password + ", passCode=" + passCode + ", dob=" + dob
				+ ", countryCode=" + countryCode + ", phoneNumber=" + phoneNumber + ", roleType=" + roleType
				+ ", address1=" + address1 + ", address2=" + address2 + ", zipCode=" + zipCode + ", country=" + country
				+ ", city=" + city + ", state=" + state + ", sourceLogin=" + sourceLogin + ", signupSteps="
				+ signupSteps + ", isEmailValid=" + isEmailValid + ", isMobileValid=" + isMobileValid + ", status="
				+ status + ", createdBy=" + createdBy + ", updatedBy=" + updatedBy + ", createdDate=" + createdDate
				+ ", updatedDate=" + updatedDate + ", nAds=" + nAds + ", userRole=" + userRole + "]";
	}

}
