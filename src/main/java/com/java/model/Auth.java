package com.java.model;

public class Auth {

	private int authId;
	private String authName;
	private String authPath;


	private int parentId;
	private String authDescription;
	private String state;
	private String iconCls;

    private String homeaddress;
    private String politicalStatus;
    private String pursuits_talents;
    private String companyold;//原来单位
    private String certify;
    private String classtype;
    private String photo;
    private String prize;

    private String profession;
    private String shcool;
    private String phoneEMG;
    private String email;
    private String phone;
    private String iDcode;
    private String marriage;
    private String menbertype;
    private String sex;
    private String expense_center;
    private String position;

    private String entercompany_date;
    private String work_ege;
    private String birthday;
    private String classname;
    private String division;
    private String department;
    private String departmentname;
    private String menu_level;
    private String menberName;
    private String menberNo;
    private String menberPath;
    private int menberId;
	public Auth() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Auth(String authName, String authPath, String authDescription,
			String iconCls) {
		super();
		this.authName = authName;
		this.authPath = authPath;
		this.authDescription = authDescription;
		this.iconCls = iconCls;
	}


    public Auth(String menberName,String menberPath, String authDescription,
                String iconCls, String homeaddress, String politicalStatus,
                String pursuits_talents, String companyold, String certify,
                String classtype, String photo, String prize, String profession,
                String shcool, String phoneEMG, String email, String phone, String iDcode,
                String marriage, String menbertype, String sex, String expense_center,
                String position, String entercompany_date, String work_ege, String birthday,
                String classname, String division, String department, String departmentname,String menu_level,String menberNo) {
        super();
        this.menberName = menberName;

        this.authName = authName;
        this.menberPath = menberPath;
        this.authDescription = authDescription;
        this.iconCls = iconCls;

        this.homeaddress = homeaddress;
        this.politicalStatus = politicalStatus;
        this.pursuits_talents = pursuits_talents;
        this.companyold = companyold;
        this.certify = certify;
        this.classtype = classtype;
        this.photo = photo;
        this.prize = prize;
        this.profession = profession;
        this.shcool = shcool;
        this.phoneEMG = phoneEMG;

        this.email = email;
        this.phone = phone;
        this.iDcode = iDcode;
        this.marriage = marriage;
        this.menbertype = menbertype;
        this.sex = sex;
        this.expense_center = expense_center;
        this.position = position;
        this.entercompany_date = entercompany_date;

        this.work_ege= work_ege;
        this.birthday = birthday;
        this.classname = classname;
        this.division = division;
        this.department= department;
        this.departmentname = departmentname;
        this.menu_level = menu_level;
        this.menberNo= menberNo;

    }
    public String getAuthName() {
        return authName;
    }
    public void getAuthName(int authId) {
        this.authName = authName;
    }

	public int getAuthId() {
		return authId;
	}
	public void setAuthId(int authId) {
		this.authId = authId;
	}


	public String getAuthPath() {
		return authPath;
	}
	public void setAuthPath(String authPath) {
		this.authPath = authPath;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getAuthDescription() {
		return authDescription;
	}
	public void setAuthDescription(String authDescription) {
		this.authDescription = authDescription;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}


    public String getDepartmentname() {return departmentname;}
    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname;
    }
    public String getDepartment() {return department;}
    public void setDepartment(String department) {
        this.department = department;
    }
    public String getDivision() {return division;}
    public void setDivision(String division) {
        this.division = division;
    }

    public String getClassname() {return classname;}
    public void setClassname(String classname) {
        this.classname = classname;
    }
    public String getBirthday() {return birthday;}
    public void setBirthday(String iconCls) {
        this.birthday = birthday;
    }
    public String getWork_ege() {return work_ege;}
    public void setWork_ege(String work_ege) {
        this.work_ege = work_ege;
    }
    public String getEntercompany_date() {return entercompany_date;}
    public void setEntercompany_date(String entercompany_date) {
        this.entercompany_date = entercompany_date;
    }
    public String getPosition() {return position;}
    public void setPosition(String position) {
        this.position = position;
    }
    public String getExpense_center() {return expense_center;}
    public void setExpense_center(String expense_center) {
        this.expense_center = expense_center;
    }
    public String getSex() {return sex;}
    public void setSex(String iconCls) {
        this.sex = sex;
    }
    public String getMenbertype() {return menbertype;}
    public void setMenbertype(String menbertype) {
        this.menbertype = menbertype;
    }
    public String getMarriage() {return marriage;}
    public void setMarriage(String marriage) {
        this.marriage = marriage;
    }
    public String getIDcode() {return iDcode;}
    public void setIDcode(String iDcode) {
        this.iDcode = iDcode;
    }
    public String getHomeaddress() {return homeaddress;}
    public void setHomeaddress(String homeaddress) {
        this.homeaddress = homeaddress;
    }
    public String getPoliticalStatus() {return politicalStatus;}
    public void setPoliticalStatus(String politicalStatus) {
        this.politicalStatus = politicalStatus;
    }
    public String getPursuits_talents() {return pursuits_talents;}
    public void setPursuits_talents(String pursuits_talents) {
        this.pursuits_talents = pursuits_talents;
    }
    public String getCompanyold() {return companyold;}
    public void setCompanyold(String companyold) {
        this.companyold = companyold;
    }
    public String getCertify() {return certify;}
    public void setCertify(String certify) {
        this.certify = certify;
    }
    public String getClasstype() {return classtype;}
    public void setClasstype(String classtype) {
        this.classtype = classtype;
    }
    public String getPhoto() {return photo;}
    public void setPhoto(String photo) {
        this.photo = photo;
    }
    public String getPrize() {return prize;}
    public void setPrize(String prize) {
        this.prize = prize;
    }
    public String getProfession() {return profession;}
    public void setProfession(String profession) {
        this.profession = profession;
    }
    public String getShcool() {return shcool;}
    public void setShcool(String shcool) {
        this.shcool = shcool;
    }
    public String getPhoneEMG() {return phoneEMG;}
    public void setPhoneEMG(String phoneEMG) {
        this.phoneEMG = phoneEMG;
    }
    public String getEmail() {return email;}
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {return phone;}
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMenu_level() {return menu_level;}
    public void setMenu_level(String menu_level) {
        this.menu_level = menu_level;
    }

    public String getMenberName() {
        return menberName;
    }
    public void setMenberName(String menberName) {
        this.menberName = menberName;
    }

    public String getMenberNo() {return menberNo;}
    public void setMenberNo(String menberNo) {
        this.menberNo = menberNo;
    }

    public String getMenberPath() {
        return menberPath;
    }
    public void setMenberPath(String menberPath) {
        this.menberPath = menberPath;
    }

    public int getMenberId() {
        return menberId;
    }
    public void setMenberId(int menberId) {
        this.menberId = menberId;
    }
}
