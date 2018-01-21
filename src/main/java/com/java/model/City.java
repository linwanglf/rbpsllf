package com.java.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by xxjs-gd-llf
 * DATETIME:2017/9/27 17:35
 * Description:
 */
@Setter
@Getter
public class City implements Serializable {

    private static final long serialVersionUID = -7670969056193842442L;

    private int id ;
    private String citycode ;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public String getProvincecode() {
        return provincecode;
    }

    public void setProvincecode(String provincecode) {
        this.provincecode = provincecode;
    }

    private String cityname ;
    private String provincecode ;

}
