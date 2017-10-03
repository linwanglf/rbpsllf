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

    /**
     create table examscore(
     )
     */
}
