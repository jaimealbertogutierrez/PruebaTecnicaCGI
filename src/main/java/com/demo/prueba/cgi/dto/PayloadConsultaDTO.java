/**
 * 
 */
package com.demo.prueba.cgi.dto;

import java.io.Serializable;

/**
 * @author Jaime Gutierrez
 *
 */
public final class PayloadConsultaDTO implements Serializable{



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String hotelId;
	private String checkIn;
	private String checkOut;
	private int [] ages;
	
	/**
	 * 
	 */
	public PayloadConsultaDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public String getHotelId() {
		return hotelId;
	}

	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}

	public String getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(String checkIn) {
		this.checkIn = checkIn;
	}

	public String getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(String checkOut) {
		this.checkOut = checkOut;
	}

	public int [] getAges() {
		return ages;
	}

	public void setAges(int [] ages) {
		this.ages = ages;
	}

}
