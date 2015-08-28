package com.kituri.tankmmdatabase.data.tank;

import com.kituri.app.data.Entry;

/**
 * 钢舞姬搜索用数据
 */
public class TankSearchData extends Entry{

	private static final long serialVersionUID = 3672726097211956879L;
	
	public static final int TANK_ALL = -1;
	/**
	*  坦克类型，轻中重突轻自。
	**/
	public static final int TANK_TYPE = 0;
	public static final int TANK_CLASS = 1;
	public static final int TANK_NAME = 2;
	public static final int TANK_NATIONALITY = 3;
	public static final int TANK_STAR = 4;
	public static final int TANK_FIRE = 5;
	public static final int TANK_ARMOUR_PIERCING = 6;
	public static final int TANK_HIT = 7;
	public static final int TANK_DURABLE = 8;
	public static final int TANK_ARMOUR = 9;
	public static final int TANK_DODGE = 10;
	public static final int TANK_HIDE = 11;
	public static final int TANK_SPOT = 12;
	public static final int TANK_EQUIPMENT_SLOTS = 13;
	
//	public static final int CONDITIONAL_QUERY[] = {TANK_ALL, TANK_TYPE, TANK_CLASS, TANK_NAME, TANK_NATIONALITY,
//		TANK_STAR, TANK_FIRE, TANK_ARMOUR_PIERCING, TANK_HIT, TANK_DURABLE, TANK_ARMOUR, TANK_DODGE, TANK_HIDE,
//		TANK_SPOT, TANK_EQUIPMENT_SLOTS};
	
	public static final int DEFAULT_PAGE = 1;
	
	private int queryKey;
	private int queryValue;
	private int select = TANK_ALL;
	
	public int getSelect() {
		return select;
	}

	public void setSelect(int select) {
		this.select = select;
	}

	public int getQueryValue() {
		return queryValue;
	}

	public void setQueryValue(int queryValue) {
		this.queryValue = queryValue;
	}

	private int page;
	
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
	
	public TankSearchData(int queryKey){
		this(queryKey, 0);
	}

	public TankSearchData(int queryKey, int queryValue){
		this.queryKey = queryKey;
		this.queryValue = queryValue;
	}
	
	public TankSearchData(TankSearchData data){
		this.select = data.getSelect();
		this.page = data.getPage();
		this.queryKey = data.getQueryKey();
		this.queryValue = data.getQueryValue();
	}
	
	public int getQueryKey() {
		return queryKey;
	}

	public void setQueryKey(int queryKey) {
		this.queryKey = queryKey;
	}
	
}
