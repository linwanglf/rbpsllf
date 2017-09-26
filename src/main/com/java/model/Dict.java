package com.java.model;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by xxjs-gd-llf
 * DATETIME:2017/9/25 22:24
 * Description:
 */
@Setter
@Getter
public class Dict implements Serializable{

    /**
     * ID
     */
    private int dictId;

    /**
     * 类型
     */
    private String dictType;

    /**
     * 名称
     */
    private String dictName;

    /**
     * 描述
     */
    private String dictDesc;

    /**
     * 状态
     */
    private String stat;

    /**
     * 顺序
     */
    private int showSeq;

    /**
     * 默认
     */
    private String isDefault;

    /**
     * 是否可修改
     */
    private String canModify;

    public int getDictId() {
        return dictId;
    }

    public void setDictId(int dictId) {
        this.dictId = dictId;
    }

    public String getDictType() {
        return dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public String getDictDesc() {
        return dictDesc;
    }

    public void setDictDesc(String dictDesc) {
        this.dictDesc = dictDesc;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public int getShowSeq() {
        return showSeq;
    }

    public void setShowSeq(int showSeq) {
        this.showSeq = showSeq;
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    public String getCanModify() {
        return canModify;
    }

    public void setCanModify(String canModify) {
        this.canModify = canModify;
    }

}
