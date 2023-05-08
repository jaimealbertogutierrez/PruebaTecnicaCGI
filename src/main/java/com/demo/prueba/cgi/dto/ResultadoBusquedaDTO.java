package com.demo.prueba.cgi.dto;

import java.io.Serializable;

public class ResultadoBusquedaDTO implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4370541015655631338L;
	private String searchId;
	private PayloadConsultaDTO search;
	private int count;
	
	public ResultadoBusquedaDTO() {
		// TODO Auto-generated constructor stub
	}

	public String getSearchId() {
		return searchId;
	}

	public void setSearchId(String searchId) {
		this.searchId = searchId;
	}

	public PayloadConsultaDTO getSearch() {
		return search;
	}

	public void setSearch(PayloadConsultaDTO search) {
		this.search = search;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	

}
