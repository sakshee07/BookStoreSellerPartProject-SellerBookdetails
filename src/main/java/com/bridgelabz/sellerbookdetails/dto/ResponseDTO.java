package com.bridgelabz.sellerbookdetails.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class ResponseDTO {
    String message;
    Object response;
    public ResponseDTO(String message, String response) {
        this.message = message;
        this.response = response;
    }
    public ResponseDTO(String message, Object response) {
        this.message = message;
        this.response = response;
    }
	public String getMessage() {
		return message;
	}
	public Object getResponse() {
		return response;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setResponse(Object response) {
		this.response = response;
	}
    
    
}
