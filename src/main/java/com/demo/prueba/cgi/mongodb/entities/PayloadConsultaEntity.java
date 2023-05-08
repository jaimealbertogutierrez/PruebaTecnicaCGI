package com.demo.prueba.cgi.mongodb.entities;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Document
public class PayloadConsultaEntity {

	@Id
	private ObjectId searchId;
	
	private String hotelId;
	private String checkIn;
	private String checkOut;
	private int [] ages;

	public PayloadConsultaEntity() {
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

	public int[] getAges() {
		return ages;
	}

	public void setAges(int[] ages) {
		this.ages = ages;
	}

	public ObjectId getSearchId() {
		return searchId;
	}

	public void setSearchId(ObjectId searchId) {
		this.searchId = searchId;
	}	
	
	public String toJson() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            // Manejo de excepciones
        }
        return "";
    }
	

}