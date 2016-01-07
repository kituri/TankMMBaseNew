package com.kituri.tankmmdatabase.data.tank;

import java.util.ArrayList;
import java.util.List;

import com.kituri.app.data.Entry;
import com.kituri.tankmmdatabase.KituriTankMMApplication;
import com.kituri.tankmmdatabase.R;

public class TankDataBase extends Entry{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9096366248299455791L;
	
	public static final int _ALL = -1;
//	/**
//	*  坦克类型，轻中重突轻自。
//	**/
	public static final int _TANK_TYPE = 0;
	public static final int _TANK_NATIONALITY = 1;
	public static final int _TANK_STAR = 2;
	public static final int _TANK_STATISTIC = 3;
	public static final int _TANK_RES = 4;
	
	public interface TANK_DEFAULT_ALL{
		public static final int ALL = -1;
		public static final String ALL_STRING = KituriTankMMApplication.getInstance().
				getResources().getStringArray(R.array.tank_manager_default_all_string)[0];
		public static final String ALL_ICON = "icon_empty";
	}
	
//	public interface TANK_ALL{
//		//只供搜索种类使用
//		//坦克国籍："英国", "美国", "德国", "苏联", "法国", "中国"
//
//		public static final int TYPE = _TANK_TYPE;
////		public static final int CLASS = TANK_CLASS;
////		public static final int NAME = TANK_NAME;
//		public static final int NATIONALITY = _TANK_NATIONALITY;
//		public static final int STAR = _TANK_STAR;
//		public static final int FIRE = _TANK_FIRE;
//		public static final int ARMOUR_PIERCING = _TANK_ARMOUR_PIERCING;
//		public static final int HIT = _TANK_HIT;
//		public static final int DURABLE = _TANK_DURABLE;
//		public static final int ARMOUR = _TANK_ARMOUR;
//		public static final int DODGE = _TANK_DODGE;
//		public static final int HIDE = _TANK_HIDE;
//		public static final int SPOT = _TANK_SPOT;
//		//public static final int EQUIPMENT_SLOTS = TANK_EQUIPMENT_SLOTS;
//		
//		//搜索结果排序
//		public static final int ALL[] = {TYPE, NATIONALITY,
//			STAR, FIRE, ARMOUR_PIERCING, DURABLE, ARMOUR, DODGE, HIDE,
//			SPOT};
//		public static final String ALL_STRING[] = {"车型", "国籍", "星级", "火力", "穿甲", "耐久",
//			"装甲", "闪避", "隐藏", "侦查"};
//		public static final String ALL_ICONS[] = {"icon_empty", "icon_empty", "icon_empty",
//			"icon_empty", "icon_empty", "icon_empty", "icon_empty","icon_empty", "icon_empty",
//			"icon_empty"};
//	}
	public interface TANK_STATISTIC{
		public static final int FIRE = 0;
		public static final int ARMOUR_PIERCING = 1;
		public static final int HIT = 2;
		public static final int DURABLE = 3;
		public static final int ARMOUR = 4;
		public static final int DODGE = 5;
		public static final int HIDE = 6;
		public static final int SPOT = 7;
		
		public static final int ALL[] = {FIRE, ARMOUR_PIERCING, HIT, DURABLE, ARMOUR, DODGE, HIDE, SPOT};
		public static final String ALL_STRING[] = KituriTankMMApplication.getInstance().
				getResources().getStringArray(R.array.tank_manager_statistic_all_string);
		public static final String ALL_ICONS[] = {"icon_tank_statistics_fire",
			"icon_tank_statistics_armourpiercing", "icon_tank_statistics_hit", "icon_tank_statistics_durable", "icon_tank_statistics_armour",
			"icon_tank_statistics_dodge", "icon_tank_statistics_hide", "icon_tank_statistics_spot"};
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
		public static final String ALL_STRING[] = KituriTankMMApplication.getInstance().
				getResources().getStringArray(R.array.tank_manager_national_all_string);
		public static final String ALL_ICONS[] = {"icon_tank_national_flag_01",
			"icon_tank_national_flag_02", "icon_tank_national_flag_03", "icon_tank_national_flag_04",
			"icon_tank_national_flag_05", "icon_tank_national_flag_06"};
	}
	
