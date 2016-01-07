package com.kituri.tankmmdatabase.data.equip;

import com.kituri.app.data.Entry;
import com.kituri.tankmmdatabase.KituriTankMMApplication;
import com.kituri.tankmmdatabase.R;

public class EquipDataBase extends Entry {

	/**
	 * 装备数据库
	 */
	private static final long serialVersionUID = 9096366248299455791L;

	public interface TYPE {
		// 装备类型："炮座", "改装", "外身", "内舱", "内壁", "炮架", "特殊"

		/**
		 * 炮座
		 */
		public static final int GUNREST = 0;
		/**
		 * 改装
		 */
		public static final int REFIT = 1;
		/**
		 * 内舱
		 */
		public static final int INTERNAL_CABINS = 2;
		/**
		 * 外身
		 */
		public static final int OUTSIDE_BODY = 3;
		/**
		 * 内壁
		 */
		public static final int INNER_WALL = 4;
		/**
		 * 炮架
		 */
		public static final int GUN_CARRIAGE = 5;
		/**
		 * 特殊
		 */
		public static final int SPECIAL = 6;

		public static final int ALL[] = { GUNREST, REFIT, INTERNAL_CABINS, 
				OUTSIDE_BODY, INNER_WALL, GUN_CARRIAGE, SPECIAL };
		public static final String ALL_STRING[] = KituriTankMMApplication.getInstance().
				getResources().getStringArray(R.array.equip_manager_all);
		public static final String ALL_ICONS[] = { "icon_equip_type_01",
				"icon_equip_type_02", "icon_equip_type_03", "icon_equip_type_04",
				"icon_equip_type_05", "icon_equip_type_06", "icon_equip_type_07" };
	}

	public interface SLOT{
		// 装备类型："炮座", "改装", "外身", "内舱", "内壁", "炮架", "特殊"

		/**
		 * 炮座
		 */
		public static final int GUNREST = 0;
		/**
		 * 改装
		 */
		public static final int REFIT = 1;
		/**
		 * 内舱
		 */
		public static final int INTERNAL_CABINS = 2;
		/**
		 * 外身
		 */
		public static final int OUTSIDE_BODY = 3;
		/**
		 * 内壁
		 */
		public static final int INNER_WALL = 4;
		/**
		 * 炮架
		 */
		public static final int GUN_CARRIAGE = 5;
		/**
		 * 特殊
		 */
		public static final int SPECIAL = 6;

		public static final int ALL[] = { GUNREST, REFIT, INTERNAL_CABINS, 
				OUTSIDE_BODY, INNER_WALL, GUN_CARRIAGE, SPECIAL };
		public static final String ALL_STRING[] = KituriTankMMApplication.getInstance().
				getResources().getStringArray(R.array.equip_manager_solt);
		public static final String ALL_ICONS[] = { "icon_equip_type_01",
				"icon_equip_type_02", "icon_equip_type_03", "icon_equip_type_04",
				"icon_equip_type_05", "icon_equip_type_06", "icon_equip_type_07" };
	}

	
//	private List<String> ALL_ICONS = new ArrayList<String>();
//	private List<String> ALL_STRING = new ArrayList<String>();
//	private List<Integer> ALL = new ArrayList<Integer>();
//
//	public List<String> getALL_STRING() {
//		return ALL_STRING;
//	}
//
//	public List<String> getALL_ICONS() {
//		return ALL_ICONS;
//	}
//
//	public List<Integer> getALL() {
//		return ALL;
//	}

}
