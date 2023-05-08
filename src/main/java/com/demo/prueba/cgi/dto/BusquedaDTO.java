package com.demo.prueba.cgi.dto;

import java.io.Serializable;

public final class BusquedaDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String searchId;
	
	public BusquedaDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public BusquedaDTO(String searchId) {
		// TODO Auto-generated constructor stub
		this.searchId = searchId;
	}


	public String getSearchId() {
		return searchId;
	}

	public void setSearchId(String searchId) {
		this.searchId = searchId;
	}

}
