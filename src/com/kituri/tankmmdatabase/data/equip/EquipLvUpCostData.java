package com.kituri.tankmmdatabase.data.equip;

import com.kituri.app.data.Entry;
/**
 * 装备升级的消耗
 */

public class EquipLvUpCostData extends Entry{

	private static final long serialVersionUID = -2332395323375506211L;
	public int getEquipCounts() {
		return equipCounts;
	}
	public void setEquipCounts(int equipCounts) {
		this.equipCounts = equipCounts;
	}
	public int getEquipLv() {
		return equipLv;
	}
	public void setEquipLv(int equipLv) {
		this.equipLv = equipLv;
	}
	public int getMapCounts() {
		return mapCounts;
	}
	public void setMapCounts(int mapCounts) {
		this.mapCounts = mapCounts;
	}
	public int getMapLv() {
		return mapLv;
	}
	public void setMapLv(int mapLv) {
		this.mapLv = mapLv;
	}
	public int getCostPoint() {
		return costPoint;
	}
	public void setCostPoint(int costPoint) {
		this.costPoint = costPoint;
	}
	
	/**
	 * 非炮座火药的情况
	 */
	public int getSpEquipCounts(){
		if(equipLv > 6){
			return equipCounts;
		}
		return equipCounts - 1;
	}
	
	/**
	 * 非炮座火药的情况
	 */
	public int getNorEquipCounts(){
		if(equipLv > 6){
			return 0;
		}
		return 1;
	}
	
	/**
	 * 低级装备需求的数量
	 */
	private int equipCounts;
	/**
	 * 低级装备需求的级别
	 */
	private int equipLv;
	/**
	 * 图纸需求的数量
	 */
	private int mapCounts;
	/**
	 * 图纸需求的级别
	 */
	private int mapLv;
	/**
	 * 密码子
	 */
	private int costPoint;

	
}
