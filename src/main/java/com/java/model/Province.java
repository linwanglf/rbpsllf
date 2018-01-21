package com.java.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by xxjs-gd-llf
 * DATETIME:2017/9/27 17:34
 * Description:
 */
@Getter
@Setter
public class Province implements Serializable{
    private int id ;
    private String provincecode ;
    private String provincename ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProvincecode() {
        return provincecode;
    }

    public void setProvincecode(String provincecode) {
        this.provincecode = provincecode;
    }

    public String getProvincename() {
        return provincename;
    }

    public void setProvincename(String provincename) {
        this.provincename = provincename;
    }

    public String getRegioncode() {
        return regioncode;
    }

    public void setRegioncode(String regioncode) {
        this.regioncode = regioncode;
    }

    private String  regioncode ;

}
