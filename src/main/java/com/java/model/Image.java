package com.java.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by xxjs-gd-llf
 * DATETIME:2017/10/10 11:11
 * Description:
 */
@Setter
@Getter
public class Image {
    private  int id;
    private  String imagePath;
    private  String userid;
    private String imageName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
