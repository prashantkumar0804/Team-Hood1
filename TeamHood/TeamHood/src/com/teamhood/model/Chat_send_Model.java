package com.teamhood.model;

public class Chat_send_Model {

	String msg;
	String id;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMessage_status() {
		return message_status;
	}

	public void setMessage_status(String message_status) {
		this.message_status = message_status;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getTo_email_user_id() {
		return to_email_user_id;
	}

	public void setTo_email_user_id(String to_email_user_id) {
		this.to_email_user_id = to_email_user_id;
	}

	public String getFrom_email_user_id() {
		return from_email_user_id;
	}

	public void setFrom_email_user_id(String from_email_user_id) {
		this.from_email_user_id = from_email_user_id;
	}

	String message_status;
	String created;
	String to_email_user_id;
	String from_email_user_id;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
