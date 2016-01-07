package com.kituri.tankmmdatabase.data.tech;

import java.util.ArrayList;
import java.util.List;

import com.kituri.app.data.Entry;
import com.kituri.tankmmdatabase.KituriTankMMApplication;
import com.kituri.tankmmdatabase.R;

public class TechDataBase extends Entry {

	/**
	 * 科技数据库
	 */
	private static final long serialVersionUID = 9096366248299455791L;

	public interface TYPE {
		// 科技类型："主炮", "防护", "车体", "引擎", "索敌"

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

		public static final int ALL[] = { TYPE_MAIN_GUN, TYPE_SHIELD, TYPE_BODYWORK,
			TYPE_ENGINE, TYPE_SEEK };
		public static final String ALL_STRING[] = KituriTankMMApplication.getInstance().
				getResources().getStringArray(R.array.tech_manager_type_all_string);
		public static final String ALL_ICONS[] = { "icon_empty",
				"icon_empty", "icon_empty", "icon_empty",
				"icon_empty"};
	}

	public interface TECH_MAIN_GUN{
		//主炮："标准弹", "硬芯弹", "脱壳弹", "榴弹", "火箭弹", "破甲弹", "碎甲弹"

		public static final int AP = 0; 
		public static final int APCR = 1; 
		public static final int APDS = 2; 
		public static final int HE = 3; 
		public static final int RP = 4; 
		public static final int HEAT = 5;
		public static final int HESH = 6;
		
		public static final int ALL[] = {AP, APCR, APDS, HE, RP, HEAT, HESH};
		public static final String ALL_STRING[] = KituriTankMMApplication.getInstance().
				getResources().getStringArray(R.array.tech_manager_main_gun_all_string);
		public static final String ALL_ICONS[] = {"icon_tech_maingun_type_01",
			"icon_tech_maingun_type_02", "icon_tech_maingun_type_03", "icon_tech_maingun_type_04",
			"icon_tech_maingun_type_05", "icon_tech_maingun_type_06", "icon_tech_maingun_type_07"};
//		public static final String ALL_STRING_SUB[] = {"标准弹", "硬芯弹", "脱壳弹", "榴弹",
//				"火箭弹", "破甲弹", "碎甲弹"};
	}
	
	public interface TECH_SHIELD{
		//防护："标准防护", "重装防护", "轻薄防护"

		public static final int MEDIUM = 0; 
		public static final int HEAVY = 1; 
		public static final int LIGHT = 2; 
		
		public static final int ALL[] = {MEDIUM, HEAVY, LIGHT};
		public static final String ALL_STRING[] = KituriTankMMApplication.getInstance().
				getResources().getStringArray(R.array.tech_manager_shield_all_string);
		public static final String ALL_ICONS[] = {"icon_tech_shield_type_01",
			"icon_tech_shield_type_02", "icon_tech_shield_type_03"};
	}
	
	public interface TECH_BODYWORK{
		//车身："综合车身"

		public static final int COMPREHENSIVE_BODYWORK = 0; 
		
		public static final int ALL[] = {COMPREHENSIVE_BODYWORK};
		public static final String ALL_STRING[] =  KituriTankMMApplication.getInstance().
				getResources().getStringArray(R.array.tech_manager_bodywork_all_string);
		public static final String ALL_ICONS[] = {"icon_tech_bodywork_type_01"};
	}

	public interface TECH_ENGINE{
		//引擎："综合引擎"

		public static final int COMPREHENSIVE_ENGINE = 0; 
		
		public static final int ALL[] = {COMPREHENSIVE_ENGINE};
		public static final String ALL_STRING[] = KituriTankMMApplication.getInstance().
				getResources().getStringArray(R.array.tech_manager_engine_all_string);
		public static final String ALL_ICONS[] = {"icon_tech_engine_type_01"};
	}
	
	public interface TECH_SEEK{
		//索敌："综合索敌"

		public static final int COMPREHENSIVE_SEEK = 0; 
		
		public static final int ALL[] = {COMPREHENSIVE_SEEK};
		public static final String ALL_STRING[] = KituriTankMMApplication.getInstance().
				getResources().getStringArray(R.array.tech_manager_seek_all_string);
		public static final String ALL_ICONS[] = {"icon_empty"};
	}
	
