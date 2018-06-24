package com.java.manager;

import com.java.dao.AttentanceDao;
import com.java.dao.ExamScoreDao;
import com.java.model.Attentance;
import com.java.model.ExamScore;
import com.java.util.DbUtil;
import org.apache.commons.lang.StringUtils;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xxjs-gd-llf
 * DATETIME:2017/10/3 22:11
 * Description:
 */
public class AttentanceManager {

    private  DbUtil dbUtil=new DbUtil();
    private AttentanceDao attentanceDao=new AttentanceDao();

    public List<Attentance> getAttentance_planList(){
        List<Attentance> list =  new ArrayList<>();
        try {
//            ResultSet resultSet = attentanceDao.attentance_planList(dbUtil.getCon(),null);
//           // Attentance attentance = new Attentance();//此句需要放在while (resultSet.next())循环里面，否则只会读一条记录
//            while (resultSet.next()){
//                Attentance attentance = new Attentance();
//                attentance.setMenberNo(resultSet.getString("menberNo"));
//                attentance.setMenberName(resultSet.getString("menberName"));
//                attentance.setDepartment(resultSet.getString("department"));
//                attentance.setApply_date(resultSet.getString("apply_date"));
//                list.add(attentance);
//            }
            String department="设备科";
            //ResultSet resultSet = attentanceDao.attentance_planList(dbUtil.getCon(),null);
           ResultSet resultSet = attentanceDao.attentance_planList_bydepartment(dbUtil.getCon(),null,department);
            while (resultSet.next()){
                Attentance attentance = new Attentance();
                attentance.setMenberNo(resultSet.getString("menberNo"));
                //attentance.setMenberName(resultSet.getString("menberName"));
                //attentance.setDepartment(resultSet.getString("department"));
                //attentance.setApply_date(resultSet.getString("apply_date"));
                //-----------------------------------------------------
                String menberNo_sel=resultSet.getString("menberNo");
                String menberName_sel="";
                String menbertype_sel="";
                String department_sel="";
                String Offday_apply_date_sel="";
                String Holiday_apply_date_sel="";

                int Normol_overtime_sel=0;
                int Offday_overtime_sel=0;
                int Holiday_overtime_sel=0;
                String att_type_sel="";
                String att_project_sel="";
                    String type_normal="平时";
                    String type_offday="休息日";
                    String type_holiday="节假日";
                ResultSet resultSet_bymenberNo = attentanceDao.attentance_planList_bymenberNo(dbUtil.getCon(),null,menberNo_sel);
                while (resultSet_bymenberNo.next()){
                    Attentance attentance_bymenberNo = new Attentance();
                    menberName_sel=resultSet_bymenberNo.getString("menberName");
                    menbertype_sel=resultSet_bymenberNo.getString("menbertype");
                    department_sel=resultSet_bymenberNo.getString("department");
                    att_type_sel=resultSet_bymenberNo.getString("att_type");
                    att_project_sel=resultSet_bymenberNo.getString("att_project");



                    if (type_normal.equals(att_type_sel))
                    {
                        Normol_overtime_sel=Normol_overtime_sel+resultSet_bymenberNo.getInt("plan_houre");
                        Offday_apply_date_sel=Offday_apply_date_sel+ StringUtils.substring(resultSet_bymenberNo.getString("apply_date"),5,10)+",";

                    }
                    else if(type_offday.equals(att_type_sel))
                    {
                        Offday_overtime_sel=Offday_overtime_sel+resultSet_bymenberNo.getInt("plan_houre");
                        Offday_apply_date_sel=Offday_apply_date_sel+ StringUtils.substring(resultSet_bymenberNo.getString("apply_date"),5,10)+",";

                    }
                    else if(type_holiday.equals(att_type_sel))
                    {
                        Holiday_overtime_sel=Holiday_overtime_sel+resultSet_bymenberNo.getInt("plan_houre");
                        Holiday_apply_date_sel=Holiday_apply_date_sel+ StringUtils.substring(resultSet_bymenberNo.getString("apply_date"),5,10)+",";

                    }


                }
                //StringUtils.substring("中国人民共和国", 2, 4)
                attentance.setMenberName(menberName_sel);
                attentance.setDepartment(department_sel);
                attentance.setAtt_type(att_type_sel);
                attentance.setAtt_project(att_project_sel);

                attentance.setOffday_apply_date(Offday_apply_date_sel);
                attentance.setHoliday_apply_date(Holiday_apply_date_sel);
                attentance.setNormol_overtime(Normol_overtime_sel);
                attentance.setOffday_overtime(Offday_overtime_sel);
                attentance.setHoliday_overtime(Holiday_overtime_sel);
                attentance.setMenbertype(menbertype_sel);


                //-----------------------------------------------------
                list.add(attentance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
