package com.java.model;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class Region implements Serializable{
	private int id;
    private String regioncode;
	private String regionname;


}
