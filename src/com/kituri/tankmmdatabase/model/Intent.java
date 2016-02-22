package com.kituri.tankmmdatabase.model;

/**
 * @author Kituri
 * 
 */
public class Intent extends com.kituri.app.model.Intent {

	private static final long serialVersionUID = -50553432991124L;

	public Intent(String action) {
		super(action);
	}

	public Intent() {
		super();
	}

	// 利用Intent传递值赋值在这里
	// public static final String EXTRA_FIRST_STARTUP =
	// "com.kituri.app.intent.extra.first_startup";
	public static final String EXTRA_DATABASE_CATEGORY = "com.kituri.app.intent.extra.database.category";
	public static final String EXTRA_TANK_SEARCH_DATA = "com.kituri.app.intent.extra.tank.search.data";
	public static final String EXTRA_EQUIP_SEARCH_DATA = "com.kituri.app.intent.extra.equip.search.data";
	public static final String EXTRA_TANK_DATA = "com.kituri.app.intent.extra.tank.data";
	public static final String EXTRA_TECH_SEARCH_DATA = "com.kituri.app.intent.extra.tech.search.data";
	public static final String EXTRA_TECH_TYPE_DATA = "com.kituri.app.intent.extra.tech.type.data";
	public static final String EXTRA_TECH_SELECT_DATA = "com.kituri.app.intent.extra.tech.select.data";
	public static final String EXTRA_TERRAIN_LIST = "com.kituri.app.intent.extra.terrain.list";
	public static final String EXTRA_TECH_PAGE_DATA = "com.kituri.app.intent.extra.tech.page.data";
	
	public static final String EXTRA_TIP = "com.kituri.app.intent.extra.tip";
	public static final String EXTRA_BOOLEAN_IS_SHARE = "com.kituri.app.intent.extra.boolean.is.share";
	
	// 跳转Action
	public static final String ACTION_DATABASE_CATEGORY = "com.kituri.app.intent.database.category";
	public static final String ACTION_TANK_LIST = "com.kituri.app.intent.tank.list";	
	public static final String ACTION_TANK_DETAIL = "com.kituri.app.intent.tank.detail";
	public static final String ACTION_CONDITIONAL_QUERY = "com.kituri.app.intent.conditional.query";
	public static final String ACTION_EQUIP_DETAIL = "com.kituri.app.intent.equip.detail";
	public static final String ACTION_EQUIP_COUNTS_CHANGE = "com.kituri.app.intent.equip.counts.change";
	public static final String ACTION_TECH_TYPE_DETAIL = "com.kituri.app.intent.tech.type.detail";
	public static final String ACTION_TECH_DETAIL = "com.kituri.app.intent.tech.detail";
	public static final String ACTION_TERRAIN_DETAIL = "com.kituri.app.intent.terrain.detail";
	public static final String ACTION_TECH_EMU_UPDATE = "com.kituri.app.intent.tech.emu.update";
	
	public static final String ACTION_DIALOG_DISMISS = "com.kituri.app.intent.dialog.dismiss";
	
	
	public static final String ACTION_WEB = "com.kituri.app.intent.web";
	
	
}
