package com.petStore.dto;

import com.petStore.model.Users;

public class UsersDto extends Users{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5546001112659588009L;
	private int idRole;
	
	private int idHierarchy;
	private String inCharge;
	
	public UsersDto(Users from){
		super(from);
	}

	public int getIdRole() {
		return idRole;
	}

	public void setIdRole(int idRole) {
		this.idRole = idRole;
	}

	public int getIdHierarchy() {
		return idHierarchy;
	}

	public void setIdHierarchy(int idHierarchy) {
		this.idHierarchy = idHierarchy;
	}

	public String getInCharge() {
		return inCharge;
	}

	public void setInCharge(String inCharge) {
		this.inCharge = inCharge;
	}
	
	
}
