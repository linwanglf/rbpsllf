package com.java.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.java.model.PageBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.java.model.Auth;
import com.java.model.User;
import com.java.util.StringUtil;

public class AuthDao {

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
	 * @param authIds
	 * @return
	 * @throws Exception
	 */
	public JSONArray getAuthsByParentId(Connection con,String parentId,String authIds)throws Exception{
		JSONArray jsonArray=this.getAuthByParentId(con, parentId,authIds);
		for(int i=0;i<jsonArray.size();i++){
			JSONObject jsonObject=jsonArray.getJSONObject(i);
			if("open".equals(jsonObject.getString("state"))){
				continue;
			}else{
				jsonObject.put("children", getAuthsByParentId(con,jsonObject.getString("id"),authIds));
			}
		}
		return jsonArray;
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

	public int authAdd(Connection con,Auth auth)throws Exception{
		String sql="insert into t_auth values(null,?,?,?,?,'open',?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, auth.getAuthName());
		pstmt.setString(2, auth.getAuthPath());
		pstmt.setInt(3, auth.getParentId());
		pstmt.setString(4, auth.getAuthDescription());
		pstmt.setString(5, auth.getIconCls());
		return pstmt.executeUpdate();
	}

    //项目SQL语句中的?对应的就是setString的字段
    public int menberAdd(Connection con,Auth menber)throws Exception{
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
	public int authUpdate(Connection con,Auth auth)throws Exception{
		String sql="update t_auth set authName=?,authPath=?,authDescription=?,iconCls=? where authId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, auth.getAuthName());
		pstmt.setString(2, auth.getAuthPath());
		pstmt.setString(3, auth.getAuthDescription());
		pstmt.setString(4, auth.getIconCls());
		pstmt.setInt(5, auth.getAuthId());
		return pstmt.executeUpdate();
	}

    public int menberUpdate(Connection con,Auth menber)throws Exception{
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


    public ResultSet personList(Connection con, PageBean pageBean, Auth auth)throws Exception{

        String stemp="000000";
        StringBuffer sb=new StringBuffer("select * from t_menber where  menberNo NOT IN('"+stemp+"')");
        //StringBuffer sb=new StringBuffer("select * from t_menber where menberNo NOT IN('000000')");
        if(StringUtil.isNotEmpty(auth.getMenberName())){
            sb.append(" and menberName like '%"+auth.getMenberName()+"%'");
        }
        if(auth.getParentId()!=-1){
            sb.append(" and parentId="+auth.getParentId());
        }
        if(pageBean!=null){
            sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());
        }
        PreparedStatement pstmt=con.prepareStatement(sb.toString());
        return pstmt.executeQuery();
    }

    public int personCount(Connection con,Auth auth)throws Exception{

        String stemp="000000";
        StringBuffer sb=new StringBuffer("select count(*) as total from t_menber where menberNo NOT IN('"+stemp+"')");
        //StringBuffer sb=new StringBuffer("select count(*) as total from t_menber where menberNo NOT IN('000000')");
       if(StringUtil.isNotEmpty(auth.getMenberName())){
           sb.append(" and menberName like '%"+auth.getMenberName()+"%'");
       }
        if(auth.getParentId()!=-1){
            sb.append(" and parentId="+auth.getParentId());
        }

        PreparedStatement pstmt=con.prepareStatement(sb.toString());
        ResultSet rs=pstmt.executeQuery();
        if(rs.next()){
            return rs.getInt("total");
        }else{
            return 0;
        }
    }
}
