package com.java.dao;


import com.java.model.Select;
import com.java.model.PageBean;
import com.java.util.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.text.SimpleDateFormat;

public class SelectDao {

	/**
	 * 根据parentId,authId返回Menu对象
	 * @param con
	 * @param parentId
	 * @param authIds
	 * @return
	 * @throws Exception
	 */


	public JSONArray getAuthByParentId(Connection con,String parentId,String authIds)throws Exception{
		JSONArray jsonArray=new JSONArray();
		String sql="select * from t_auth where parentId=? and authId in ("+authIds+")";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, parentId);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("id", rs.getInt("authId"));
			jsonObject.put("text", rs.getString("authName"));
			if(!hasChildren(con, rs.getString("authId"), authIds)){
				jsonObject.put("state", "open");
			}else{
				jsonObject.put("state", rs.getString("state"));				
			}
			jsonObject.put("iconCls", rs.getString("iconCls"));
			JSONObject attributeObject=new JSONObject();
			attributeObject.put("authPath", rs.getString("authPath"));
			jsonObject.put("attributes", attributeObject);
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}
	
	private boolean hasChildren(Connection con,String parentId,String authIds)throws Exception{
		String sql="select * from t_auth where parentId=? and authId in ("+authIds+")";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, parentId);
		ResultSet rs=pstmt.executeQuery();
		return rs.next();
	}

	/**
	 * 返回所有的Menu
	 * @param con
	 * @param parentId
	 * @param classIds
	 * @return
	 * @throws Exception
	 */
	public JSONArray getAuthsByParentId(Connection con,String parentId,String classIds)throws Exception{
		JSONArray jsonArray=this.getAuthByParentId(con, parentId,classIds);
		for(int i=0;i<jsonArray.size();i++){
			JSONObject jsonObject=jsonArray.getJSONObject(i);
			if("open".equals(jsonObject.getString("state"))){
				continue;
			}else{
				jsonObject.put("children", getAuthsByParentId(con,jsonObject.getString("id"),classIds));
			}
		}
		return jsonArray;
	}



    public JSONArray storecountList(Connection con,String ClassNO)throws Exception{
        JSONArray jsonArray=new JSONArray();
        String sql="select * from t_product_store where product_store_select_ClassNO= ?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, ClassNO);

        ResultSet rs=pstmt.executeQuery();
        while(rs.next()){
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("product_store_Name", rs.getString("product_store_Name"));
            jsonObject.put("product_store_Count_Input", rs.getString("product_store_Count_Input"));
            jsonObject.put("product_store_select_ClassNO", rs.getString("product_store_select_ClassNO"));
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    public int updatePresetLeftNum(Connection con,Select select,String ClassNO )throws Exception{
        String sql="update t_product_store set product_store_Count_Input= product_store_Count_Input-1,product_store_select_date=now() where product_store_NO=? and product_store_select_ClassNO=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, select.getProduct_store_NO());
        pstmt.setString(2, ClassNO);
        return pstmt.executeUpdate();

    }
    public int   checkzero(Connection con,Select select,String ClassNO )throws Exception{
        String sql="select product_store_Count_Input as total from t_product_store where  product_store_NO=? and product_store_select_ClassNO=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, select.getProduct_store_NO());
        pstmt.setString(2, ClassNO);
        System.out.println("SQL:" + sql);
        System.out.println("parame1 product_store_no : " + select.getProduct_store_NO() );
        System.out.println("parm2  Classno " + ClassNO  );
        ResultSet rs=pstmt.executeQuery();
        if(rs.next()){
            return rs.getInt("total");
        }else{
            return 0;
        }
    }
    public void   QuerySelct(Connection con,Select select,String UserNO )throws Exception{
        String sql="SELECT * from t_menber  where  menberNo=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, UserNO);
        ResultSet rs=pstmt.executeQuery();
        while(rs.next()){
            select.setMenberName( rs.getString("menberName"));
            select.setMenberNo( rs.getString("menberNo"));
            select.setClassname( rs.getString("classname"));
            select.setMenberName( rs.getString("menberName"));

            select.setDivision( rs.getString("division"));
            select.setDepartment( rs.getString("department"));
            select.setDepartmentname( rs.getString("departmentname"));
        }

    }
//确认是否已经有投票记录
    public int checkonlyselectBymenberNo(Connection con,String menberNO)throws Exception{
        String sql="select count(*) as total from t_ticket_record where ticket_record_menberNO=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, menberNO);
        ResultSet rs=pstmt.executeQuery();
        if(rs.next()){
            return rs.getInt("total");
        }else{
            return 0;
        }
    }
    public void   QuerySelctProductName(Connection con,Select select,String product_store_NO)throws Exception {
        String sql = "SELECT * from t_product_store  where  product_store_NO=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, product_store_NO);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            select.setProduct_store_Name( rs.getString("product_store_Name"));
        }
    }

    public int InsertselectRecord(Connection con,Select select)throws Exception{

        String UserOrderNO="000";
        String sql="insert into t_ticket_record values(null,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstmt=con.prepareStatement(sql);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String timenow=df.format(new Date());// new Date()为获取当前系统时间
        pstmt.setString(1,select.getProduct_store_Name());//选择的物品名称
        pstmt.setString(2, UserOrderNO);//投票顺序号
        pstmt.setString(3, select.getMenberName());// 选择者的用户名
        pstmt.setString(4, select.getMenberNo());//选择者的工号
        pstmt.setString(5, select.getProduct_store_NO());//物品编号
        pstmt.setString(6, select.getClassname());//选择者所在的班次
        pstmt.setString(7, timenow);
        pstmt.setString(8, select.getDivision());
        pstmt.setString(9, select.getDepartment());
        pstmt.setString(10, select.getDepartmentname());
        pstmt.setString(11, select.getClassname());


        return pstmt.executeUpdate();
    }

	public JSONArray getCheckedAuthByParentId(Connection con,String parentId,String authIds)throws Exception{
		JSONArray jsonArray=new JSONArray();
		String sql="select * from t_auth where parentId=? ";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, parentId);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			JSONObject jsonObject=new JSONObject();
			int authId=rs.getInt("authId");
			jsonObject.put("id", authId);
			jsonObject.put("text", rs.getString("authName"));
			jsonObject.put("state", rs.getString("state"));
			jsonObject.put("iconCls", rs.getString("iconCls"));
			if(StringUtil.existStrArr(authId+"", authIds.split(","))){
				jsonObject.put("checked", true);
			}
			JSONObject attributeObject=new JSONObject();
			attributeObject.put("authPath", rs.getString("authPath"));
			jsonObject.put("attributes", attributeObject);
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}

    public JSONArray getCheckedAuthByParentId_KQ(Connection con,String parentId,String authIds)throws Exception{
        JSONArray jsonArray=new JSONArray();
       // String sql="select * from t_menber where parentId=?";
        String stemp="000000";
        String sql= "select * from t_menber where parentId=? and menberNo =?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, parentId);
        pstmt.setString(2,stemp);
        ResultSet rs=pstmt.executeQuery();
        while(rs.next()){
            JSONObject jsonObject=new JSONObject();
            int authId=rs.getInt("menberId");
            jsonObject.put("id", authId);
            jsonObject.put("text", rs.getString("menberName"));
            jsonObject.put("state", rs.getString("state"));
            jsonObject.put("iconCls", rs.getString("iconCls"));
            if(StringUtil.existStrArr(authId+"", authIds.split(","))){
                jsonObject.put("checked", true);
            }
            JSONObject attributeObject=new JSONObject();
            attributeObject.put("authPath", rs.getString("menberPath"));
            jsonObject.put("attributes", attributeObject);
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    public JSONArray getCheckedMenberlistByParentId(Connection con,String parentId,String authIds)throws Exception{
        JSONArray jsonArray=new JSONArray();
        //String sql="select * from t_menber where parentId=? ";
        String sql="select * from t_auth where parentId=? ";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, parentId);
        ResultSet rs=pstmt.executeQuery();
        while(rs.next()){
            JSONObject jsonObject=new JSONObject();
            int authId=rs.getInt("authId");
            jsonObject.put("id", authId);
            jsonObject.put("text", rs.getString("authName"));
            jsonObject.put("state", rs.getString("state"));
            jsonObject.put("iconCls", rs.getString("iconCls"));
            if(StringUtil.existStrArr(authId+"", authIds.split(","))){
                jsonObject.put("checked", true);
            }
            JSONObject attributeObject=new JSONObject();
            attributeObject.put("authPath", rs.getString("authPath"));
            jsonObject.put("attributes", attributeObject);
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }


	public JSONArray getCheckedAuthsByParentId(Connection con,String parentId,String authIds)throws Exception{
		JSONArray jsonArray=this.getCheckedAuthByParentId(con, parentId,authIds);
		for(int i=0;i<jsonArray.size();i++){
			JSONObject jsonObject=jsonArray.getJSONObject(i);
			if("open".equals(jsonObject.getString("state"))){
				continue;
			}else{
				jsonObject.put("children", getCheckedAuthsByParentId(con,jsonObject.getString("id"),authIds));
			}
		}
		return jsonArray;
	}

    public JSONArray getCheckedAuthsByParentId_KQ(Connection con,String parentId,String authIds)throws Exception{
        JSONArray jsonArray=this.getCheckedAuthByParentId_KQ(con, parentId,authIds);
        for(int i=0;i<jsonArray.size();i++){
            JSONObject jsonObject=jsonArray.getJSONObject(i);
            if("open".equals(jsonObject.getString("state"))){
                continue;
            }else{
                jsonObject.put("children", getCheckedAuthsByParentId_KQ(con,jsonObject.getString("id"),authIds));
            }
        }
        return jsonArray;
    }

    public JSONArray getCheckedMenberlistsByParentId(Connection con,String parentId,String menberIds)throws Exception{
        JSONArray jsonArray=this.getCheckedMenberlistByParentId(con, parentId,menberIds);
        for(int i=0;i<jsonArray.size();i++){
            JSONObject jsonObject=jsonArray.getJSONObject(i);
            if("open".equals(jsonObject.getString("state"))){
                continue;
            }else{
                jsonObject.put("children",getCheckedMenberlistsByParentId(con,jsonObject.getString("id"),menberIds));
            }
        }
        return jsonArray;
    }
    public JSONArray getCheckedMenberByParentId(Connection con,String parentId,String menberIds)throws Exception{
        JSONArray jsonArray=this.getCheckedMenberlistByParentId(con, parentId,menberIds);
        for(int i=0;i<jsonArray.size();i++){
            JSONObject jsonObject=jsonArray.getJSONObject(i);
            if("open".equals(jsonObject.getString("state"))){
                continue;
            }else{
                jsonObject.put("children", getCheckedMenberlistsByParentId(con,jsonObject.getString("id"),menberIds));
            }
        }
        return jsonArray;
    }

	public JSONArray getTreeGridAuthByParentId(Connection con,String parentId)throws Exception{
		JSONArray jsonArray=new JSONArray();
		String sql="select * from t_auth where parentId=? ";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, parentId);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("id", rs.getInt("authId"));
			jsonObject.put("text", rs.getString("authName"));
			jsonObject.put("state", rs.getString("state"));
			jsonObject.put("iconCls", rs.getString("iconCls"));
			jsonObject.put("authPath", rs.getString("authPath"));
			jsonObject.put("authDescription", rs.getString("authDescription"));
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}
    public JSONArray getTreeGridMenberByParentId(Connection con,String parentId)throws Exception{
        JSONArray jsonArray=new JSONArray();
        String stemp="000000";
        String sql="select * from t_menber where parentId=? and menberNo=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, parentId);
        pstmt.setString(2, stemp);
        ResultSet rs=pstmt.executeQuery();
        while(rs.next()){
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("id", rs.getInt("menberId"));
            jsonObject.put("text", rs.getString("menberName"));
            jsonObject.put("state", rs.getString("state"));
            jsonObject.put("iconCls", rs.getString("iconCls"));
            jsonObject.put("menberPath", rs.getString("menberPath"));
            jsonObject.put("menberDescription", rs.getString("menberDescription"));
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }
	public JSONArray getListByParentId(Connection con,String parentId)throws Exception{
		JSONArray jsonArray=this.getTreeGridAuthByParentId(con, parentId);
		for(int i=0;i<jsonArray.size();i++){
			JSONObject jsonObject=jsonArray.getJSONObject(i);
			if("open".equals(jsonObject.getString("state"))){
				continue;
			}else{
				jsonObject.put("children", getListByParentId(con,jsonObject.getString("id")));
			}
		}
		return jsonArray;
	}

    public JSONArray getMenberListByParentId(Connection con,String parentId)throws Exception{
        JSONArray jsonArray=this.getTreeGridMenberByParentId(con, parentId);
        for(int i=0;i<jsonArray.size();i++){
            JSONObject jsonObject=jsonArray.getJSONObject(i);
            if("open".equals(jsonObject.getString("state"))){
                continue;
            }else{
                jsonObject.put("children", getMenberListByParentId(con,jsonObject.getString("id")));
            }
        }
        return jsonArray;
    }

	public int authAdd(Connection con,Select select)throws Exception{
		String sql="insert into t_auth values(null,?,?,?,?,'open',?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, select.getAuthName());
		pstmt.setString(2, select.getAuthPath());
		pstmt.setInt(3, select.getParentId());
		pstmt.setString(4, select.getAuthDescription());
		pstmt.setString(5, select.getIconCls());
		return pstmt.executeUpdate();
	}

    //项目SQL语句中的?对应的就是setString的字段
    public int menberAdd(Connection con,Select menber)throws Exception{
        String sql="insert into t_menber values(null,?,?,?,?,'open',?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstmt=con.prepareStatement(sql);
        //pstmt.setString(1, menber.getAuthName());
        pstmt.setString(1, menber.getMenberName());
        pstmt.setString(2, menber.getAuthPath());
        pstmt.setInt(3, menber.getParentId());
        pstmt.setString(4, menber.getAuthDescription());
        pstmt.setString(5, menber.getIconCls());

        pstmt.setString(6, menber.getHomeaddress());
        pstmt.setString(7, menber.getPoliticalStatus());
        pstmt.setString(8, menber.getPursuits_talents());
        pstmt.setString(9, menber.getCompanyold());//原来单位
        pstmt.setString(10, menber.getCertify());
        pstmt.setString(11, menber.getClasstype());
        pstmt.setString(12, menber.getPhoto());
        pstmt.setString(13, menber.getPrize());

        pstmt.setString(14, menber.getProfession());
        pstmt.setString(15, menber.getShcool());
        pstmt.setString(16, menber.getPhoneEMG());
        pstmt.setString(17, menber.getEmail());
        pstmt.setString(18, menber.getPhone());
        pstmt.setString(19, menber.getIDcode());
        pstmt.setString(20, menber.getMarriage());
        pstmt.setString(21, menber.getMenbertype());
        pstmt.setString(22, menber.getSex());
        pstmt.setString(23, menber.getExpense_center());
        pstmt.setString(24, menber.getPosition());

        pstmt.setString(25, menber.getEntercompany_date());
        pstmt.setString(26, menber.getWork_ege());
        pstmt.setString(27, menber.getBirthday());
        pstmt.setString(28, menber.getClassname());
        pstmt.setString(29, menber.getDivision());
        pstmt.setString(30, menber.getDepartment());
        pstmt.setString(31, menber.getDepartmentname());
        pstmt.setString(32, menber.getMenu_level());
        pstmt.setString(33, menber.getMenberNo());
        return pstmt.executeUpdate();
    }
	public int authUpdate(Connection con,Select select)throws Exception{
		String sql="update t_auth set authName=?,authPath=?,authDescription=?,iconCls=? where authId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, select.getAuthName());
		pstmt.setString(2, select.getAuthPath());
		pstmt.setString(3, select.getAuthDescription());
		pstmt.setString(4, select.getIconCls());
		pstmt.setInt(5, select.getAuthId());
		return pstmt.executeUpdate();
	}

    public int menberUpdate(Connection con,Select menber)throws Exception{
        String sql="update t_menber set menberName=?,menberPath=?,menberDescription=?,iconCls=? where menberId=?";
        //String sql="update t_menber set menberName=? where menberId=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
       // pstmt.setString(1, menber.getAuthName());
        pstmt.setString(1, menber.getMenberName());
        pstmt.setString(2, menber.getAuthPath());
        pstmt.setString(3, menber.getAuthDescription());
        pstmt.setString(4, menber.getIconCls());
        //pstmt.setInt(5, menber.getAuthId());
        pstmt.setInt(5, menber.getMenberId());
        return pstmt.executeUpdate();
    }

	public boolean isLeaf(Connection con,String authId)throws Exception{
		String sql="select * from t_auth where parentId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, authId);
		ResultSet rs=pstmt.executeQuery();
		return !rs.next();
	}

    public boolean isLeafmenber(Connection con,String menberId)throws Exception{
        String sql="select * from t_menber where parentId=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, menberId);
        ResultSet rs=pstmt.executeQuery();
        return !rs.next();
    }

	public int updateStateByAuthId(Connection con,String state,String authId)throws Exception{
		String sql="update t_auth set state=? where authId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, state);
		pstmt.setString(2, authId);
		return pstmt.executeUpdate();
	}
    public int updateStateByAuthIdmenber(Connection con,String state,String menberId)throws Exception{
        String sql="update t_menber set state=? where menberId=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, state);
        pstmt.setString(2, menberId);
        return pstmt.executeUpdate();
    }

	public int authDelete(Connection con,String authId)throws Exception{
		String sql="delete from t_auth where authId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, authId);
		return pstmt.executeUpdate();
	}

    public int menberDelete(Connection con,String menberId)throws Exception{
        String sql="delete from t_menber where menberId=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, menberId);
        return pstmt.executeUpdate();
    }

    public int updateStateByMenberId(Connection con,String state,String authId)throws Exception{
        String sql="update t_menber set state=? where menberId=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, state);
        pstmt.setString(2, authId);
        return pstmt.executeUpdate();
    }
	public int getAuthCountByParentId(Connection con,String parentId)throws Exception{
		String sql="select count(*) as total from t_auth where parentId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, parentId);
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			return rs.getInt("total");
		}else{
			return 0;
		}
	}

    public int getMenberCountByParentId(Connection con,String parentId)throws Exception{
        String sql="select count(*) as total from t_menber where parentId=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, parentId);
        ResultSet rs=pstmt.executeQuery();
        if(rs.next()){
            return rs.getInt("total");
        }else{
            return 0;
        }
    }


    public ResultSet personList(Connection con, PageBean pageBean, Select select)throws Exception{
        String stemp="000000";
        //StringBuffer sb=new StringBuffer("select * from t_ticket_record");
        StringBuffer sb=new StringBuffer("select * from t_ticket_record where ticket_record_menberNO NOT IN('"+stemp+"')");
        if(StringUtil.isNotEmpty(select.getMenberName())){
            sb.append(" and ticket_record_menbername like '%"+select.getMenberName()+"%'");
            System.out.println("start username 1111111 ");
            System.out.println(select.getMenberName());
        }
        if(StringUtil.isNotEmpty(select.getMenberNo())){
            sb.append(" and ticket_record_menberNO like '%"+select.getMenberNo()+"%'");
            System.out.println("start username 222222 ");
            System.out.println(select.getMenberNo());
        }


//        if(select.getParentId()!=-1){
//            sb.append(" and parentId="+select.getParentId());
//        }

        if(pageBean!=null){
            sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());
        }

        PreparedStatement pstmt=con.prepareStatement(sb.toString());
        return pstmt.executeQuery();
    }

    public int personCount(Connection con,Select select)throws Exception{

        StringBuffer sb=new StringBuffer("select count(*) as total from t_ticket_record");

        PreparedStatement pstmt=con.prepareStatement(sb.toString());
        ResultSet rs=pstmt.executeQuery();
        if(rs.next()){
            return rs.getInt("total");
        }else{
            return 0;
        }
    }
}
