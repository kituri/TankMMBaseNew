package com.kituri.tankmmdatabase.data.tech;

import com.kituri.app.data.Entry;

/**
 * 科技数据
 */
public class TechTypeData extends Entry{

	private static final long serialVersionUID = 3672726097211956879L;



	//科技名字，例：综合车身、标准弹（AP） 小分类中的type
	private String typeName;
	private int type;
	
	private String icon;
	
	//all里的总的type ，如 "主炮", "防护", "车体", "引擎", "索敌"
	private int allType;

	public TechTypeData(int allType, int type, String typeName, String icon){
		this.allType = allType;
		this.type = type;
		this.typeName = typeName;
		this.icon = icon;
	}
	
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public int getAllType() {
		return allType;
	}

	public void setAllType(int allType) {
		this.allType = allType;
	}
	
}
