package com.kituri.tankmmdatabase.data.equip;

import java.util.List;

import com.kituri.app.data.Entry;
import com.kituri.tankmmdatabase.data.tank.TankStatisticData;

/**
 * 装备数据
 */
public class EquipData extends Entry{

	private static final long serialVersionUID = 3672726097211956879L;
	
	/**
	 * 炮座
	 */
	public static final int TYPE_GUNREST = 0;
	/**
	 * 改装
	 */
	public static final int TYPE_REFIT = 1;
	/**
	 * 外身
	 */
	public static final int TYPE_OUTSIDE_BODY = 2;
	/**
	 * 内舱
	 */
	public static final int TYPE_INTERNAL_CABINS = 3;
	/**
	 * 内壁
	 */
	public static final int TYPE_INNER_WALL = 4;
	/**
	 * 炮架
	 */
	public static final int TYPE_GUN_CARRIAGE = 5;
	/**
	 * 特殊
	 */
	public static final int TYPE_SPECIAL = 6;
	
	
	//装备名字
	private String equipName;

	//插槽位置
	private int equipType;
	
	//增加的属性 和 其值
	private List<TankStatisticData> statisticDatas;
	
	//特殊作用
	private List<EquipSpecialData> specialDatas;

	//掉落的地点（含军港等）
	private List<EquipDropData> dropDatas;
	
//	//icon
//	private int iconResId;
//	
//	public int getIconResId() {
//		return iconResId;
//	}
//	public void setIconResId(int iconResId) {
//		this.iconResId = iconResId;
//	}
	public int getEquipType() {
		return equipType;
	}
	public void setEquipType(int equipType) {
		this.equipType = equipType;
	}
	public List<TankStatisticData> getStatisticDatas() {
		return statisticDatas;
	}
	public void setStatisticDatas(List<TankStatisticData> statisticDatas) {
		this.statisticDatas = statisticDatas;
	}
	public List<EquipSpecialData> getSpecialDatas() {
		return specialDatas;
	}
	public void setSpecialDatas(List<EquipSpecialData> specialDatas) {
		this.specialDatas = specialDatas;
	}
	public List<EquipDropData> getDropDatas() {
		return dropDatas;
	}
	public void setDropDatas(List<EquipDropData> dropDatas) {
		this.dropDatas = dropDatas;
	}
	public String getEquipName() {
		return equipName;
	}
	public void setEquipName(String equipName) {
		this.equipName = equipName;
	}
}
