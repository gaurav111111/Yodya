package com.yupexx.bazaar.api.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "cron_queue")
public class CronModel {

//	public enum Via {
//		EMAIL, SMS, PUSH
//	}
//
//	public enum EmailType {
//		USER_NEW, USER_FORGOT,USER_DELETE ,USER_PROFILE, USER_PASS, AD_APPROVED, AD_REJECTED,VERIFY_EMAIL,VERIFY_PHONE, OTHER
//	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

//	@Enumerated(EnumType.STRING)
//	public Via via;

	String via;
	// @Enumerated(EnumType.STRING)
	// public EmailType emailType;

	public String emailType;
	private String data;
	private Integer isProcessed = 0;

	@Column(columnDefinition = "TINYINT")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean status = true;
	// @JsonIgnore
	private Long createdBy = (long) -1;
	private Long updatedBy = (long) -1;
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

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Integer getIsProcessed() {
		return isProcessed;
	}

	public void setIsProcessed(Integer isProcessed) {
		this.isProcessed = isProcessed;
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
//	public Via getVia() {
//		return via;
//	}
//	public void setVia(Via via) {
//		this.via = via;
//	}
//	public EmailType getEmailType() {
//		return emailType;
//	}
//	public void setEmailType(EmailType emailType) {
//		this.emailType = emailType;
//	}

	public String getVia() {
		return via;
	}

	public void setVia(String via) {
		this.via = via;
	}

	public String getEmailType() {
		return emailType;
	}

	public void setEmailType(String emailType) {
		this.emailType = emailType;
	}

}
