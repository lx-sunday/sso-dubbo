package com.sso.interfaces.model;

import java.io.Serializable;



public class SsoUserVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3995598910225450272L;

	private Long id;

	private String username;


	private String password;

	@Override
	public String toString(){
		
		return "id="+id+" username="+username+" password="+password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}




}
