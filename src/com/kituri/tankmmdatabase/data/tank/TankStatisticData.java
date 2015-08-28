package com.kituri.tankmmdatabase.data.tank;

import com.kituri.app.data.Entry;

/**
 * 钢舞姬面板9项数据
 */
public class TankStatisticData extends Entry{


	/**
	 * 
	 */
	private static final long serialVersionUID = 5092219852081843593L;
	static public final int STATISTIC_FIRE = 0;
	static public final int STATISTIC_ARMOURPIERCING = 1;
	static public final int STATISTIC_HIT = 2;
	static public final int STATISTIC_DURABLE = 3;
	static public final int STATISTIC_ARMOUR = 4;
	static public final int STATISTIC_DODGE = 5;
	static public final int STATISTIC_HIDE = 6;
	static public final int STATISTIC_SPOT = 7;
	static public final int STATISTIC_RANGE = 8;
	
	private int statistic;
	private int value;

	public TankStatisticData(int statistic, int value){
		this.statistic = statistic;
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getStatistic() {
		return statistic;
	}

	public void setStatistic(int statistic) {
		this.statistic = statistic;
	}
	
}
