package com.java.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * Created by xxjs-gd-llf
 * DATETIME:2018/1/14 16:44
 * Description:
 */
@Setter
@Getter
@ToString
public class TerminalInfo{
    /**
     * ID
     */
    private  String id;
    /**
     * 终端iP地址
     */
    private  String ip;

    /**
     * 主机名
     */
    private String hostName;
    /**
     * 采集时间
     */
    private Date collectTime;
    /**
     * CPU使用率
     */
    private String cpuUsedRate;
    /**
     * CPU温度
     */
    private  String cpuTemperature;


    /**
     * 内存使用率
     */
    private String memoryUsedRate;

    /**
     * Gac进程是否存在 1 表示还在,0表示没有
     */
    private String gacProcess;




}
