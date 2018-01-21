package com.java.model;

public class Region {
	private int regionid;
	private String regionname;
	private String createtime;
	
	
	public Region() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Region(int regionid, String regionname, String createtime) {
		super();
		this.regionid = regionid;
		this.regionname = regionname;
		this.createtime = createtime;
	}
	
	public Region(String regionname, String createtime) {
		super();	
		this.regionname = regionname;
		this.createtime = createtime;
	}



	public int getRegionid() {
		return regionid;
	}
	public void setRegionid(int regionid) {
		this.regionid = regionid;
	}
	public String getRegionname() {
		return regionname;
	}
	public void setRegionname(String regionname) {
		this.regionname = regionname;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	
	
	@Override
	public String toString() {
		return "Region [regionid=" + regionid + ", regionname=" + regionname
				+ ", createtime=" + createtime + "]";
	}
	
}
