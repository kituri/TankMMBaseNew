/**
 * 
 */
package com.kituri.tankmmdatabase.model;




/**
 * @author kituri
 * 
 */
public class DataTypeTransformer {

	//getTankFilterListForDialog
//	public static ListEntry transformTankFilterList(Context context, TankSearchData from) {
//		TankFilterList list = new TankFilterList();
//		//TankSearchResult to = new TankSearchResult();
//		//ALL和其他种类都是一种类型，用来映射其他搜索对象
//		list.setTankSearchData(from);
//		TankDataBase dataBase = new TankDataBase(from.getQueryKey());
//		for(int i = 0; i < dataBase.getALL().size(); i++){
//			TankSearchResult to = new TankSearchResult(from.getQueryKey(), dataBase.getALL().get(i));
//			to.setIconResId(context.getResources().getIdentifier(
//					dataBase.getALL_ICONS().get(i), "drawable", context.getPackageName()));
//			to.setName(dataBase.getALL_STRING().get(i));
//			list.add(to);
//		}
//		return list;
//	}
	
//	public static TankSearchResult transform(Context context, TankSearchData from) {
//		TankSearchResult to = new TankSearchResult();
//		TankDataBase dataBase = new TankDataBase(from.getQueryKey());
//		if(from.getQueryValue() < dataBase.getALL_ICONS().size()){
//			to.setIconResId(context.getResources().getIdentifier(
//					dataBase.getALL_ICONS().get(from.getQueryValue()),
//					"drawable", context.getPackageName()));
//		}
//		if(from.getQueryValue() < dataBase.getALL_ICONS().size()){
//			to.setName(dataBase.getALL_STRING().get(from.getQueryValue()));
//		}
//		
//		return to;
//	}
	
}
