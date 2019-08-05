package com.petStore.dto;

import com.petStore.model.Items;

public class ItemsDto extends Items {
	private String family;
	private String subFamily;
	private String unitOfMeasurement;
	public String getFamily() {
		return family;
	}
	public void setFamily(String family) {
		this.family = family;
	}
	public String getUnitOfMeasurement() {
		return unitOfMeasurement;
	}
	public void setUnitOfMeasurement(String unitOfMeasurement) {
		this.unitOfMeasurement = unitOfMeasurement;
	}
	public String getSubFamily() {
		return subFamily;
	}
	public void setSubFamily(String subFamily) {
		this.subFamily = subFamily;
	}
	
}
