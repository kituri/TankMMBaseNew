package com.kituri.tankmmdatabase.data.tech;

import com.kituri.app.data.Entry;

/**
 * 科技数据
 */
public class TechTypeData extends Entry{

	private static final long serialVersionUID = 3672726097211956879L;

	/**
	 * 主炮
	 */
	public static final int TYPE_MAIN_GUN = 0;
	/**
	 * 防护
	 */
	public static final int TYPE_SHIELD = 1;
	/**
	 * 车体
	 */
	public static final int TYPE_BODYWORK = 2;
	/**
	 * 引擎
	 */
	public static final int TYPE_ENGINE = 3;
	/**
	 * 索敌
	 */
	public static final int TYPE_SEEK = 4;

	//科技名字，例：综合车身、标准弹（AP） 小分类中的type
	private String typeName;
	//typeName的内部属性
	private int type;
	
	private String icon;
	
	//all里的总的type ，如 "主炮", "防护", "车体", "引擎", "索敌"
	private int allType;
	
//	//科技等级 3期合计
//	private int allLv;	
	private int allLv1;
	private int allLv2;
	private int allLv3;
	
	
	//是否被选中
	private Boolean isSelect = false;

	public TechTypeData(){
		
	}
	
	public TechTypeData(int allType, int type, String typeName, String icon){
		this.allType = allType;
		this.type = type;
		this.typeName = typeName;
		this.icon = icon;
		allLv1 = -1;
		allLv2 = -1;
		allLv3 = -1;
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
	
	public Boolean getIsSelect() {
		return isSelect;
	}

	public void setIsSelect(Boolean isSelect) {
		this.isSelect = isSelect;
	}

	//记录用的tag用于sp储存本地科技级别
	public String getTag(int lv) {
		// TODO Auto-generated method stub
		//int allType, int type, String typeName,
		return typeName + allType + type + lv;
	}
	
//	//记录用的tag用于sp储存已修改的本地科技级别
//	public String getNowTag() {
//		// TODO Auto-generated method stub
//		//int allType, int type, String typeName,
//		return typeName + allType + type + "now";
//	}
	
	public int getAllLv1() {
		return allLv1;
	}

	public void setAllLv1(int allLv1) {
		this.allLv1 = allLv1;
	}

	public int getAllLv2() {
		return allLv2;
	}

	public void setAllLv2(int allLv2) {
		this.allLv2 = allLv2;
	}

	public int getAllLv3() {
		return allLv3;
	}

	public void setAllLv3(int allLv3) {
		this.allLv3 = allLv3;
	}

	
	public int getAllLv() {
		return allLv1 + allLv2 + allLv3;
	}
	
}
