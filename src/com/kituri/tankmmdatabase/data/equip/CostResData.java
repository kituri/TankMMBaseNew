package com.kituri.tankmmdatabase.data.equip;

import com.kituri.app.data.Entry;
/**
 * 消耗资源的类型
 **/
public class CostResData extends Entry{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8343250625169136487L;
	
	/**
	 * 密码子
	 */
	public static final int RES_TYPE_TECH_POINT = 1;
	/**
	 * 图纸N01
	 */
	public static final int RES_TYPE_FILE_N01 = 2;
	/**
	 * 图纸N02
	 */
	public static final int RES_TYPE_FILE_N02 = 3;
	/**
	 * 图纸N03
	 */
	public static final int RES_TYPE_FILE_N03 = 4;
	/**
	 * 图纸N04
	 */
	public static final int RES_TYPE_FILE_N04 = 5;
	
	/**
	 * 装备类型01（炮座）
	 */
	public static final int RES_TYPE_EQUIP_TYPE_01 = 6;
	
	/**
	 * 装备类型07(特殊)
	 */
	public static final int RES_TYPE_EQUIP_TYPE_07 = 7;
	
	public int getResType() {
		return resType;
	}
	public void setResType(int resType) {
		this.resType = resType;
	}
	public int getResCount() {
		return resCount;
	}
	public void setResCount(int resCount) {
		this.resCount = resCount;
	}
	//资源类型
	private int resType;
	//资源数量
	private int resCount;
	
	public CostResData(int resType, int resCount){
		this.resType = resType;
		this.resCount = resCount;
	}
	
}
