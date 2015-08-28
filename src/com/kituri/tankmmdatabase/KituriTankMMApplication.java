package com.kituri.tankmmdatabase;

import android.content.Context;
import android.content.Intent;

import com.kituri.tankmmdatabase.data.equip.EquipSearchData;
import com.kituri.tankmmdatabase.data.tank.TankData;
import com.kituri.tankmmdatabase.data.tank.TankSearchData;
import com.kituri.tankmmdatabase.data.tech.TechSearchData;
import com.kituri.tankmmdatabase.data.tech.TechTypeData;
import com.kituri.tankmmdatabase.ui.equip.EquipListActivity;
import com.kituri.tankmmdatabase.ui.tank.TankDetailActivity;
import com.kituri.tankmmdatabase.ui.tank.TankListActivity;
import com.kituri.tankmmdatabase.ui.tech.TechDetailActivity;
import com.kituri.tankmmdatabase.ui.tech.TechListActivity;

public class KituriTankMMApplication extends com.kituri.app.KituriApplication{

	
	static public void gotoTechTypeDetail(Context context, TechTypeData data){
		Intent intent = new Intent(context, TechDetailActivity.class);		
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.putExtra(com.kituri.tankmmdatabase.model.Intent.EXTRA_TECH_TYPE_DATA,
				data);
		context.startActivity(intent);
	}
	
	static public void gotoTechList(Context context, TechSearchData data){
		Intent intent = new Intent(context, TechListActivity.class);			
		intent.putExtra(com.kituri.tankmmdatabase.model.Intent.EXTRA_TECH_SEARCH_DATA,
				data);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
	}
	
	static public void gotoEquipList(Context context, EquipSearchData data){
		Intent intent = new Intent(context, EquipListActivity.class);		
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.putExtra(com.kituri.tankmmdatabase.model.Intent.EXTRA_EQUIP_SEARCH_DATA,
				data);
		context.startActivity(intent);
	}
	
	static public void gotoTankList(Context context, TankSearchData searchData){
		Intent intent = new Intent(context, TankListActivity.class);		
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.putExtra(com.kituri.tankmmdatabase.model.Intent.EXTRA_TANK_SEARCH_DATA,
				searchData);
		context.startActivity(intent);
	}
	
	static public void gotoTankDetail(Context context, TankData data){
		Intent intent = new Intent(context, TankDetailActivity.class);		
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.putExtra(com.kituri.tankmmdatabase.model.Intent.EXTRA_TANK_DATA,
				data);
		context.startActivity(intent);
	}
	
	@Override
	public void onCreate(){
		super.onCreate();
	}
	
	
	
}
