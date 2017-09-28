package com.java.model;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;


public class CardHead {
	private int cardheadid;
	private int cardcode;
	private int machineid;
	private String machinename;
	private String createtime;
	public int getCardheadid() {
		return cardheadid;
	}
	public void setCardheadid(int cardheadid) {
		this.cardheadid = cardheadid;
	}
	public int getCardcode() {
		return cardcode;
	}
	public void setCardcode(int cardcode) {
		this.cardcode = cardcode;
	}
	public int getMachineid() {
		return machineid;
	}
	public void setMachineid(int machineid) {
		this.machineid = machineid;
	}
	public String getMachinename() {
		return machinename;
	}
	
	
	public CardHead(int cardcode, int machineid, String machinename,
			String createtime) {
		super();
		this.cardcode = cardcode;
		this.machineid = machineid;
		this.machinename = machinename;
		this.createtime = createtime;
	}
	@Override
	public String toString() {
		return "CardHead [cardheadid=" + cardheadid + ", cardcode=" + cardcode
				+ ", machineid=" + machineid + ", machinename=" + machinename
				+ ", createtime=" + createtime + "]";
	}
	public void setMachinename(String machinename) {
		this.machinename = machinename;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	
}
