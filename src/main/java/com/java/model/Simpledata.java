package com.java.model;



import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by xxjs-gd-llf
 * DATETIME:2017/9/28 16:09
 * Description:
 */

@Setter
@Getter
public class Simpledata implements Serializable {

    public String name;
    public Integer[] data;

    public Simpledata(String name, Integer[] data){
        this.name = name;
        this.data = data;
    }

}
