package com.java.model;

public class Role {

	private int roleId;
	private String roleName;
	private String authIds;
	private String authStrs;
	private String roleDescription;

    private int menberId;//新增加
    private String menberName;//新增加
    private String menu_level;//新增加

	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Role(String roleName, String roleDescription) {
		super();
		this.roleName = roleName;
		this.roleDescription = roleDescription;
	}
	
	public Role(int roleId, String authIds) {
		super();
		this.roleId = roleId;
		this.authIds = authIds;
	}

    public Role(int menberId,String menberName, String menu_level) {
        super();
        this.menberId = menberId;
        this.menberName = menberName;
        this.menu_level = menu_level;
    }


	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getAuthIds() {
		return authIds;
	}
	public void setAuthIds(String authIds) {
		this.authIds = authIds;
	}
	public String getAuthStrs() {
		return authStrs;
	}
	public void setAuthStrs(String authStrs) {
		this.authStrs = authStrs;
	}
	public String getRoleDescription() {
		return roleDescription;
	}
	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}


    public int getMenberId() {return menberId;}
    public void setMenberId(String roleDescription) {
        this.menberId = menberId;
    }

    public String getMenberName() {
        return menberName;
    }
    public void setMenberName(String menberName) {
        this.menberName= menberName;
    }

    public String getMenu_level() {
        return menu_level;
    }
    public void setMenu_level(String menu_level) {
        this.menu_level= menu_level;
    }

}
