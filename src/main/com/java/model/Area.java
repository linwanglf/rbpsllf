package com.java.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Area implements Serializable{
	private int id;
	private String areacode;
	private String areaname;
	private String citycode;


	
}
