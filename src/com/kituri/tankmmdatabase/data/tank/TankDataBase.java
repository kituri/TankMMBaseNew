package com.kituri.tankmmdatabase.data.tank;

import java.util.ArrayList;
import java.util.List;

import com.kituri.app.data.Entry;

public class TankDataBase extends Entry{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9096366248299455791L;
	
	public static final int _ALL = TankSearchData.TANK_ALL;
	/**
	*  坦克类型，轻中重突轻自。
	**/
	public static final int TYPE = TankSearchData.TANK_TYPE;
	public static final int CLASS = TankSearchData.TANK_CLASS;
	public static final int NAME = TankSearchData.TANK_NAME;
	public static final int NATIONALITY = TankSearchData.TANK_NATIONALITY;
	public static final int STAR = TankSearchData.TANK_STAR;
	public static final int FIRE = TankSearchData.TANK_FIRE;
	public static final int ARMOUR_PIERCING = TankSearchData.TANK_ARMOUR_PIERCING;
	public static final int HIT = TankSearchData.TANK_HIT;
	public static final int DURABLE = TankSearchData.TANK_DURABLE;
	public static final int ARMOUR = TankSearchData.TANK_ARMOUR;
	public static final int DODGE = TankSearchData.TANK_DODGE;
	public static final int HIDE = TankSearchData.TANK_HIDE;
	public static final int SPOT = TankSearchData.TANK_SPOT;
	public static final int EQUIPMENT_SLOTS = TankSearchData.TANK_EQUIPMENT_SLOTS;
	
//	public static final int CONDITIONAL_QUERY[] = {TANK_ALL, TANK_TYPE, TANK_CLASS, TANK_NAME, TANK_NATIONALITY,
//		TANK_STAR, TANK_FIRE, TANK_ARMOUR_PIERCING, TANK_HIT, TANK_DURABLE, TANK_ARMOUR, TANK_DODGE, TANK_HIDE,
//		TANK_SPOT, TANK_EQUIPMENT_SLOTS};
	
//	public static final int CONDITIONAL_QUERY[] = TankSearchData.CONDITIONAL_QUERY;
	
	public interface TANK_DEFAULT_ALL{
		public static final int ALL = -1;
		public static final String ALL_STRING = "全部";
		public static final String ALL_ICON = "icon_empty";
	}
	
	public interface TANK_ALL{
		//坦克国籍："英国", "美国", "德国", "苏联", "法国", "中国"

		public static final int TYPE = TankSearchData.TANK_TYPE;
		public static final int CLASS = TankSearchData.TANK_CLASS;
		public static final int NAME = TankSearchData.TANK_NAME;
		public static final int NATIONALITY = TankSearchData.TANK_NATIONALITY;
		public static final int STAR = TankSearchData.TANK_STAR;
		public static final int FIRE = TankSearchData.TANK_FIRE;
		public static final int ARMOUR_PIERCING = TankSearchData.TANK_ARMOUR_PIERCING;
		public static final int HIT = TankSearchData.TANK_HIT;
		public static final int DURABLE = TankSearchData.TANK_DURABLE;
		public static final int ARMOUR = TankSearchData.TANK_ARMOUR;
		public static final int DODGE = TankSearchData.TANK_DODGE;
		public static final int HIDE = TankSearchData.TANK_HIDE;
		public static final int SPOT = TankSearchData.TANK_SPOT;
		public static final int EQUIPMENT_SLOTS = TankSearchData.TANK_EQUIPMENT_SLOTS;
		
		public static final int ALL[] = {CLASS, NAME, NATIONALITY,
			STAR, FIRE, ARMOUR_PIERCING, HIT, DURABLE, ARMOUR, DODGE, HIDE,
			SPOT, EQUIPMENT_SLOTS};
		public static final String ALL_STRING[] = {"型号", "名字", "国籍", "星级", "火力", "穿甲", "命中", "耐力",
			"装甲", "闪避", "隐藏", "侦查", "装备槽"};
		public static final String ALL_ICONS[] = {"icon_empty", "icon_empty", "icon_empty", "icon_empty",
			"icon_empty", "icon_empty", "icon_empty", "icon_empty","icon_empty", "icon_empty",
			"icon_empty", "icon_empty", "icon_empty"};
	}
	

	public interface TANK_NATIONAL{
		//坦克国籍："英国", "美国", "德国", "苏联", "法国", "中国"

		public static final int UK = 0; 
		public static final int AM = 1; 
		public static final int DE = 2; 
		public static final int SU = 3; 
		public static final int FR = 4; 
		public static final int CH = 5;
		
		public static final int ALL[] = {UK, AM, DE, SU, FR, CH};
		public static final String ALL_STRING[] = {"英国", "美国", "德国", "苏联", "法国", "中国"};
		public static final String ALL_ICONS[] = {"icon_tank_national_flag_01",
			"icon_tank_national_flag_02", "icon_tank_national_flag_03", "icon_tank_national_flag_04",
			"icon_tank_national_flag_05", "icon_tank_national_flag_06"};
	}
	
	
	public interface TANK_TYPE{
		//坦克类型：重坦、中坦、轻坦、自行、轻歼、突击
		public static final int HT = 0; 
		public static final int MT = 1; 
		public static final int LT = 2; 
		public static final int SPG = 3; 	 
		public static final int LTD = 4;
		public static final int TD = 5;
		
		public static final int ALL[] = {HT, MT, LT, SPG, LTD, TD};
		public static final String ALL_STRING[] = {"重坦", "中坦", "轻坦", "自行", "轻歼", "突击"};
		public static final String ALL_ICONS[] = {"icon_tank_type_ht", "icon_tank_type_mt",
			"icon_tank_type_lt", "icon_tank_type_spg", "icon_tank_type_ltd", "icon_tank_type_td"};
	
	}
	
	private List<String> ALL_ICONS = new ArrayList<String>();
	private List<String> ALL_STRING = new ArrayList<String>();
	private List<Integer> ALL = new ArrayList<Integer>();
	
	public List<String> getALL_STRING() {
		return ALL_STRING;
	}

	public List<String> getALL_ICONS() {
		return ALL_ICONS;
	}

	public List<Integer> getALL() {
		return ALL;
	}

	/**
	 * 必须需要查询条件来生成
	 **/
	public TankDataBase(int conditionalQuery){
		ALL.clear();
		ALL_ICONS.clear();
		ALL_STRING.clear();
		switch (conditionalQuery) {
		case TYPE:
			for(int typeAll : TANK_TYPE.ALL){
				ALL.add(typeAll);
			}
			for(String typeAll : TANK_TYPE.ALL_ICONS){
				ALL_ICONS.add(typeAll);
			}
			for(String typeAll : TANK_TYPE.ALL_STRING){
				ALL_STRING.add(typeAll);
			}
			break;

		case NATIONALITY:
			for(int typeAll : TANK_NATIONAL.ALL){
				ALL.add(typeAll);
			}
			for(String typeAll : TANK_NATIONAL.ALL_ICONS){
				ALL_ICONS.add(typeAll);
			}
			for(String typeAll : TANK_NATIONAL.ALL_STRING){
				ALL_STRING.add(typeAll);
			}
			break;
		case _ALL:
			for(int all : TANK_ALL.ALL){
				ALL.add(all);
			}
			for(String all : TANK_ALL.ALL_ICONS){
				ALL_ICONS.add(all);
			}
			for(String all : TANK_ALL.ALL_STRING){
				ALL_STRING.add(all);
			}
			break;
		}
	}
	

	

}
