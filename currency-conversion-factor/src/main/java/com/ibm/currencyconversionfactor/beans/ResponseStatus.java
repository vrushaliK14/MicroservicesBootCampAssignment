package com.ibm.currencyconversionfactor.beans;

public class ResponseStatus {
	private String status;
	private String message;
	private String port;
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
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	@Override
	public String toString() {
		return "ResponseStatus [status=" + status + ", message=" + message + ", port=" + port + "]";
	}
	
}
