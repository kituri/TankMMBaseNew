package com.kituri.tankmmdatabase.data.tank;

import com.kituri.app.data.Entry;

/**
 * 钢舞姬搜索用数据
 */
public class TankSearchFilterItemData extends Entry{

	private static final long serialVersionUID = 3672726097211956879L;
	
	public String getFilterName() {
		return filterName;
	}
	public void setFilterName(String filterName) {
		this.filterName = filterName;
	}
	public int getFilterValue() {
		return filterValue;
	}
	public void setFilterValue(int filterValue) {
		this.filterValue = filterValue;
	}
	public int getIcon() {
		return icon;
	}
	public void setIcon(int icon) {
		this.icon = icon;
	}
	public int getSelect() {
		return select;
	}
	public void setSelect(int select) {
		this.select = select;
	}
	public int getFilterType() {
		return filterType;
	}
	public void setFilterType(int filterType) {
		this.filterType = filterType;
	}
	
	public TankSearchFilterItemData(int filterType, String filterName, int filterValue, int icon){
		this(filterType, filterName, filterValue, icon, TankSearchFilterData.TANK_ALL);
	}
	
	public TankSearchFilterItemData(int filterType, String filterName, int filterValue, int icon, int select){
		this.filterType = filterType;
		this.filterName = filterName;
		this.filterValue = filterValue;
		this.icon = icon;
		this.select = select;
	}
	
	private int filterType;
	private String filterName;
	private int filterValue;
	private int icon;
	private int select;//数值为 filterValue里的一种
	
	
}
