package com.java.model;

public class Site {
	private int siteid ;
	private String sitename;
	private int regionid;
	private String regionname;
	private String siteboss;
	private String sitepartner;
	private String createtime;
	
	
	
	public Site(String sitename, int regionid, String regionname,
			String siteboss, String sitepartner, String createtime) {
		super();
		this.sitename = sitename;
		this.regionid = regionid;
		this.regionname = regionname;
		this.siteboss = siteboss;
		this.sitepartner = sitepartner;
		this.createtime = createtime;
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
	public String getSiteboss() {
		return siteboss;
	}
	public void setSiteboss(String siteboss) {
		this.siteboss = siteboss;
	}
	public String getSitepartner() {
		return sitepartner;
	}
	public void setSitepartner(String sitepartner) {
		this.sitepartner = sitepartner;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	
}