	public interface TECH_TYPE_MAINGUN_AP{
		//里面直接写id
		public static final int[][] RANK_LV_01 = {
			{1101,    0,    0,    0,    0, 1111},
			{   0,    0, 1106, 1108,    0,    0},
			{1102,    0, 1107,    0, 1112,    0},
			{   0, 1104,    0, 1109,    0,    0},
			{1103, 1105,    0, 1110, 1113,    0},
		};
		
		public static final int[][] RANK_LV_02 = {
			{ 1201,     0,     0,     0,     0,  1211},
			{    0,     0,  1206,  1209,     0,     0},
			{ 1202,     0,  1207,     0,  1212,     0},
			{    0,  1204,     0,  1210,     0,     0},
			{ 1203,  1205,  1208,     0,  1213,     0},
		};
		
		public static final int[][] RANK_LV_03 = {
			{    0,  1303,  1306,     0,     0,  1309},
			{    0,     0,     0,  1307,     0,     0},
			{ 1301,  1304,     0,  1308,  1310,     0},
			{    0,     0,     0,     0,     0,     0},
			{ 1302,  1305,     0,     0,     0,  1311},
		};
	}
	
	public interface TECH_TYPE_MAINGUN_APCR{
		//里面直接写id
		public static final int[][] RANK_LV_01 = {
			{ 2101,  2104,     0,  2108,     0,  2113},
			{    0,  2105,     0,     0,     0,     0},
			{ 2102,     0,     0,  2109,  2111,  2114},
			{    0,     0,     0,  2110,     0,     0},
			{ 2103,  2106,  2107,     0,  2112,  2115},
		};
		
		public static final int[][] RANK_LV_02 = {
			{ 2201,     0,  2206,     0,     0,  2210},
			{    0,     0,     0,     0,     0,     0},
			{ 2202,  2204,  2207,     0,     0,     0},
			{    0,  2205,  2208,     0,  2211,     0},
			{ 2203,     0,     0,  2209,  2212,     0},
		};
		
		public static final int[][] RANK_LV_03 = {
			{    0,  2303,  2304,     0,     0,  2308},
			{    0,     0,     0,     0,     0,     0},
			{ 2301,     0,  2305,     0,  2309,     0},
			{    0,     0,  2306,     0,     0,     0},
			{ 2302,     0,     0,  2307,  2310,     0},
		};
	}
	
	public interface TECH_TYPE_MAINGUN_APDS{
		//里面直接写id
		public static final int[][] RANK_LV_01 = {
			{ 3101,  3102,     0,  3106,     0,  3111},
			{    0,     0,     0,     0,     0,    0},
			{    0,  3103,     0,  3107,  3109,  3112},
			{    0,     0,     0,  3108,     0,    0},
			{    0,  3104,  3105,     0,  3110,  3113},
		};
		
		public static final int[][] RANK_LV_02 = {
			{ 3201,  3202,     0,     0,     0,  3211},
			{    0,     0,     0,  3206,     0,     0},
			{    0,  3203,     0,  3207,  3209,  3212},
			{    0,     0,     0,  3208,     0,     0},
			{    0,  3204,  3205,     0,  3210,  3213},
		};
		
		public static final int[][] RANK_LV_03 = {
			{    0,  3302,  3305,     0,     0,  3311},
			{    0,     0,     0,  3306,     0,     0},
			{    0,  3303,     0,  3307,  3309,  3312},
			{    0,     0,     0,  3308,     0,     0},
			{ 3301,  3304,     0,     0,  3310,  3313},
		};
	}

	public interface TECH_TYPE_MAINGUN_HE{
		//里面直接写id
		public static final int[][] RANK_LV_01 = {
			{ 4101,  4104,     0,     0,  4111,  4114},
			{    0,  4105,     0,  4108,     0,     0},
			{ 4102,     0,     0,  4109,  4112,  4115},
			{    0,     0,  4106,  4110,  4113,     0},
			{ 4103,     0,  4107,     0,     0,  4116},
		};
		
		public static final int[][] RANK_LV_02 = {
			{ 4201,     0,     0,     0,  4211,  4214},
			{    0,  4204,     0,  4208,     0,     0},
			{ 4202,     0,  4206,  4209,  4212,  4215},
			{    0,  4205,     0,  4210,     0,     0},
			{ 4203,     0,  4207,     0,  4213,  4216},
		};
		
