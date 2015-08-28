package com.kituri.tankmmdatabase.data.equip;

import com.kituri.app.data.Entry;


/**
 * 装备搜索用数据
 */
public class EquipSearchData extends Entry{

	private static final long serialVersionUID = 3672726097211956879L;
	
	private int select = EquipData.TYPE_GUNREST;
	
	//插槽位置
	private int equipType;
	
	private String equipTypeName;

	public int getSelect() {
		return select;
	}

	public void setSelect(int select) {
		this.select = select;
	}

	public EquipSearchData(int select){
		this(select, EquipData.TYPE_GUNREST, null);
	}
	
	public EquipSearchData(int select, int equipType, String equipTypeName){
		this.select = select;
		this.equipType = equipType;
		this.equipTypeName = equipTypeName;
	}

	public String getEquipTypeName() {
		return equipTypeName;
	}

	public void setEquipTypeName(String equipTypeName) {
		this.equipTypeName = equipTypeName;
	}
	
	public int getEquipType() {
		return equipType;
	}
	
	public void setEquipType(int equipType) {
		this.equipType = equipType;
	}
	
	
	
}
