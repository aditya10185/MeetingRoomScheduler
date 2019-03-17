package com.meetingroom.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "admin_user")
public class AdminUser extends User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7802681604558338772L;
	
	@Column(name = "role")
	private String role;
	
	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * 
	 */
	public AdminUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param contactNo
	 * @param password
	 */
	public AdminUser(String firstName, String lastName, String email, String contactNo, String password, String role) {
		super(firstName, lastName, email, contactNo, password);
		// TODO Auto-generated constructor stub
		this.role = role;
	}
	
	

}
