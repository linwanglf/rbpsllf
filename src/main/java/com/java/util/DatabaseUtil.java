package com.java.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * Created by xxjs-gd-llf
 * DATETIME:2018/1/14 17:14
 * Description:
 */
public class DatabaseUtil {
    public static Connection conn;
    private DatabaseUtil(){
    }
    static{
        try {
            String driver = "com.mysql.jdbc.Driver";
            String url="jdbc:mysql://127.0.0.1:3306/sgopdb";
//            String url="jdbc:mysql://192.168.3.7:3306/sgopdb";
            String name="root";
            String pwd="root";

            Class.forName(driver);
            conn=DriverManager.getConnection(url, name, pwd);
            System.out.println("conn:"+conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        return conn;
    }

    public  static void main(String[] args ){
        Connection connection =  DatabaseUtil.getConnection();
        Connection connection1 = DatabaseUtil.getConnection();
        System.out.println(connection == connection1 );
    }

}
