package com.yupexx.bazaar.api.model.ads;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import com.yupexx.bazaar.api.model.CategoryAccessoryModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ad_post_user_subscriptions")
public class AdPostUserSubscriptionsModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer adSubscriptionId;
	private Long userMasterId;
	
	private Integer adId;
	private float adSubscriptionPrice;
	private Timestamp expiryDate;
	@JsonIgnore
	@Column(columnDefinition = "TINYINT")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean status;
	@Column(columnDefinition = "TINYINT")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean paymentPaid;
	@JsonIgnore
	private Long createdBy;
	@JsonIgnore
	private Long updatedBy;
	@JsonIgnore
	@CreationTimestamp
	private Timestamp createdDate;
	@JsonIgnore
	@UpdateTimestamp
	private Timestamp updatedDate;
	
	@OneToOne
	@JoinColumn(name="adSubscriptionId",referencedColumnName="id",updatable=false,insertable=false)
	private AdPostDealModel deal;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAdSubscriptionId() {
		return adSubscriptionId;
	}

	public void setAdSubscriptionId(Integer adSubscriptionId) {
		this.adSubscriptionId = adSubscriptionId;
	}

	public Long getUserMasterId() {
		return userMasterId;
	}

	public void setUserMasterId(Long userMasterId) {
		this.userMasterId = userMasterId;
	}

	public Integer getAdId() {
		return adId;
	}

	public void setAdId(Integer adId) {
		this.adId = adId;
	}

	public Timestamp getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Timestamp expiryDate) {
		this.expiryDate = expiryDate;
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

	public AdPostDealModel getDeal() {
		return deal;
	}

	public void setDeal(AdPostDealModel deal) {
		this.deal = deal;
	}

	public float getAdSubscriptionPrice() {
		return adSubscriptionPrice;
	}

	public void setAdSubscriptionPrice(float adSubscriptionPrice) {
		this.adSubscriptionPrice = adSubscriptionPrice;
	}

	public Boolean getPaymentPaid() {
		return paymentPaid;
	}

	public void setPaymentPaid(Boolean paymentPaid) {
		this.paymentPaid = paymentPaid;
	}
	
}
