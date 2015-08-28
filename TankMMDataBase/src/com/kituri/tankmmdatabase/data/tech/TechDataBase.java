package com.kituri.tankmmdatabase.data.tech;

import java.util.ArrayList;
import java.util.List;

import com.kituri.app.data.Entry;

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
		public static final String ALL_STRING[] = { "主炮", "防护", "车体", "引擎", "索敌" };
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
		public static final String ALL_STRING[] = {"标准弹（AP）", "硬芯弹（APCR）", "脱壳弹（APDS）", "榴弹（HE）",
			"火箭弹（RP）", "破甲弹（HEAT）", "碎甲弹（HESH）"};
		public static final String ALL_ICONS[] = {"icon_tech_maingun_type_01",
			"icon_tech_maingun_type_02", "icon_tech_maingun_type_03", "icon_tech_maingun_type_04",
			"icon_tech_maingun_type_05", "icon_tech_maingun_type_06", "icon_tech_maingun_type_07"};
	}
	
	public interface TECH_SHIELD{
		//防护："标准防护", "重装防护", "轻薄防护"

		public static final int MEDIUM = 0; 
		public static final int HEAVY = 1; 
		public static final int LIGHT = 2; 
		
		public static final int ALL[] = {MEDIUM, HEAVY, LIGHT};
		public static final String ALL_STRING[] = {"标准防护", "重装防护", "轻薄防护"};
		public static final String ALL_ICONS[] = {"icon_tech_shield_type_01",
			"icon_tech_shield_type_02", "icon_tech_shield_type_03"};
	}
	
	public interface TECH_BODYWORK{
		//车身："综合车身"

		public static final int COMPREHENSIVE_BODYWORK = 0; 
		
		public static final int ALL[] = {COMPREHENSIVE_BODYWORK};
		public static final String ALL_STRING[] = {"综合车身"};
		public static final String ALL_ICONS[] = {"icon_tech_bodywork_type_01"};
	}

	public interface TECH_ENGINE{
		//引擎："综合引擎"

		public static final int COMPREHENSIVE_ENGINE = 0; 
		
		public static final int ALL[] = {COMPREHENSIVE_ENGINE};
		public static final String ALL_STRING[] = {"综合引擎"};
		public static final String ALL_ICONS[] = {"icon_tech_engine_type_01"};
	}
	
	public interface TECH_SEEK{
		//索敌："综合索敌"

		public static final int COMPREHENSIVE_SEEK = 0; 
		
		public static final int ALL[] = {COMPREHENSIVE_SEEK};
		public static final String ALL_STRING[] = {"综合索敌"};
		public static final String ALL_ICONS[] = {"icon_empty"};
	}
	
	public interface TECH_TYPE_MAINGUN_AP{
		//里面直接写id
		public static final int[][] RANK_LV_01 = {
			{111,   0,   0,   0,   0, 161},
			{  0,   0, 132, 142,   0,   0},
			{113,   0, 133,   0, 153,   0},
			{  0, 124,   0, 144,   0,   0},
			{115, 125,   0, 145, 155,   0},
		};
		
		public static final int[][] RANK_LV_02 = {
			{ 211,    0,    0,    0,    0,  261},
			{   0,    0,  232,  242,    0,    0},
			{ 213,    0,  233,    0,  253,    0},
			{   0,  224,    0,  244,    0,    0},
			{ 215,  225,  235,    0,  255,    0},
		};
		
		public static final int[][] RANK_LV_03 = {
			{   0,  321,  331,    0,    0,  361},
			{   0,    0,    0,  342,    0,    0},
			{ 313,  323,    0,  343,  353,    0},
			{   0,    0,    0,    0,    0,    0},
			{ 315,  325,    0,    0,    0,  365},
		};
	}
	
	public interface TECH_TYPE_MAINGUN_APCR{
		//里面直接写id
		public static final int[][] RANK_LV_01 = {
			{ 111,  121,    0,  141,    0,  161},
			{   0,  122,    0,    0,    0,    0},
			{ 113,    0,    0,  143,  153,  163},
			{   0,    0,    0,  144,    0,    0},
			{ 115,  125,  135,    0,  155,  165},
		};
		
		public static final int[][] RANK_LV_02 = {
			{ 211,    0,  231,    0,    0,  261},
			{   0,    0,    0,    0,    0,    0},
			{ 213,  223,  233,    0,    0,    0},
			{   0,  224,  234,    0,  254,    0},
			{ 215,    0,    0,  245,  255,    0},
		};
		
		public static final int[][] RANK_LV_03 = {
			{   0,  321,  331,    0,    0,  361},
			{   0,    0,    0,    0,    0,    0},
			{ 313,    0,  333,    0,  353,    0},
			{   0,    0,  334,    0,    0,    0},
			{ 315,    0,    0,  345,  355,    0},
		};
	}
	
	public interface TECH_TYPE_MAINGUN_APDS{
		//里面直接写id
		public static final int[][] RANK_LV_01 = {
			{ 111,  121,    0,  141,    0,  161},
			{   0,    0,    0,    0,    0,    0},
			{   0,  123,    0,  143,  153,  163},
			{   0,    0,    0,  144,    0,    0},
			{   0,  125,  135,    0,  155,  165},
		};
		
		public static final int[][] RANK_LV_02 = {
			{ 211,  221,    0,    0,    0,  261},
			{   0,    0,    0,  242,    0,    0},
			{   0,  223,    0,  243,  253,  263},
			{   0,    0,    0,  244,    0,    0},
			{   0,  225,  235,    0,  255,  265},
		};
		
		public static final int[][] RANK_LV_03 = {
			{   0,  321,  331,    0,    0,  361},
			{   0,    0,    0,  342,    0,    0},
			{   0,  323,    0,  343,  353,  363},
			{   0,    0,    0,  344,    0,    0},
			{ 315,  325,    0,    0,  355,  365},
		};
	}

	public interface TECH_TYPE_MAINGUN_HE{
		//里面直接写id
		public static final int[][] RANK_LV_01 = {
			{ 111,  121,    0,    0,  151,  161},
			{   0,  122,    0,  142,    0,    0},
			{ 113,    0,    0,  143,  153,  163},
			{   0,    0,  134,  144,  154,    0},
			{ 115,    0,  135,    0,    0,  165},
		};
		
		public static final int[][] RANK_LV_02 = {
			{ 211,    0,    0,    0,  251,  261},
			{   0,  222,    0,  242,    0,    0},
			{ 213,    0,  233,  243,  253,  263},
			{   0,  224,    0,  244,    0,    0},
			{ 215,    0,  235,    0,  255,  265},
		};
		
		public static final int[][] RANK_LV_03 = {
			{ 311,    0,  331,    0,    0,  361},
			{   0,  322,    0,  342,    0,    0},
			{ 313,  323,    0,  343,  353,  363},
			{   0,    0,  334,  344,    0,    0},
			{ 315,    0,  335,    0,  355,  365},
		};
	}

	public interface TECH_TYPE_MAINGUN_RP{
		//里面直接写id
		public static final int[][] RANK_LV_01 = {
			{   0,  121,    0,  141,  151,    0},
			{ 112,    0,    0,    0,    0,    0},
			{   0,  123,    0,    0,    0,    0},
			{ 114,    0,  134,    0,    0,  164},
			{   0,    0,    0,    0,    0,    0},
		};

		public static final int[][] RANK_LV_02 = {
			{   0,    0,    0,  241,  251,    0},
			{ 212,  222,    0,    0,    0,  262},
			{   0,    0,    0,  243,    0,    0},
			{   0,  224,  234,    0,    0,  264},
			{   0,    0,    0,    0,    0,    0},
		};

		public static final int[][] RANK_LV_03 = {
			{   0,    0,    0,    0,    0,    0},
			{ 312,  322,    0,    0,  352,  362},
			{   0,    0,    0,  343,    0,    0},
			{   0,  324,  334,    0,    0,    0},
			{   0,    0,    0,  345,    0,  365},
		};
	}

	public interface TECH_TYPE_MAINGUN_HEAT{
		//里面直接写id
		public static final int[][] RANK_LV_01 = {
			{ 111,    0,  131,    0,    0,  161},
			{   0,    0,    0,  142,    0,    0},
			{ 113,  123,  133,  143,  153,  163},
			{   0,  124,    0,    0,    0,    0},
			{ 115,    0,    0,  145,  155,  165},
		};

		public static final int[][] RANK_LV_02 = {
			{ 211,    0,    0,    0,  251,  261},
			{   0,    0,  232,    0,    0,    0},
			{ 213,  223,  233,  243,  253,  263},
			{   0,  224,    0,    0,    0,    0},
			{ 215,    0,  235,  245,    0,  265},
		};

		public static final int[][] RANK_LV_03 = {
			{ 311,    0,    0,  341,    0,  361},
			{   0,    0,  332,    0,    0,    0},
			{ 313,  323,  333,  343,  353,  363},
			{   0,  324,    0,    0,    0,    0},
			{ 315,    0,  335,    0,  355,  365},
		};
	}
	
	public interface TECH_TYPE_MAINGUN_HESH{
		//里面直接写id
		public static final int[][] RANK_LV_01 = {
			{   0,    0,    0,    0,    0,    0},
			{ 112,    0,    0,  142,  152,    0},
			{   0,  123,    0,    0,    0,    0},
			{ 114,    0,    0,    0,    0,  164},
			{   0,    0,  135,    0,    0,    0},
		};

		public static final int[][] RANK_LV_02 = {
			{   0,    0,    0,    0,    0,    0},
			{ 212,    0,    0,  242,    0,  262},
			{   0,  223,    0,    0,    0,    0},
			{ 214,    0,    0,    0,  254,    0},
			{   0,    0,  235,    0,    0,    0},
		};

		public static final int[][] RANK_LV_03 = {
			{   0,    0,  331,    0,    0,    0},
			{ 312,  322,    0,    0,  352,  362},
			{   0,    0,  333,    0,    0,    0},
			{ 314,    0,    0,  344,  354,  364},
			{   0,    0,    0,    0,    0,    0},
		};

	}
	
	public interface TECH_TYPE_SHIELD_MEDIUM{
		//里面直接写id
		public static final int[][] RANK_LV_01 = {
			{   0,    0,    0,    0,  151,  161},
			{   0,  122,  132,  142,    0,    0},
			{ 113,    0,    0,    0,  153,  163},
			{   0,  124,  134,  144,    0,  164},
			{   0,    0,    0,    0,    0,    0},
		};

		public static final int[][] RANK_LV_02 = {
			{   0,    0,    0,    0,    0,    0},
			{   0,  222,    0,  242,  252,  262},
			{ 213,    0,    0,  243,    0,    0},
			{   0,  224,  234,    0,  254,  264},
			{   0,    0,    0,  245,    0,  265},
		};

		public static final int[][] RANK_LV_03 = {
			{   0,    0,    0,    0,    0,    0},
			{   0,  322,    0,  342,  352,  362},
			{ 313,    0,  333,    0,  353,    0},
			{   0,    0,  334,  344,    0,  364},
			{   0,    0,    0,    0,  355,  365},
		};

	}
	
	public interface TECH_TYPE_SHIELD_HEAVY{
		//里面直接写id
		public static final int[][] RANK_LV_01 = {
			{   0,    0,    0,    0,  151,  161},
			{   0,  122,  132,  142,    0,    0},
			{ 113,    0,    0,    0,  153,    0},
			{   0,  124,  134,  144,    0,  164},
			{   0,    0,    0,    0,    0,    0},
		};

		public static final int[][] RANK_LV_02 = {
			{   0,    0,    0,    0,    0,    0},
			{   0,  222,    0,  242,  252,  262},
			{ 213,    0,    0,  243,    0,    0},
			{   0,  224,  234,  244,  254,  264},
			{   0,    0,    0,    0,    0,    0},
		};

		public static final int[][] RANK_LV_03 = {
			{   0,    0,    0,    0,    0,    0},
			{   0,  322,    0,  342,  352,  362},
			{ 313,    0,  333,    0,    0,    0},
			{   0,    0,  334,  344,    0,  364},
			{   0,    0,    0,    0,  355,  365},
		};

	}
	
	public interface TECH_TYPE_SHIELD_LIGHT{
		//里面直接写id
		public static final int[][] RANK_LV_01 = {
			{   0,    0,    0,    0,    0,    0},
			{   0,  122,  132,  142,  152,  162},
			{ 113,    0,    0,    0,    0,    0},
			{   0,  124,  134,  144,    0,  164},
			{   0,    0,    0,    0,    0,    0},
		};

		public static final int[][] RANK_LV_02 = {
			{   0,    0,    0,    0,    0,    0},
			{   0,  222,    0,  242,  252,  262},
			{ 213,    0,    0,  243,    0,    0},
			{   0,  224,  234,    0,    0,    0},
			{   0,    0,    0,    0,  255,  265},
		};

		public static final int[][] RANK_LV_03 = {
			{   0,    0,    0,    0,    0,  361},
			{   0,  322,    0,    0,  352,    0},
			{ 313,    0,  333,  343,    0,  363},
			{   0,    0,  334,    0,    0,    0},
			{   0,    0,    0,    0,  355,  365},
		};

	}

	public interface TECH_TYPE_BODYWORK_COMPREHENSIVE_BODYWORK{
		//里面直接写id
		public static final int[][] RANK_LV_01 = {
			{   0,  121,    0,    0,    0,    0},
			{ 112,    0,    0,    0,    0,  162},
			{   0,  123,  133,  143,  153,    0},
			{ 114,    0,    0,    0,    0,  164},
			{   0,    0,  135,    0,    0,    0},
		};

		public static final int[][] RANK_LV_02 = {
			{   0,    0,  231,    0,    0,    0},
			{ 212,  222,  232,    0,    0,    0},
			{   0,  223,    0,  243,    0,  263},
			{ 214,    0,    0,  244,  254,    0},
			{   0,    0,    0,    0,    0,    0},
		};

		public static final int[][] RANK_LV_03 = {
			{ 311,    0,    0,    0,  351,    0},
			{   0,    0,  332,  342,    0,    0},
			{   0,  323,    0,    0,    0,  363},
			{ 314,    0,  334,    0,  354,    0},
			{   0,  325,    0,  345,    0,    0},
		};


	}

	public interface TECH_TYPE_ENGINE_COMPREHENSIVE_ENGINE{
		//里面直接写id
		public static final int[][] RANK_LV_01 = {
			{ 111,  121,  131,  141,    0,    0},
			{   0,    0,  132,    0,  152,  162},
			{ 113,  123,    0,  143,    0,    0},
			{   0,    0,  134,  144,  154,  164},
			{   0,    0,    0,    0,    0,    0},
		};

		public static final int[][] RANK_LV_02 = {
			{   0,    0,    0,    0,    0,    0},
			{   0,    0,    0,  242,    0,  262},
			{ 213,  223,  233,  243,  253,    0},
			{   0,    0,    0,  244,    0,  264},
			{ 215,  225,  235,    0,  255,  265},
		};

		public static final int[][] RANK_LV_03 = {
			{   0,    0,    0,    0,  351,  361},
			{ 312,  322,  332,    0,  352,    0},
			{   0,    0,    0,  343,    0,  363},
			{ 314,  324,  334,  344,  354,    0},
			{   0,    0,    0,    0,    0,  365},
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
