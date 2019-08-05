package com.petStore.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "items")
public class Items {

	
	
	 private Integer idItem;
	 private String animalSpecies;
	 private Integer age;
	 private String name;
	 private String description;
	 private double unitCost;
	 private String barCode;
	 private byte[] image;
	 private String urlImage;
	 private String color;
	 private String brand;
	 private double startPrice;
	 private double endPrice;
	 private Integer quantityPeerUnit;
	 private Integer unitWeight;
	 private Integer discount;
	 private Date dateUpdate;
	 private Integer idUserUpdate;
	 private Date updatedDate;
	 private Integer idStatus;
	 
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idItem", unique = true, nullable = false)
	public Integer getIdItem() {
		return idItem;
	}
	public void setIdItem(Integer idItem) {
		this.idItem = idItem;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getUnitCost() {
		return unitCost;
	}
	public void setUnitCost(double unitCost) {
		this.unitCost = unitCost;
	}
	public String getBarCode() {
		return barCode;
	}
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public String getUrlImage() {
		return urlImage;
	}
	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public double getStartPrice() {
		return startPrice;
	}
	public void setStartPrice(double startPrice) {
		this.startPrice = startPrice;
	}
	public double getEndPrice() {
		return endPrice;
	}
	public void setEndPrice(double endPrice) {
		this.endPrice = endPrice;
	}
	public Integer getUnitWeight() {
		return unitWeight;
	}
	public void setUnitWeight(Integer unitWeight) {
		this.unitWeight = unitWeight;
	}
	public Integer getDiscount() {
		return discount;
	}
	public void setDiscount(Integer discount) {
		this.discount = discount;
	}
	public Date getDateUpdate() {
		return dateUpdate;
	}
	public void setDateUpdate(Date dateUpdate) {
		this.dateUpdate = dateUpdate;
	}
	public Integer getIdUserUpdate() {
		return idUserUpdate;
	}
	public void setIdUserUpdate(Integer idUserUpdate) {
		this.idUserUpdate = idUserUpdate;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public Integer getIdStatus() {
		return idStatus;
	}
	public void setIdStatus(Integer idStatus) {
		this.idStatus = idStatus;
	}
	public String getAnimalSpecies() {
		return animalSpecies;
	}
	public void setAnimalSpecies(String animalSpecies) {
		this.animalSpecies = animalSpecies;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Integer getQuantityPeerUnit() {
		return quantityPeerUnit;
	}
	public void setQuantityPeerUnit(Integer quantityPeerUnit) {
		this.quantityPeerUnit = quantityPeerUnit;
	}
	public String getName() {
		return name;
	}
	
	 
}
