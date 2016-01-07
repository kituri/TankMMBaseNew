package com.kituri.tankmmdatabase;

import java.util.ArrayList;

import com.kituri.app.KituriApplication;
import com.kituri.tankmmdatabase.controller.BodyWorkManager;
import com.kituri.tankmmdatabase.controller.BulletManager;
import com.kituri.tankmmdatabase.controller.EngineManager;
import com.kituri.tankmmdatabase.controller.ShieldManager;
import com.kituri.tankmmdatabase.controller.TankManager;
import com.kituri.tankmmdatabase.controller.TechManager;
import com.kituri.tankmmdatabase.controller.TipManager;
import com.kituri.tankmmdatabase.data.equip.EquipSearchData;
import com.kituri.tankmmdatabase.data.tank.TankData;
import com.kituri.tankmmdatabase.data.tank.TankSearchFilterData;
import com.kituri.tankmmdatabase.data.tech.BodyWorkTechData;
import com.kituri.tankmmdatabase.data.tech.BulletTechData;
import com.kituri.tankmmdatabase.data.tech.EngineTechData;
import com.kituri.tankmmdatabase.data.tech.ShieldTechData;
import com.kituri.tankmmdatabase.data.tech.TechData;
import com.kituri.tankmmdatabase.data.tech.TechSearchData;
import com.kituri.tankmmdatabase.data.tech.TechSpecialData;
import com.kituri.tankmmdatabase.data.tech.TechTypeData;
import com.kituri.tankmmdatabase.data.tip.TerrainData;
import com.kituri.tankmmdatabase.data.tip.Tip;
import com.kituri.tankmmdatabase.ui.equip.EquipListActivity;
import com.kituri.tankmmdatabase.ui.equip.EquipToolActivity;
import com.kituri.tankmmdatabase.ui.metaphysics.MetaphysicsActivity;
import com.kituri.tankmmdatabase.ui.setting.SettingActivity;
import com.kituri.tankmmdatabase.ui.tank.TankDetailActivity;
import com.kituri.tankmmdatabase.ui.tank.TankListActivity;
import com.kituri.tankmmdatabase.ui.tank.TankLive2DActivity;
import com.kituri.tankmmdatabase.ui.tech.TechDetailActivity;
import com.kituri.tankmmdatabase.ui.tech.TechEmuActivity;
import com.kituri.tankmmdatabase.ui.tech.TechListActivity;
import com.kituri.tankmmdatabase.ui.tip.OfficialStrategyActivity;
import com.kituri.tankmmdatabase.ui.tip.StrategyDetailActivity;
import com.kituri.tankmmdatabase.ui.tip.TerrainActivity;
import com.kituri.tankmmdatabase.ui.tip.TipActivity;
import com.kituri.tankmmdatabase.utils.MUtils;
import com.kituri.tankmmdatabase.utils.Utils;

import android.content.Context;
import android.content.Intent;


public class KituriTankMMApplication<MainActivity> extends KituriApplication{

	static final public ArrayList<TankData> tanks = new ArrayList<TankData>();
	static final public ArrayList<EngineTechData> engines = new ArrayList<EngineTechData>();
	static final public ArrayList<BodyWorkTechData> bodyworks = new ArrayList<BodyWorkTechData>();
	static final public ArrayList<ShieldTechData> shields = new ArrayList<ShieldTechData>();
	static final public ArrayList<BulletTechData> bullets = new ArrayList<BulletTechData>();
	static final public ArrayList<TechSpecialData> techs = new ArrayList<TechSpecialData>();
	static final public ArrayList<TerrainData> terrains = new ArrayList<TerrainData>();
	
	static final public Boolean IS_DEBUG_MODE = true;//debug 开关
	//static public Boolean SP_MODE_IS_MAX_STAR = false; //授勋彩蛋开关
	
	//static final public String SP_MODE_SCHOOL_MIZUGI_REDAY = "sp_mode_school_mizugi_reday";//玄学开关
	//static final public String SP_MODE_SCHOOL_MIZUGI = "sp_mode_school_mizugi";//玄学开关
	static final public String MODE_NEED_UPDATE = "mode_need_update";//需要更新接口
	
	static public void gotoTechTypeDetail(Context context, TechTypeData data){
		Intent intent = new Intent(context, TechDetailActivity.class);		
		intent.putExtra(com.kituri.tankmmdatabase.model.Intent.EXTRA_TECH_TYPE_DATA,
				data);
		context.startActivity(intent);
	}
	
//	static public void gotoTechTypeDetail(Context context, TechData data){
//		Intent intent = new Intent(context, TechDetailActivity.class);	
//		intent.putExtra(com.kituri.tankmmdatabase.model.Intent.EXTRA_TECH_TYPE_DATA,
//				data);
//		intent.putExtra(com.kituri.tankmmdatabase.model.Intent.EXTRA_TECH_SELECT_DATA,
//				data);
//		context.startActivity(intent);
////		Toast.makeText(context, "gotoTechTypeDetail空实现~", Toast.LENGTH_SHORT).show();
//	}
	
	static public void gotoTechList(Context context, TechSearchData data){
		Intent intent = new Intent(context, TechListActivity.class);			
		intent.putExtra(com.kituri.tankmmdatabase.model.Intent.EXTRA_TECH_SEARCH_DATA,
				data);
		context.startActivity(intent);
	}
	