		public static final int[][] RANK_LV_03 = {
			{ 4301,     0,  4306,     0,     0,  4314},
			{    0,  4304,     0,  4309,     0,     0},
			{ 4302,  4305,     0,  4310,  4312,  4315},
			{    0,     0,  4307,  4311,     0,     0},
			{ 4303,     0,  4308,     0,  4313,  4316},
		};
	}

	public interface TECH_TYPE_MAINGUN_RP{
		//里面直接写id
		public static final int[][] RANK_LV_01 = {
			{    0,  5103,     0,  5106,  5107,     0},
			{ 5101,     0,     0,     0,     0,     0},
			{    0,  5104,     0,     0,     0,     0},
			{ 5102,     0,  5105,     0,     0,  5108},
			{    0,     0,     0,     0,     0,     0},
		};

		public static final int[][] RANK_LV_02 = {
			{    0,     0,     0,  5205,  5207,     0},
			{ 5201,  5202,     0,     0,     0,  5208},
			{    0,     0,     0,  5206,     0,     0},
			{    0,  5203,  5204,     0,     0,  5209},
			{    0,     0,     0,     0,     0,     0},
		};

		public static final int[][] RANK_LV_03 = {
			{    0,     0,     0,     0,     0,     0},
			{ 5301,  5302,     0,     0,  5307,  5308},
			{    0,     0,     0,  5305,     0,     0},
			{    0,  5303,  5304,     0,     0,     0},
			{    0,     0,     0,  5306,     0,  5309},
		};
	}

	public interface TECH_TYPE_MAINGUN_HEAT{
		//里面直接写id
		public static final int[][] RANK_LV_01 = {
			{ 6101,     0,  6106,     0,     0,  6113},
			{    0,     0,     0,  6108,     0,     0},
			{ 6102,  6104,  6107,  6109,  6111,  6114},
			{    0,  6105,     0,     0,     0,     0},
			{ 6103,     0,     0,  6110,  6112,  6115},
		};

		public static final int[][] RANK_LV_02 = {
			{ 6201,     0,     0,     0,  6211,  6213},
			{    0,     0,  6206,     0,     0,     0},
			{ 6202,  6204,  6207,  6209,  6212,  6214},
			{    0,  6205,     0,     0,     0,     0},
			{ 6203,     0,  6208,  6210,     0,  6215},
		};

		public static final int[][] RANK_LV_03 = {
			{ 6301,     0,     0,  6309,     0,  6313},
			{    0,     0,  6306,     0,     0,    0},
			{ 6302,  6304,  6307,  6310,  6311,  6314},
			{    0,  6305,     0,     0,     0,    0},
			{ 6303,     0,  6308,     0,  6312,  6315},
		};
	}
	
	public interface TECH_TYPE_MAINGUN_HESH{
		//里面直接写id
		public static final int[][] RANK_LV_01 = {
			{    0,     0,     0,     0,     0,     0},
			{ 7101,     0,     0,  7105,  7106,     0},
			{    0,  7103,     0,     0,     0,     0},
			{ 7102,     0,     0,     0,     0,  7107},
			{    0,     0,  7104,     0,     0,     0},
		};

		public static final int[][] RANK_LV_02 = {
			{    0,     0,     0,     0,     0,     0},
			{ 7201,     0,     0,  7205,     0,  7207},
			{    0,  7203,     0,     0,     0,     0},
			{ 7202,     0,     0,     0,  7206,     0},
			{    0,     0,  7204,     0,     0,     0},
		};

		public static final int[][] RANK_LV_03 = {
			{    0,     0,  7304,     0,     0,     0},
			{ 7301,  7303,     0,     0,  7307,  7309},
			{    0,     0,  7305,     0,     0,     0},
			{ 7302,     0,     0,  7306,  7308,  7310},
			{    0,     0,     0,     0,     0,     0},
		};

	}
	
	public interface TECH_TYPE_SHIELD_MEDIUM{
		//里面直接写id
		public static final int[][] RANK_LV_01 = {
			{   0,    0,    0,    0,  508,  510},
			{   0,  502,  505,  506,    0,    0},
			{ 501,    0,    0,    0,  509,  511},
			{   0,  503,  504,  507,    0,  512},
			{   0,    0,    0,    0,    0,    0},
		};

