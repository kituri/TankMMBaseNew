package com.kituri.tankmmdatabase.controller;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.kituri.tankmmdatabase.R;
import com.kituri.tankmmdatabase.data.DataBaseCategoryData;


public class DataBaseCategoryManager {
	private DataBaseCategoryManager(){
		
	}
	
	static public List<DataBaseCategoryData> getDataBaseCategoryList(Context context){
		List<DataBaseCategoryData> listEntry = new ArrayList<DataBaseCategoryData>();	
		listEntry.add(getDataBaseCategoryData(DataBaseCategoryData.TYPE_TANK, 
				context.getString(R.string.cap_database_category_tank),
				R.drawable.icon_category_tank, true));
		listEntry.add(getDataBaseCategoryData(DataBaseCategoryData.TYPE_EQUIP, 
				context.getString(R.string.cap_database_category_equip),
				R.drawable.icon_category_equip, true));
		listEntry.add(getDataBaseCategoryData(DataBaseCategoryData.TYPE_TECHNOLOGY, 
				context.getString(R.string.cap_database_category_technology),
				R.drawable.icon_category_technology, true));
		listEntry.add(getDataBaseCategoryData(DataBaseCategoryData.TYPE_DEVELOPMENT, 
				context.getString(R.string.cap_database_category_development),
				R.drawable.icon_category_development, false));
		listEntry.add(getDataBaseCategoryData(DataBaseCategoryData.TYPE_MAP, 
				context.getString(R.string.cap_database_category_map),
				R.drawable.icon_category_map, false));
		listEntry.add(getDataBaseCategoryData(DataBaseCategoryData.TYPE_SKILL, 
				context.getString(R.string.cap_database_category_skill),
				R.drawable.icon_category_skill, false));
		listEntry.add(getDataBaseCategoryData(DataBaseCategoryData.TYPE_QUEST, 
				context.getString(R.string.cap_database_category_quest),
				R.drawable.icon_category_quest, false));
		listEntry.add(getDataBaseCategoryData(DataBaseCategoryData.TYPE_METAPHYSICS, 
				context.getString(R.string.cap_database_category_metaphysics),
				R.drawable.icon_category_metaphysics, false));
		listEntry.add(getDataBaseCategoryData(DataBaseCategoryData.TYPE_SATELLITE, 
				context.getString(R.string.cap_database_category_satellite),
				R.drawable.icon_category_satellite, false));
		return listEntry;
	}
	
	static public DataBaseCategoryData getDataBaseCategoryData(int type, String name, int resId, Boolean enabled){
		DataBaseCategoryData data = new DataBaseCategoryData();
		data.setType(type);
		data.setName(name);
		data.setResId(resId);
		data.setEnabled(enabled);
		return data;
	}
	
}
