package com.kituri.tankmmdatabase.data;

import com.kituri.app.data.Entry;

public class DataBaseCategoryData extends Entry{

	/**
	 * 
	 */
	private static final long serialVersionUID = 763378975387934242L;

	static public final int TYPE_EQUIP = 1;
	static public final int TYPE_TECHNOLOGY = 2;
	static public final int TYPE_TANK = 3;
	static public final int TYPE_DEVELOPMENT = 4;
	static public final int TYPE_MAP = 5;	
	static public final int TYPE_SKILL = 6;
	static public final int TYPE_QUEST = 7;
	static public final int TYPE_METAPHYSICS = 8;
	static public final int TYPE_SATELLITE = 9;
	
	private int type;
	private String name;
	private int resId;
	private Boolean enabled;
	
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public int getResId() {
		return resId;
	}
	public void setResId(int resId) {
		this.resId = resId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public static int getTypeEquip() {
		return TYPE_EQUIP;
	}
	
}