		public static final int[][] RANK_LV_02 = {
			{   0,    0,    0,    0,    0,    0},
			{   0,  514,    0,  518,  521,  522},
			{ 513,    0,    0,  519,    0,    0},
			{   0,  515,  516,    0,  520,  523},
			{   0,    0,    0,  517,    0,  524},
		};

		public static final int[][] RANK_LV_03 = {
			{   0,    0,    0,    0,  531,  534},
			{   0,  526,    0,  529,  532,    0},
			{ 525,    0,  528,    0,    0,    0},
			{   0,    0,  527,  530,    0,  535},
			{   0,    0,    0,    0,  533,  536},
		};

	}
	
	public interface TECH_TYPE_SHIELD_HEAVY{
		//里面直接写id
		public static final int[][] RANK_LV_01 = {
			{   0,    0,    0,    0,  408,  410},
			{   0,  402,  405,  406,    0,    0},
			{ 401,    0,    0,    0,  409,    0},
			{   0,  403,  404,  407,    0,  411},
			{   0,    0,    0,    0,    0,    0},
		};

		public static final int[][] RANK_LV_02 = {
			{   0,    0,    0,    0,    0,    0},
			{   0,  413,    0,  417,  419,  421},
			{ 412,    0,    0,  418,    0,    0},
			{   0,  414,  415,  416,  420,  422},
			{   0,    0,    0,    0,    0,    0},
		};

		public static final int[][] RANK_LV_03 = {
			{   0,    0,    0,  427,  429,  431},
			{   0,  424,    0,    0,    0,    0},
			{ 423,    0,  426,    0,    0,  432},
			{   0,    0,  425,  428,    0,    0},
			{   0,    0,    0,    0,  430,  433},
		};

	}
	
	public interface TECH_TYPE_SHIELD_LIGHT{
		//里面直接写id
		public static final int[][] RANK_LV_01 = {
			{   0,    0,    0,    0,    0,    0},
			{   0,  602,  605,  606,  608,  609},
			{ 601,    0,    0,    0,    0,    0},
			{   0,  603,  604,  607,    0,  610},
			{   0,    0,    0,    0,    0,    0},
		};

		public static final int[][] RANK_LV_02 = {
			{   0,    0,    0,    0,  617,  619},
			{   0,  612,    0,  616,    0,    0},
			{ 611,    0,    0,  615,    0,    0},
			{   0,  613,  614,    0,    0,    0},
			{   0,    0,    0,    0,  618,  620},
		};

		public static final int[][] RANK_LV_03 = {
			{   0,    0,    0,    0,    0,  628},
			{   0,  622,    0,    0,  626,    0},
			{ 621,    0,  624,  625,    0,  630},
			{   0,    0,  623,    0,    0,    0},
			{   0,    0,    0,    0,  627,  629},
		};

	}

	public interface TECH_TYPE_BODYWORK_COMPREHENSIVE_BODYWORK{
		//里面直接写id
		public static final int[][] RANK_LV_01 = {
			{   0,  203,    0,    0,    0,    0},
			{ 201,    0,    0,    0,    0,  209},
			{   0,  204,  205,  207,  208,    0},
			{ 202,    0,    0,    0,    0,  210},
			{   0,    0,  206,    0,    0,    0},
		};

		public static final int[][] RANK_LV_02 = {
			{   0,    0,  215,    0,    0,    0},
			{ 211,  213,  216,    0,    0,    0},
			{   0,  214,    0,  217,    0,  220},
			{ 212,    0,    0,  218,  219,    0},
			{   0,    0,    0,    0,    0,    0},
		};

		public static final int[][] RANK_LV_03 = {
			{ 221,    0,    0,    0,  229,    0},
			{   0,    0,  225,  227,    0,    0},
			{   0,  223,    0,    0,    0,  231},
			{ 222,    0,  226,    0,  230,    0},
			{   0,  224,    0,  228,    0,    0},
		};


	}
	
//引擎编号 101起
	public interface TECH_TYPE_ENGINE_COMPREHENSIVE_ENGINE{
		//里面直接写id
		public static final int[][] RANK_LV_01 = {
			{ 101,  103,  105,  108,    0,    0},
			{   0,    0,  106,    0,  111,  113},
			{ 102,  104,    0,  109,    0,    0},
			{   0,    0,  107,  110,  112,  114},
			{   0,    0,    0,    0,    0,    0},
		};

