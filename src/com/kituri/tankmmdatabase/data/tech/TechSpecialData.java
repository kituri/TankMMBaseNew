package com.kituri.tankmmdatabase.data.tech;

import com.kituri.app.data.Entry;

/**
 * 装备的特殊作用
 */
public class TechSpecialData extends Entry{

	private static final long serialVersionUID = 5092219852081843593L;

	//编号id(0号)
	private int id;
	//名字(稳定)
	private String name;
	//介绍(从接近阶段开始提高命中5点)
	private String description;

	public TechSpecialData(int id, String name, String description){
		this.id = id;
		this.name = name;
		this.description = description;
	}
	
	public TechSpecialData(String name, String description){
		this(-1, name, description);
	}
	
	public TechSpecialData(String name){
		this(name, "");
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
