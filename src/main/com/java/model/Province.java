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
    private String  regioncode ;

}
