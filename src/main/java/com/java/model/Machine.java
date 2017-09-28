package com.java.model;

public class Machine {
	private int machineid;
	private String machinename;
	private int siteid;
	private String sitename;
	private String createtime;
	public int getMachineid() {
		return machineid;
	}
	public void setMachineid(int machineid) {
		this.machineid = machineid;
	}
	public String getMachinename() {
		return machinename;
	}
	public void setMachinename(String machinename) {
		this.machinename = machinename;
	}
	public int getSiteid() {
		return siteid;
	}
	public void setSiteid(int siteid) {
		this.siteid = siteid;
	}
	public String getSitename() {
		return sitename;
	}
	public void setSitename(String sitename) {
		this.sitename = sitename;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public Machine(String machinename, int siteid, String sitename,
			String createtime) {
		super();
		this.machinename = machinename;
		this.siteid = siteid;
		this.sitename = sitename;
		this.createtime = createtime;
	}
	
	@Override
	public String toString() {
		return "Machine [machineid=" + machineid + ", machinename="
				+ machinename + ", siteid=" + siteid + ", sitename=" + sitename
				+ ", createtime=" + createtime + "]";
	}
	
	

}
