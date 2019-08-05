package com.petStore.model;

// Generated 22-ene-2017 16:32:08 by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.beans.BeanUtils;

/**
 * Users generated by hbm2java
 */
@Entity
@Table(name = "users")
public class Users implements java.io.Serializable {

	private Integer idUsers;
	private String firstName;
	private String middleName;
	private String secondName;
	private String address;
	private String cellPhone;
	private String phone;
	private String zip;
	private String email;
	private String rfc;
	private String city;
	private String state;
	private String country;
	private Date updatedDate;
	private Date expiredDate;
	private String login;
	private String password;
	private int idStatus;
	private String position;
	private byte[] image;
	private Integer idBranch;

	public Users(){
		
	}
	public Users(Users from) {
		copyFrom(from);
	}
	
	public Users( String firstName, String address,
			String phone, String zip, String email, String rfc, String city,
			String state, String country, Date updatedDate, String login,
			String password, int idStatus) {
		
		this.firstName = firstName;
		this.address = address;
		this.phone = phone;
		this.zip = zip;
		this.email = email;
		this.rfc = rfc;
		this.city = city;
		this.state = state;
		this.country = country;
		this.updatedDate = updatedDate;
		this.login = login;
		this.password = password;
		this.idStatus = idStatus;
	}

	public Users( String firstName, String middleName,
			String secondName, String address, String cellPhone, String phone,
			String zip, String email, String rfc, String city, String state,
			String country, Date updatedDate, Date expiredDate, String login,
			String password, int idStatus) {
		this.firstName = firstName;
		this.middleName = middleName;
		this.secondName = secondName;
		this.address = address;
		this.cellPhone = cellPhone;
		this.phone = phone;
		this.zip = zip;
		this.email = email;
		this.rfc = rfc;
		this.city = city;
		this.state = state;
		this.country = country;
		this.updatedDate = updatedDate;
		this.expiredDate = expiredDate;
		this.login = login;
		this.password = password;
		this.idStatus = idStatus;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idUsers", unique = true, nullable = false)
	public Integer getIdUsers() {
		return this.idUsers;
	}

	public void setIdUsers(Integer idUsers) {
		this.idUsers = idUsers;
	}

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "idCompany", nullable = false)
//	public Company getCompany() {
//		return this.company;
//	}
//
//	public void setCompany(Company company) {
//		this.company = company;
//	}
	
	@Column(name = "firstName", nullable = false, length = 70)
	public String getFirstName() {
		return this.firstName;
	}

	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "middleName", length = 70)
	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	@Column(name = "secondName", length = 70)
	public String getSecondName() {
		return this.secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	@Column(name = "address", nullable = false, length = 200)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "cellPhone", length = 13)
	public String getCellPhone() {
		return this.cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	@Column(name = "phone", nullable = false, length = 13)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "zip", nullable = false, length = 5)
	public String getZip() {
		return this.zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	@Column(name = "email", nullable = false, length = 70)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "rfc", nullable = false, length = 13)
	public String getRfc() {
		return this.rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	@Column(name = "city", nullable = false, length = 90)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "state", nullable = false, length = 90)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "country", nullable = false, length = 90)
	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "updatedDate", nullable = false, length = 10)
	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "expiredDate", length = 10)
	public Date getExpiredDate() {
		return this.expiredDate;
	}

	public void setExpiredDate(Date expiredDate) {
		this.expiredDate = expiredDate;
	}

	@Column(name = "login", nullable = false, length = 100)
	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Column(name = "password", nullable = false, length = 500, updatable=false)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "idStatus", nullable = false)
	public int getIdStatus() {
		return this.idStatus;
	}

	public void setIdStatus(int idStatus) {
		this.idStatus = idStatus;
	}
	private void copyFrom(Users from){
		BeanUtils.copyProperties(from,this);
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public Integer getIdBranch() {
		return idBranch;
	}
	public void setIdBranch(Integer idBranch) {
		this.idBranch = idBranch;
	}
	
}