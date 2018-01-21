package com.java.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by xxjs-gd-llf
 * DATETIME:2017/10/3 21:58
 * Description:
 */
@Getter
@Setter
public class ExamScore {
    /**
     * 姓名
     */
    private String name;

    /**
     * 班级
     */
    private String classGrade;

    /**
     * 笔试成绩
     */
    private String Score;

    /**
     * 考试时间
     */
    private String examTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassGrade() {
        return classGrade;
    }

    public void setClassGrade(String classGrade) {
        this.classGrade = classGrade;
    }

    public String getScore() {
        return Score;
    }

    public void setScore(String score) {
        Score = score;
    }

    public String getExamTime() {
        return examTime;
    }

    public void setExamTime(String examTime) {
        this.examTime = examTime;
    }

    /**
     create table examscore(
     )
     */
}
