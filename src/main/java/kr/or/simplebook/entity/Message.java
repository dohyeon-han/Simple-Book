package kr.or.simplebook.entity;

import lombok.Data;

@Data
public class Message {
	private StatusEnum status;
	private String message;
	private Object data;
	
	public Message() {
		status = StatusEnum.BAD_REQUEST;
		message = null;
		data = null;
	}
	
	public enum StatusEnum {
	    OK(200, "OK"),
	    BAD_REQUEST(400, "BAD REQUEST"),
	    NOT_FOUND(404, "NOT FOUND"),
	    INTERNAL_SERVER_ERROR(500, "INTERNAL SERVER ERROR");

	    int code;
	    String message;

	    StatusEnum(int code, String message) {
	        this.code = code;
	        this.message = message;
	    }
	}
}