	public interface TANK_RES{
		//坦克资源："残骸", "计划", "模块", "人偶"

		public static final int WRECKAGE = 0; 
		public static final int PLAN = 1; 
		public static final int MODULE = 2; 
		public static final int DOLL = 3;
		
		public static final int ALL[] = {WRECKAGE, PLAN, MODULE, DOLL};
		public static final String ALL_STRING[] = KituriTankMMApplication.getInstance().
				getResources().getStringArray(R.array.tank_manager_res_all_string);
		public static final String ALL_ICONS[] = {"icon_res_type01",
			"icon_res_type02", "icon_res_type03", "icon_res_type04"};
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
		public static final String ALL_STRING[] = KituriTankMMApplication.getInstance().
				getResources().getStringArray(R.array.tank_manager_type_all_string);
		public static final String ALL_ICONS[] = {"icon_tank_type_ht", "icon_tank_type_mt",
			"icon_tank_type_lt", "icon_tank_type_spg", "icon_tank_type_ltd", "icon_tank_type_td"};
	
	}
	
	public interface TANK_STAR{

		public static final int ONE = 1; 
		public static final int TWO = 2; 
		public static final int THREE= 3; 
		
		public static final int ALL[] = {ONE, TWO, THREE};
		public static final String ALL_STRING[] = KituriTankMMApplication.getInstance().
				getResources().getStringArray(R.array.tank_manager_star_all_string);
		public static final String ALL_ICONS[] = {"icon_rank_lv_01", "icon_rank_lv_02", "icon_rank_lv_03"};
	}
	
	private List<String> ALL_ICONS = new ArrayList<String>();
	private List<String> ALL_STRING = new ArrayList<String>();
	private List<Integer> ALL = new ArrayList<Integer>();
	private int type;
	
	public int getType() {
		return type;
	}

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
		this.type = conditionalQuery;
		ALL.clear();
		ALL_ICONS.clear();
		ALL_STRING.clear();
		switch (conditionalQuery) {
		case _TANK_TYPE:
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

		case _TANK_NATIONALITY:
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
		case _TANK_STATISTIC:
			for(int typeAll : TANK_STATISTIC.ALL){
				ALL.add(typeAll);
			}
			for(String typeAll : TANK_STATISTIC.ALL_ICONS){
				ALL_ICONS.add(typeAll);
			}
			for(String typeAll : TANK_STATISTIC.ALL_STRING){
				ALL_STRING.add(typeAll);
			}
			break;
		case _TANK_STAR:
			for(int typeAll : TANK_STAR.ALL){
				ALL.add(typeAll);
			}
			for(String typeAll : TANK_STAR.ALL_ICONS){
				ALL_ICONS.add(typeAll);
			}
			for(String typeAll : TANK_STAR.ALL_STRING){
				ALL_STRING.add(typeAll);
			}
			break;
		case _TANK_RES:
			for(int typeAll : TANK_RES.ALL){
				ALL.add(typeAll);
			}
			for(String typeAll : TANK_RES.ALL_ICONS){
				ALL_ICONS.add(typeAll);
			}
			for(String typeAll : TANK_RES.ALL_STRING){
				ALL_STRING.add(typeAll);
			}
			break;
//		case _ALL:
//		default:
//			for(int all : TANK_ALL.ALL){
//				ALL.add(all);
//			}
//			for(String all : TANK_ALL.ALL_ICONS){
//				ALL_ICONS.add(all);
//			}
//			for(String all : TANK_ALL.ALL_STRING){
//				ALL_STRING.add(all);
//			}
//			break;
			
		}
	}
	

	

}
