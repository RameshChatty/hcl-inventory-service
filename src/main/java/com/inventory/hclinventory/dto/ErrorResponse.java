package com.inventory.hclinventory.dto;
import java.util.List;

import lombok.Data;

@Data
public class ErrorResponse  extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private String errorCode;
    private List<String> messages;
    
    
	public ErrorResponse(String errorCode, List<String> messages) {
		
		super();
		this.errorCode = errorCode;
		this.messages = messages;
	}
	
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public List<String> getMessages() {
		return messages;
	}
	public void setMessages(List<String> messages) {
		this.messages = messages;
	}
    
    
}