package com.yupexx.bazaar.api.model.view;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import com.yupexx.bazaar.api.model.CategoryModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ads_by_sellerid")
public class SellerAdCountVwModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(insertable = false,updatable = false)
	private String profilePic;
	@Column(insertable = false,updatable = false)
	private String fullName;
	@Column(insertable = false,updatable = false)
	private String email;
	private enum gender {
		male, female
	};
	@Column(insertable = false,updatable = false)
	private String countryCode;
	@Column(insertable = false,updatable = false)
	private Long phoneNumber;
	@Column(columnDefinition = "TINYINT",insertable = false,updatable = false)
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean status;
	@CreationTimestamp
	@Column(insertable = false,updatable = false)
	private Timestamp createdDate;
	@UpdateTimestamp
	@Column(insertable = false,updatable = false)
	private Timestamp updatedDate;
	@Column(insertable = false,updatable = false)
	private Long nAds;
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
	public Long getnAds() {
		return nAds;
	}
	public void setnAds(Long nAds) {
		this.nAds = nAds;
	}

}
