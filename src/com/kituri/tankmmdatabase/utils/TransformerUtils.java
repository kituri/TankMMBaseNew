package com.kituri.tankmmdatabase.utils;

import android.content.Context;

import com.kituri.tankmmdatabase.data.equip.EquipData;
import com.kituri.tankmmdatabase.data.equip.EquipDataBase;
import com.kituri.tankmmdatabase.data.equip.EquipSearchData;
import com.kituri.tankmmdatabase.data.tank.TankData;
import com.kituri.tankmmdatabase.data.tank.TankDataBase;

public class TransformerUtils {
	
	
	static public int getTankTypeIconResId(Context context, TankData data){
		if(data.getTankType() < TankDataBase.TANK_TYPE.ALL_ICONS.length){
			String icon = TankDataBase.TANK_TYPE.ALL_ICONS[data.getTankType()];
			return context.getResources().getIdentifier(icon, "drawable", context.getPackageName());
		}
		return android.R.color.transparent;
	}
	
	static public int getTankNationalityIconResId(Context context, TankData data){
		if(data.getTankType() < TankDataBase.TANK_NATIONAL.ALL_ICONS.length){
			String icon = TankDataBase.TANK_NATIONAL.ALL_ICONS[data.getTankType()];
			return context.getResources().getIdentifier(icon, "drawable", context.getPackageName());
		}
		return android.R.color.transparent;
	}
	
	static public int getEquipIconResId(Context context, EquipSearchData data){
		if(data.getEquipType() < EquipDataBase.TYPE.ALL_ICONS.length){
			String icon = EquipDataBase.TYPE.ALL_ICONS[data.getEquipType()];
			return context.getResources().getIdentifier(icon, "drawable", context.getPackageName());
		}
		return android.R.color.transparent;
	}
	
	static public int getEquipIconResId(Context context, EquipData data){
		if(data.getEquipType() < EquipDataBase.TYPE.ALL_ICONS.length){
			String icon = EquipDataBase.TYPE.ALL_ICONS[data.getEquipType()];
			return context.getResources().getIdentifier(icon, "drawable", context.getPackageName());
		}
		return android.R.color.transparent;
	}
	
//	static public String getTankSearchDataName(Context context, TankSearchData data){
//		if(data.getQuery() < TankDataBase.TANK_NATIONAL.ALL_ICONS.length){
//			String icon = TankDataBase.TANK_NATIONAL.ALL_ICONS[data.getTankType()];
//			return context.getResources().getIdentifier(icon, "drawable", context.getPackageName());
//		}
//		return 0;
//	}
	
	
//	TankSearchResult to = new TankSearchResult();
//	TankDataBase dataBase = new TankDataBase(from.getQueryKey());
//	if(from.getQueryValue() < dataBase.getALL_ICONS().size()){
//		to.setIconResId(context.getResources().getIdentifier(
//				dataBase.getALL_ICONS().get(from.getQueryValue()),
//				"drawable", context.getPackageName()));
//	}
//	if(from.getQueryValue() < dataBase.getALL_ICONS().size()){
//		to.setName(dataBase.getALL_STRING().get(from.getQueryValue()));
//	}		
//	return to;
	
//	public TankSearchResult getTankSearchResult(){
//		
//	}
//	
//	public String getSearchName(TankSearchData data){
//		switch (data.getQueryKey()) {
//		case TankSearchData.TANK_TYPE:
//			if(data.getQueryValue() < TankData.TANK_TYPE.ALL_STRING.length){
//				return TankData.TANK_TYPE.ALL_STRING[data.getQueryValue()];		
//			}			
//		}
//		return "";
//	}
//	
//	public int getSearchIcon(Context context, TankSearchData data){
//		//getResources().getIdentifier("ic_launcher", "drawable", getPackageName())
//		Resources rs = context.getResources();
//		switch (data.getQueryKey()) {
//		case TankSearchData.TANK_TYPE:
//			if (data.getQueryValue() < TankData.TANK_TYPE.ALL_ICONS.length) {
//				return rs.getIdentifier(TankData.TANK_TYPE.ALL_ICONS[data.getQueryValue()],
//						"drawable", context.getPackageName());
//			}
//		}
//		return 0;
//	}
	
	
	
}
