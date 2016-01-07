package com.kituri.tankmmdatabase.data.tech;

import java.util.List;

import com.kituri.tankmmdatabase.data.tank.TankStatisticData;

/**
 * 科技数据
 */
public class TechData extends TechTypeData{

	private static final long serialVersionUID = 3672726097211956879L;
	
	

	//科技名字，例：被帽风帽偏重穿甲弹（中口径）
	private String techName;
//	//allType，主炮、车身、引擎等
//	private int allType;
//	//type，AP、HE等
//	private int type;
	private String techIcon;
	private int techId;
	private int rank;
	//置换费用(银币)
	private int costMoney;
	
	//增加的属性 和 其值
	private List<TankStatisticData> statisticDatas;
	
	//特殊作用
	private List<TechSpecialData> specialDatas;

	public List<TankStatisticData> getStatisticDatas() {
		return statisticDatas;
	}
	public void setStatisticDatas(List<TankStatisticData> statisticDatas) {
		this.statisticDatas = statisticDatas;
	}
	public List<TechSpecialData> getSpecialDatas() {
		return specialDatas;
	}
	public void setSpecialDatas(List<TechSpecialData> specialDatas) {
		this.specialDatas = specialDatas;
	}
	
	public int getTechId() {
		return techId;
	}

	public void setTechId(int techId) {
		this.techId = techId;
	}

	public String getTechName() {
		return techName;
	}

	public void setTechName(String techName) {
		this.techName = techName;
	}
	
	public String getTechIcon() {
		return techIcon;
	}

	public void setTechIcon(String icon) {
		this.techIcon = icon;
	}
//	
//	public int getAllType() {
//		return allType;
//	}
//
//	public void setAllType(int allType) {
//		this.allType = allType;
//	}
//
//	public int getType() {
//		return type;
//	}
//
//	public void setType(int type) {
//		this.type = type;
//	}
//	
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	

	public int getCostMoney() {
		return costMoney;
	}
	public void setCostMoney(int costMoney) {
		this.costMoney = costMoney;
	}
	
}
