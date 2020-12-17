package com.yupexx.bazaar.api.model.ads;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.yupexx.bazaar.api.model.CategoryAccessoryModel;
import com.yupexx.bazaar.api.model.CategoryModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ad_post_accessory")
public class AdPostAccessoryModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@JsonIgnore
	private Integer adId;
	private Integer accessoryId;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="accessoryId",referencedColumnName="id",insertable = false,updatable = false)
	private CategoryAccessoryModel accessory;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAdId() {
		return adId;
	}
	public void setAdId(Integer adId) {
		this.adId = adId;
	}
	public Integer getAccessoryId() {
		return accessoryId;
	}
	public void setAccessoryId(Integer accessoryId) {
		this.accessoryId = accessoryId;
	}
	public CategoryAccessoryModel getAccessory() {
		return accessory;
	}
	public void setAccessory(CategoryAccessoryModel accessory) {
		this.accessory = accessory;
	}
	
	
	
}
