package com.kituri.tankmmdatabase.data.tank;

import com.kituri.app.data.ListEntry;

/**
 * 钢舞姬搜索用数据
 */
public class TankFilterList extends ListEntry{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4410889672720988339L;

	private TankSearchData tankSearchData;

	public TankSearchData getTankSearchData() {
		return tankSearchData;
	}

	public void setTankSearchData(TankSearchData tankSearchData) {
		this.tankSearchData = tankSearchData;
	}
	
}
