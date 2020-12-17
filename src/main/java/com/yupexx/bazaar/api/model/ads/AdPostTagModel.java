package com.yupexx.bazaar.api.model.ads;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.yupexx.bazaar.api.model.CategoryProductTypeModel;
import com.yupexx.bazaar.api.model.CategoryTagsModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ad_post_tag")
public class AdPostTagModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer adId;
	private Integer tagId;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="tagId",referencedColumnName="id",insertable = false,updatable = false)
	private CategoryTagsModel tag;
	
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
	public Integer getTagId() {
		return tagId;
	}
	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}
	public CategoryTagsModel getTag() {
		return tag;
	}
	public void setTag(CategoryTagsModel tag) {
		this.tag = tag;
	}
	
	
}