	static public void gotoEquipList(Context context, EquipSearchData data){
		Intent intent = new Intent(context, EquipListActivity.class);		
		intent.putExtra(com.kituri.tankmmdatabase.model.Intent.EXTRA_EQUIP_SEARCH_DATA,
				data);
		context.startActivity(intent);
	}
	
//	static public void gotoTankFilter(Activity activity, TankSearchFilterData data){
//		Intent intent = new Intent(activity, TankFilterActivity.class);		
//		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//		intent.putExtra(com.kituri.tankmmdatabase.model.Intent.EXTRA_TANK_SEARCH_DATA,
//				data);
//		activity.startActivityForResult(intent, requestCode);
//	}
	
	static public void gotoTankList(Context context){
		gotoTankList(context, null);
	}
	
	static public void gotoTankList(Context context, TankSearchFilterData searchData){
		Intent intent = new Intent(context, TankListActivity.class);		
		intent.putExtra(com.kituri.tankmmdatabase.model.Intent.EXTRA_TANK_SEARCH_DATA,
				searchData);
		context.startActivity(intent);
	}
	
	static public void gotoTankDetail(Context context, TankData data){
		Intent intent = new Intent(context, TankDetailActivity.class);		
		intent.putExtra(com.kituri.tankmmdatabase.model.Intent.EXTRA_TANK_DATA,
				data);
		context.startActivity(intent);
	}
	
	static public void gotoLive2D(Context context, TankData data){
		Intent intent = new Intent(context, TankLive2DActivity.class);		
		intent.putExtra(com.kituri.tankmmdatabase.model.Intent.EXTRA_TANK_DATA,
				data);
		context.startActivity(intent);
	}
	
	public static void gotoTips(Context context) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(context, TipActivity.class);		
		context.startActivity(intent);
	}
	
	public static void gotoMetaphysics(Context context) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(context, MetaphysicsActivity.class);		
		context.startActivity(intent);
	}	
	
	public static void gotoOfficialStrategyDetail(Context context, Tip data) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(context, StrategyDetailActivity.class);		
		intent.putExtra(com.kituri.tankmmdatabase.model.Intent.EXTRA_TIP,
				data);
		context.startActivity(intent);
	}
	
	public static void gotoOfficialStrategy(Context context) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(context, OfficialStrategyActivity.class);		
		context.startActivity(intent);
	}
	
	public static void gotoTerrainStrategy(Context context) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(context, TerrainActivity.class);		
		intent.putExtra(com.kituri.tankmmdatabase.model.Intent.EXTRA_TERRAIN_LIST,
				TipManager.getStrategyTerrains(context));
		context.startActivity(intent);
	}
	
	public static void gotoTerrain(Context context, TankData data) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(context, TerrainActivity.class);	
		intent.putExtra(com.kituri.tankmmdatabase.model.Intent.EXTRA_TANK_DATA, data);
		context.startActivity(intent);
	}
	
	public static void gotoSpTerrainStrategy(Context context) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(context, TerrainActivity.class);	
		intent.putExtra(com.kituri.tankmmdatabase.model.Intent.EXTRA_TERRAIN_LIST,
				TipManager.getSpStrategyTerrains(context));
		context.startActivity(intent);
	}
	
	public static void gotoEquipTools(Context context) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(context, EquipToolActivity.class);	
		context.startActivity(intent);
	}
	
	public static void gotoTechEmu(Context context) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(context, TechEmuActivity.class);		
		intent.putExtra(com.kituri.tankmmdatabase.model.Intent.EXTRA_TECH_SEARCH_DATA,
				new TechSearchData(TechData.TYPE_MAIN_GUN));
		context.startActivity(intent);
	}
	
	public static void gotoSetting(Context context){
		Intent intent = new Intent(context, SettingActivity.class);		
		context.startActivity(intent);
		
	}
	
//	private static SQLiteDatabase mySqLiteDatabase;
//	public static SQLiteDatabase getSqLiteDatabase(){
//		return mySqLiteDatabase;
//	}
	
	@Override
	public void onCreate(){
		super.onCreate();
		Utils.getLanguageString("");
		TankManager.init(this);
		EngineManager.init(this);
		BodyWorkManager.init(this);
		ShieldManager.init(this);
		BulletManager.init(this);
		TechManager.init(this);
		TipManager.init(this);
//		FileUtils.test();
//		MUtils.reSave(this, "tm.krw");
//		MUtils.change(this, "terrain_test.csv");
//		MUtils.change(this, "map_sp_strategy_terrain.krw");
//		MUtils.change(this, "map_strategy_terrain.krw");
//		MUtils.change(this, "head_pic_test.csv");
//		MUtils.change(this, "tank_list_test.csv");
//		MUtils.change(this, "tech_shield_test.csv");
//		MUtils.change(this, "tank_bullet_test.csv");
//		MUtils.change(this, "tank_list.csv");
//		MUtils.change(this, "tech_ability_test.csv");
//		MUtils.change(this, "tech_bodywork_test.csv");
//		MUtils.change(this, "tech_engine.csv");
//		MUtils.change(this, "tech_shield_test.csv");
		//mySqLiteDatabase=this.openOrCreateDatabase("kituri_tankmmdatabase.db", MODE_PRIVATE, null);
		//Log.d("cwz_text", "app_start");
		//sql_CreateAndInsert(this.getApplicationContext());
	}
	
//	public void sql_CreateAndInsert(Context context){
//		CreateAndInsert mysql=new CreateAndInsert(context);
////		mysql.cwz_sql(context);
//	}

	
}
