package com.java.model;

public class Attentance {

	private int attId;
	private String menberNo;
	private String menberName;
	private String att_type;//加班日类型
    private String att_project;

    private String apply_date;
    private String applyer;
    private String checkstate;
    private String checker;
    private String check_date;

    private String plan_houre;
    private String menbertype;
    private String division;
    private String department;
    private String departmentname;

    private String classname;
    private String plan_type;
    private int parentId;


    private int normol_overtime;
    private int offday_overtime;
    private int holiday_overtime;
    private String offday_apply_date;



    private String holiday_apply_date;


    public Attentance() {
		super();
		// TODO Auto-generated constructor stub
	}


    public Attentance(String menberNo,String menberName,String att_type,String att_project,String apply_date,
                      String applyer,String checkstate,String checker,String check_date,String plan_houre,
                      String menbertype, String division,String department,String departmentname,String classname,String plan_type) {
        super();

        this.menberNo= menberNo;
        this.menberName = menberName;
        this.att_type = att_type;
        this.att_project = att_project;
        this.apply_date = apply_date;
        this.applyer = applyer;

        this.checkstate = checkstate;
        this.checker = checker;
        this.check_date = check_date;
        this.plan_houre = plan_houre;
        this.menbertype = menbertype;

        this.division = division;
        this.department= department;
        this.departmentname = departmentname;
        this.classname = classname;
        this.plan_type = plan_type;
        //this.parentId = parentId;

    }
    public int getAttId() {
        return attId;
    }

    public void setAttId(int attId) {
        this.attId = attId;
    }

    public String getMenberNo() {
        return menberNo;
    }

    public void setMenberNo(String menberNo) {
        this.menberNo = menberNo;
    }

    public String getMenberName() {
        return menberName;
    }

    public void setMenberName(String menberName) {
        this.menberName = menberName;
    }

    public String getAtt_type() {
        return att_type;
    }

    public void setAtt_type(String att_type) {
        this.att_type = att_type;
    }

    public String getAtt_project() {
        return att_project;
    }

    public void setAtt_project(String att_project) {
        this.att_project = att_project;
    }

    public String getApply_date() {
        return apply_date;
    }

    public void setApply_date(String apply_date) {
        this.apply_date = apply_date;
    }

    public String getApplyer() {
        return applyer;
    }

    public void setApplyer(String applyer) {
        this.applyer = applyer;
    }

    public String getCheckstate() {
        return checkstate;
    }

    public void setCheckstate(String checkstate) {
        this.checkstate = checkstate;
    }

    public String getChecker() {
        return checker;
    }

    public void setChecker(String checker) {
        this.checker = checker;
    }

    public String getCheck_date() {
        return check_date;
    }

    public void setCheck_date(String check_date) {
        this.check_date = check_date;
    }

    public String getPlan_houre() {
        return plan_houre;
    }

    public void setPlan_houre(String plan_houre) {
        this.plan_houre = plan_houre;
    }

    public String getMenbertype() {
        return menbertype;
    }

    public void setMenbertype(String menbertype) {
        this.menbertype = menbertype;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDepartmentname() {
        return departmentname;
    }

    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getPlan_type() {
        return plan_type;
    }

    public void setPlan_type(String plan_type) {
        this.plan_type = plan_type;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getNormol_overtime() {
        return normol_overtime;
    }

    public void setNormol_overtime(int normol_overtime) {
        this.normol_overtime = normol_overtime;
    }

    public int getOffday_overtime() {
        return offday_overtime;
    }

    public void setOffday_overtime(int offday_overtime) {
        this.offday_overtime = offday_overtime;
    }

    public int getHoliday_overtime() {
        return holiday_overtime;
    }

    public void setHoliday_overtime(int holiday_overtime) {
        this.holiday_overtime = holiday_overtime;
    }

    public String getOffday_apply_date() {
        return offday_apply_date;
    }

    public void setOffday_apply_date(String offday_apply_date) {
        this.offday_apply_date = offday_apply_date;
    }

    public String getHoliday_apply_date() {
        return holiday_apply_date;
    }

    public void setHoliday_apply_date(String holiday_apply_date) {
        this.holiday_apply_date = holiday_apply_date;
    }
}
