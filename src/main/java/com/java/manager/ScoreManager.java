package com.java.manager;

import com.java.dao.ExamScoreDao;
import com.java.model.ExamScore;
import com.java.util.DbUtil;
import lombok.extern.slf4j.Slf4j;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by xxjs-gd-llf
 * DATETIME:2017/10/3 22:11
 * Description:
 */
@Slf4j
public class ScoreManager {

    private  DbUtil dbUtil=new DbUtil();
    private ExamScoreDao examScoreDao=new ExamScoreDao();

    public List<ExamScore> getScoreList(){
        List<ExamScore> list =  new ArrayList<>();
        try {
            ResultSet resultSet = examScoreDao.scoreList(dbUtil.getCon(),null);
//            ExamScore examScore = new ExamScore();//这句要移到while里面，否则每次都输出的同一条记录
            while (resultSet.next()){
                ExamScore examScore = new ExamScore();
                examScore.setName(resultSet.getString("name"));
                examScore.setClassGrade(resultSet.getString("classgrade"));
                examScore.setScore(resultSet.getString("score"));
                examScore.setExamTime(resultSet.getString("examtime"));
                list.add(examScore);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Iterator<ExamScore> ite = list.iterator();
        while (ite.hasNext()){
            System.out.println(ite.next().getName());
        }
        return list;
    }
}
