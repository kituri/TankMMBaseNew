package com.kituri.tankmmdatabase.data.tech;

import com.kituri.app.data.Entry;


/**
 * 科技搜索用数据
 */
public class TechSearchData extends Entry{

	private static final long serialVersionUID = 3672726097211956879L;
	
	private int select = TechData.TYPE_MAIN_GUN;
	
	//科技种类(主炮、车体、索敌等)
	private int type;
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	private String typeName;

	public int getSelect() {
		return select;
	}

	public void setSelect(int select) {
		this.select = select;
	}

	public TechSearchData(int select){
		this(select, TechData.TYPE_MAIN_GUN, null);
	}
	
	public TechSearchData(int select, int type, String typeName){
		this.select = select;
		this.type = type;
		this.typeName = typeName;
	}
	
}
