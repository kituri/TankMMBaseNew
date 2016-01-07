package com.kituri.tankmmdatabase.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.kituri.app.push.PsPushUserData;
import com.kituri.tankmmdatabase.KituriTankMMApplication;
import com.kituri.tankmmdatabase.R;
import com.kituri.tankmmdatabase.data.DataBaseCategoryData;
import com.kituri.tankmmdatabase.data.comparator.ComparatorCategory;
import com.kituri.tankmmdatabase.ui.MainActivity;

import android.content.Context;


public class DataBaseCategoryManager {
	private DataBaseCategoryManager(){
		
	}
	
	static public List<DataBaseCategoryData> getDataBaseCategoryList(Context context){
		List<DataBaseCategoryData> listEntry = new ArrayList<DataBaseCategoryData>();	
		listEntry.add(getDataBaseCategoryData(DataBaseCategoryData.TYPE_TANK, 
				context.getString(R.string.cap_database_category_tank),
				R.drawable.icon_category_tank, true, false));
		listEntry.add(getDataBaseCategoryData(DataBaseCategoryData.TYPE_TECHNOLOGY, 
				context.getString(R.string.cap_database_category_technology),
				R.drawable.icon_category_technology, true, false));
		listEntry.add(getDataBaseCategoryData(DataBaseCategoryData.TYPE_TIPS, 
				context.getString(R.string.cap_database_category_tips),
				R.drawable.icon_category_satellite, true, false));
		listEntry.add(getDataBaseCategoryData(DataBaseCategoryData.TYPE_METAPHYSICS, 
				context.getString(R.string.cap_database_category_metaphysics),
				R.drawable.icon_category_metaphysics, true, false));
		listEntry.add(getDataBaseCategoryData(DataBaseCategoryData.TYPE_EQUIP, 
				context.getString(R.string.cap_database_category_equip),
				R.drawable.icon_category_equip, true, false));
		listEntry.add(getDataBaseCategoryData(DataBaseCategoryData.TYPE_DEVELOPMENT, 
				context.getString(R.string.cap_database_category_development),
				R.drawable.icon_category_development, false, false));
		listEntry.add(getDataBaseCategoryData(DataBaseCategoryData.TYPE_SETTING, 
				context.getString(R.string.cap_database_category_setting),
				R.drawable.icon_category_map, true, 
				PsPushUserData.getData(context, KituriTankMMApplication.MODE_NEED_UPDATE, false)));
		listEntry.add(getDataBaseCategoryData(DataBaseCategoryData.TYPE_SKILL, 
				context.getString(R.string.cap_database_category_skill),
				R.drawable.icon_category_skill, false, false));
		listEntry.add(getDataBaseCategoryData(DataBaseCategoryData.TYPE_QUEST, 
				context.getString(R.string.cap_database_category_quest),
				R.drawable.icon_category_quest, false, false));		 
		Collections.sort(listEntry, new ComparatorCategory()); 
		return listEntry;
	}
	
	static public DataBaseCategoryData getDataBaseCategoryData(int type, String name, int resId,
			Boolean enabled, Boolean isBadger){
		DataBaseCategoryData data = new DataBaseCategoryData();
		data.setType(type);
		data.setName(name);
		data.setResId(resId);
		data.setEnabled(enabled);
		if(enabled){
			data.setIsBadger(isBadger);
		}else{
			data.setIsBadger(false);
		}	
		return data;
	}
	
}
