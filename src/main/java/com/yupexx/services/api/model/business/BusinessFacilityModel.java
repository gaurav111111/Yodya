package com.yupexx.services.api.model.business;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.yupexx.services.api.model.FacilityMasterModel;

@Entity
@Table(name = "business_facility")
public class BusinessFacilityModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer businessId;
	private Integer facilityId;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="facilityId",referencedColumnName="id",insertable = false,updatable = false)
	private FacilityMasterModel facility;
	
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
	public Integer getFacilityId() {
		return facilityId;
	}
	public void setFacilityId(Integer facilityId) {
		this.facilityId = facilityId;
	}
	public FacilityMasterModel getFacility() {
		return facility;
	}
	public void setFacility(FacilityMasterModel facility) {
		this.facility = facility;
	}
	
}
