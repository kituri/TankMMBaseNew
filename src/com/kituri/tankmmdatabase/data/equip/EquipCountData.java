package com.kituri.tankmmdatabase.data.equip;

import com.kituri.app.data.Entry;
/**
 * 用于测量装备的数量
 */
public class EquipCountData extends Entry{

	private static final long serialVersionUID = -1584553380358777891L;
	private int counts;
	private int lv;
	private Boolean isSp;
	
	public Boolean getIsSp() {
		return isSp;
	}
	public void setIsSp(Boolean isSp) {
		this.isSp = isSp;
	}
	public int getCounts() {
		return counts;
	}
	public void setCounts(int counts) {
		this.counts = counts;
	}
	public int getLv() {
		return lv;
	}
	public void setLv(int lv) {
		this.lv = lv;
	}
	
	
}
