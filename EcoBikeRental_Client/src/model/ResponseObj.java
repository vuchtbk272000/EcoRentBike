package model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseObj {
	@JsonProperty("status")
	private String status;
	@JsonProperty("message")
	private String message;
	public ResponseObj(String status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
	public ResponseObj() {}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
