package com.java.model;

public class Login {
	
	private int loginid;
	private String shopid;
	private String appid;
	private String coinlimit;
	private String coincost;
	private String secretkey;
	private String timestamp;
	private String signature;
	
	
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}
	
		
	public Login(int loginid, String shopid, String appid, String coinlimit,
			String coincost, String secretkey, String timestamp,
			String signature) {
		super();
		this.loginid = loginid;
		this.shopid = shopid;
		this.appid = appid;
		this.coinlimit = coinlimit;
		this.coincost = coincost;
		this.secretkey = secretkey;
		this.timestamp = timestamp;
		this.signature = signature;
	}

	/*
	 * New a Login Object include five keys
	 */
	public Login(String shopid, String appid, String coinlimit,
		String coincost, String secretkey) {
		super();		
		this.shopid = shopid;
		this.appid = appid;
		this.coinlimit = coinlimit;
		this.coincost = coincost;
		this.secretkey = secretkey;
	}
	
	
	public int getLoginid() {
		return loginid;
	}
	public void setLoginid(int loginid) {
		this.loginid = loginid;
	}
		
	
	public String getShopid() {
		return shopid;
	}
	public void setShopid(String shopid) {
		this.shopid = shopid;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getCoinlimit() {
		return coinlimit;
	}
	public void setCoinlimit(String coinlimit) {
		this.coinlimit = coinlimit;
	}
	public String getCoincost() {
		return coincost;
	}
	public void setCoincost(String coincost) {
		this.coincost = coincost;
	}
	public String getSecretkey() {
		return secretkey;
	}
	public void setSecretkey(String secretkey) {
		this.secretkey = secretkey;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	@Override
	public String toString() {
		return "Login [loginid=" + loginid + ", shopid=" + shopid + ", appid="
				+ appid + ", coinlimit=" + coinlimit + ", coincost=" + coincost
				+ ", secretkey=" + secretkey + ", timestamp=" + timestamp
				+ ", signature=" + signature + ", toString()="
				+ super.toString() + "]";
	}
	
	
}