		public static final int[][] RANK_LV_02 = {
			{   0,    0,    0,    0,    0,    0},
			{   0,    0,    0,  121,    0,  126},
			{ 115,  117,  119,  122,  124,    0},
			{   0,    0,    0,  123,    0,  127},
			{ 116,  118,  120,    0,  125,  128},
		};

		public static final int[][] RANK_LV_03 = {
			{   0,    0,    0,    0,  137,  142},
			{ 129,  131,  133,    0,  138,    0},
			{   0,    0,    0,  135,    0,  140},
			{ 130,  132,  134,  136,  139,    0},
			{   0,    0,    0,    0,    0,  141},
		};


	}
	
	public TechDataBase(TechSearchData data){
		ALL.clear();
		ALL_ICONS.clear();
		ALL_STRING.clear();
		switch (data.getType()) {
		case TYPE.TYPE_MAIN_GUN:
			for(int typeAll : TECH_MAIN_GUN.ALL){
				ALL.add(typeAll);
			}
			for(String typeAll : TECH_MAIN_GUN.ALL_ICONS){
				ALL_ICONS.add(typeAll);
			}
			for(String typeAll : TECH_MAIN_GUN.ALL_STRING){
				ALL_STRING.add(typeAll);
			}
			break;

		case TYPE.TYPE_SHIELD:
			for(int typeAll : TECH_SHIELD.ALL){
				ALL.add(typeAll);
			}
			for(String typeAll : TECH_SHIELD.ALL_ICONS){
				ALL_ICONS.add(typeAll);
			}
			for(String typeAll : TECH_SHIELD.ALL_STRING){
				ALL_STRING.add(typeAll);
			}
			break;
			
		case TYPE.TYPE_BODYWORK:
			for(int typeAll : TECH_BODYWORK.ALL){
				ALL.add(typeAll);
			}
			for(String typeAll : TECH_BODYWORK.ALL_ICONS){
				ALL_ICONS.add(typeAll);
			}
			for(String typeAll : TECH_BODYWORK.ALL_STRING){
				ALL_STRING.add(typeAll);
			}
			break;
			
		case TYPE.TYPE_ENGINE:
			for(int typeAll : TECH_ENGINE.ALL){
				ALL.add(typeAll);
			}
			for(String typeAll : TECH_ENGINE.ALL_ICONS){
				ALL_ICONS.add(typeAll);
			}
			for(String typeAll : TECH_ENGINE.ALL_STRING){
				ALL_STRING.add(typeAll);
			}
			break;
			
		case TYPE.TYPE_SEEK:
			for(int typeAll : TECH_SEEK.ALL){
				ALL.add(typeAll);
			}
			for(String typeAll : TECH_SEEK.ALL_ICONS){
				ALL_ICONS.add(typeAll);
			}
			for(String typeAll : TECH_SEEK.ALL_STRING){
				ALL_STRING.add(typeAll);
			}
			break;
		}
	}
	
	public TechDataBase(TechTypeData data){
		ALL.clear();
		ALL_ICONS.clear();
		ALL_STRING.clear();
		switch (data.getAllType()) {
		case TYPE.TYPE_MAIN_GUN:
			initMainGun(data);
			break;
			
		case TYPE.TYPE_SHIELD:
			intiShield(data);
			break;
			
		case TYPE.TYPE_BODYWORK:
			intiBodyWork(data);
			break;
			
		case TYPE.TYPE_ENGINE:
			intiEngine(data);
			break;
			
		case TYPE.TYPE_SEEK:
			
			break;
		}
	}
	
