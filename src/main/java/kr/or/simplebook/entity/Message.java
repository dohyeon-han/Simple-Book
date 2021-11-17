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
	
	public Message(StatusEnum status, String message, Object data) {
		this.status = status;
		this.message = message;
		this.data = data;
	}
}
