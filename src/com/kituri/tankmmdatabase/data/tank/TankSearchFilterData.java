package com.kituri.tankmmdatabase.data.tank;

import com.kituri.app.data.Entry;
import com.kituri.tankmmdatabase.data.tech.TechData;

/**
 * 钢舞姬搜索用数据
 */
public class TankSearchFilterData extends Entry{

	private static final long serialVersionUID = 3672726097211956879L;
	
	public static final int TANK_ALL = -1;


	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getNationality() {
		return nationality;
	}
	public void setNationality(int nationality) {
		this.nationality = nationality;
	}
	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		this.star = star;
	}
	public int getStatistic() {
		return statistic;
	}
	public void setStatistic(int statistic) {
		this.statistic = statistic;
	}
	public TechData getTechData() {
		return techData;
	}
	public void setTechData(TechData techData) {
		this.techData = techData;
	}
	
	private int type;
	private int nationality;
	private int star;
	private int statistic;
	private TechData techData;
	

	public TankSearchFilterData(){
		type = TANK_ALL;
		nationality = TANK_ALL;
		star = TANK_ALL;
		statistic = TANK_ALL;
	}
	
	
	
}
