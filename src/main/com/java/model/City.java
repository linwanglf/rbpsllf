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
    private String cityname ;
    private String provincecode ;

}
