package com.java.manager;

import com.java.dao.ImageDao;
import com.java.model.Image;
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
public class ImageManager {

    private DbUtil dbUtil=new DbUtil();
    private ImageDao imageDao=new ImageDao();

    public List<Image> getImageList( Image image){
        List<Image> list =  new ArrayList<>();
        try {
            ResultSet resultSet = imageDao.imageList(dbUtil.getCon(),null,image );
            while (resultSet.next()){
                Image tImage = new Image();
                tImage.setUserid(resultSet.getString("user_id"));
                tImage.setImagePath(resultSet.getString("image_path"));
                tImage.setImageName(resultSet.getString("image_name"));
                list.add(tImage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Iterator<Image> ite = list.iterator();
        while (ite.hasNext()){
            System.out.println(ite.next().getImageName());
        }
        return list;
    }

    public void saveImage(String imagePath,String imageName){

        try {
            Image image = new Image();
            image.setImagePath(imagePath);
            image.setImageName(imageName);
            image.setUserid("01");
            imageDao.imageAdd(dbUtil.getCon(),image);//按行处理

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
