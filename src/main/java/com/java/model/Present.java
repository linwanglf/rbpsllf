package com.java.model;

public class Present {

	private int id;
	private String presentCode;
	private String presentName;
	private String updateTime;

	public String getPresentCode() {
		return presentCode;
	}

	public void setPresentCode(String presentCode) {
		this.presentCode = presentCode;
	}

	public String getPresentName() {
		return presentName;
	}

	public void setPresentName(String presentName) {
		this.presentName = presentName;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public int getId() {

		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
