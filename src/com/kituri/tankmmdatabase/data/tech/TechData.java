package com.kituri.tankmmdatabase.data.tech;

import java.util.List;

import com.kituri.app.data.Entry;
import com.kituri.tankmmdatabase.data.tank.TankStatisticData;

/**
 * 科技数据
 */
public class TechData extends Entry{

	private static final long serialVersionUID = 3672726097211956879L;
	
	/**
	 * 主炮
	 */
	public static final int TYPE_MAIN_GUN = 0;
	/**
	 * 防护
	 */
	public static final int TYPE_SHIELD = 1;
	/**
	 * 车体
	 */
	public static final int TYPE_BODYWORK = 2;
	/**
	 * 引擎
	 */
	public static final int TYPE_ENGINE = 3;
	/**
	 * 索敌
	 */
	public static final int TYPE_SEEK = 4;

	//科技名字，例：被帽风帽偏重穿甲弹（中口径）
	private String techName;
	//allType，主炮、车身、引擎等
	private int allType;
	//type，AP、HE等
	private int type;
	private String icon;
	private int techId;

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
	
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	public int getAllType() {
		return allType;
	}

	public void setAllType(int allType) {
		this.allType = allType;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
