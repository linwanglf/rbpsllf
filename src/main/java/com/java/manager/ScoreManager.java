package com.java.manager;

import com.java.dao.ExamScoreDao;
import com.java.model.ExamScore;
import com.java.util.DbUtil;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xxjs-gd-llf
 * DATETIME:2017/10/3 22:11
 * Description:
 */
public class ScoreManager {

    private  DbUtil dbUtil=new DbUtil();
    private ExamScoreDao examScoreDao=new ExamScoreDao();

    public List<ExamScore> getScoreList(){
        List<ExamScore> list =  new ArrayList<>();
        try {
            ResultSet resultSet = examScoreDao.scoreList(dbUtil.getCon(),null);
            ExamScore examScore = new ExamScore();
            while (resultSet.next()){
                examScore.setName(resultSet.getString("name"));
                examScore.setClassGrade(resultSet.getString("classgrade"));
                examScore.setScore(resultSet.getString("score"));
                examScore.setExamTime(resultSet.getString("examtime"));
                list.add(examScore);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