	private void initMainGun(TechTypeData data){
		switch (data.getType()) {
		case TECH_MAIN_GUN.AP:
			RANKS.add(TECH_TYPE_MAINGUN_AP.RANK_LV_01);
			RANKS.add(TECH_TYPE_MAINGUN_AP.RANK_LV_02);
			RANKS.add(TECH_TYPE_MAINGUN_AP.RANK_LV_03);
			break;
		case TECH_MAIN_GUN.APCR:
			RANKS.add(TECH_TYPE_MAINGUN_APCR.RANK_LV_01);
			RANKS.add(TECH_TYPE_MAINGUN_APCR.RANK_LV_02);
			RANKS.add(TECH_TYPE_MAINGUN_APCR.RANK_LV_03);
			break;
		case TECH_MAIN_GUN.APDS:
			RANKS.add(TECH_TYPE_MAINGUN_APDS.RANK_LV_01);
			RANKS.add(TECH_TYPE_MAINGUN_APDS.RANK_LV_02);
			RANKS.add(TECH_TYPE_MAINGUN_APDS.RANK_LV_03);
			break;
		case TECH_MAIN_GUN.HE:
			RANKS.add(TECH_TYPE_MAINGUN_HE.RANK_LV_01);
			RANKS.add(TECH_TYPE_MAINGUN_HE.RANK_LV_02);
			RANKS.add(TECH_TYPE_MAINGUN_HE.RANK_LV_03);
			break;
		case TECH_MAIN_GUN.RP:
			RANKS.add(TECH_TYPE_MAINGUN_RP.RANK_LV_01);
			RANKS.add(TECH_TYPE_MAINGUN_RP.RANK_LV_02);
			RANKS.add(TECH_TYPE_MAINGUN_RP.RANK_LV_03);
			break;
		case TECH_MAIN_GUN.HEAT:
			RANKS.add(TECH_TYPE_MAINGUN_HEAT.RANK_LV_01);
			RANKS.add(TECH_TYPE_MAINGUN_HEAT.RANK_LV_02);
			RANKS.add(TECH_TYPE_MAINGUN_HEAT.RANK_LV_03);
			break;
		case TECH_MAIN_GUN.HESH:
			RANKS.add(TECH_TYPE_MAINGUN_HESH.RANK_LV_01);
			RANKS.add(TECH_TYPE_MAINGUN_HESH.RANK_LV_02);
			RANKS.add(TECH_TYPE_MAINGUN_HESH.RANK_LV_03);
			break;
		}
	}
	
	private void intiShield(TechTypeData data){
		switch (data.getType()) {
		case TECH_SHIELD.MEDIUM:
			RANKS.add(TECH_TYPE_SHIELD_MEDIUM.RANK_LV_01);
			RANKS.add(TECH_TYPE_SHIELD_MEDIUM.RANK_LV_02);
			RANKS.add(TECH_TYPE_SHIELD_MEDIUM.RANK_LV_03);
			break;
		case TECH_SHIELD.HEAVY:
			RANKS.add(TECH_TYPE_SHIELD_HEAVY.RANK_LV_01);
			RANKS.add(TECH_TYPE_SHIELD_HEAVY.RANK_LV_02);
			RANKS.add(TECH_TYPE_SHIELD_HEAVY.RANK_LV_03);
			break;
		case TECH_SHIELD.LIGHT:
			RANKS.add(TECH_TYPE_SHIELD_LIGHT.RANK_LV_01);
			RANKS.add(TECH_TYPE_SHIELD_LIGHT.RANK_LV_02);
			RANKS.add(TECH_TYPE_SHIELD_LIGHT.RANK_LV_03);
			break;
		}
	}
	
	private void intiBodyWork(TechTypeData data){
		switch (data.getType()) {
		case TECH_BODYWORK.COMPREHENSIVE_BODYWORK:
			RANKS.add(TECH_TYPE_BODYWORK_COMPREHENSIVE_BODYWORK.RANK_LV_01);
			RANKS.add(TECH_TYPE_BODYWORK_COMPREHENSIVE_BODYWORK.RANK_LV_02);
			RANKS.add(TECH_TYPE_BODYWORK_COMPREHENSIVE_BODYWORK.RANK_LV_03);
			break;
		}
	}
	
	private void intiEngine(TechTypeData data){
		switch (data.getType()) {
		case TECH_ENGINE.COMPREHENSIVE_ENGINE:
			RANKS.add(TECH_TYPE_ENGINE_COMPREHENSIVE_ENGINE.RANK_LV_01);
			RANKS.add(TECH_TYPE_ENGINE_COMPREHENSIVE_ENGINE.RANK_LV_02);
			RANKS.add(TECH_TYPE_ENGINE_COMPREHENSIVE_ENGINE.RANK_LV_03);
			break;
		}
	}
	
	
	
	private List<String> ALL_ICONS = new ArrayList<String>();
	private List<String> ALL_STRING = new ArrayList<String>();
	private List<Integer> ALL = new ArrayList<Integer>();
	private List<int[][]> RANKS = new ArrayList<int[][]>();
	
	public List<int[][]> getRANKS() {
		return RANKS;
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

}
