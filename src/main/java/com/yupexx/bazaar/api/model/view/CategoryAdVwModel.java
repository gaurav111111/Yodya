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

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import com.yupexx.bazaar.api.model.CategoryModel;
import com.yupexx.bazaar.api.model.ads.AdPostUserJoinModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "category_master")
public class CategoryAdVwModel {

	@Id
	private Integer id;

	private Integer parentCatId;
	private String categoryName;

	@Column(columnDefinition = "TINYINT")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	@JsonIgnore
	private Boolean status;
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

	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="parentCatId",referencedColumnName="id")
	private List<CategoryModel> data;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="catId",referencedColumnName="id",insertable = false,updatable = false)
	private List<AdPostUserJoinModel> ads;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParentCatId() {
		return parentCatId;
	}

	public void setParentCatId(Integer parentCatId) {
		this.parentCatId = parentCatId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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

	public List<CategoryModel> getData() {
		return data;
	}

	public void setData(List<CategoryModel> data) {
		this.data = data;
	}

	public List<AdPostUserJoinModel> getAds() {
		return ads;
	}

	public void setAds(List<AdPostUserJoinModel> ads) {
		this.ads = ads;
	}

	
}
