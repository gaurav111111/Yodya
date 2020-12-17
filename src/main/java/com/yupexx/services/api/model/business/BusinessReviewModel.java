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
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "business_review")
public class BusinessReviewModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Integer businessId;
	private String description;
	
	@Column(columnDefinition = "TINYINT")
	private Integer serviceQuality;
	@Column(columnDefinition = "TINYINT")
	private Integer staffBehaviour;
	@Column(columnDefinition = "TINYINT")
	private Integer ambience;
	@Column(columnDefinition = "TINYINT")
	private Integer valueMoney;
	@Column(columnDefinition = "TINYINT")
	private Integer overall;

	
	@JsonIgnore
	@Column(columnDefinition = "TINYINT")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean status=true;
	@JsonIgnore
	private Long createdBy=(long)1;
	@JsonIgnore
	private Long updatedBy=(long)1;
	@JsonIgnore
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
	public Integer getBusinessId() {
		return businessId;
	}
	public void setBusinessId(Integer businessId) {
		this.businessId = businessId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Integer getServiceQuality() {
		return serviceQuality;
	}
	public void setServiceQuality(Integer serviceQuality) {
		this.serviceQuality = serviceQuality;
	}
	public Integer getStaffBehaviour() {
		return staffBehaviour;
	}
	public void setStaffBehaviour(Integer staffBehaviour) {
		this.staffBehaviour = staffBehaviour;
	}
	public Integer getAmbience() {
		return ambience;
	}
	public void setAmbience(Integer ambience) {
		this.ambience = ambience;
	}
	public Integer getValueMoney() {
		return valueMoney;
	}
	public void setValueMoney(Integer valueMoney) {
		this.valueMoney = valueMoney;
	}
	public Integer getOverall() {
		return overall;
	}
	public void setOverall(Integer overall) {
		this.overall = overall;
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
