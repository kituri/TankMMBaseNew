package com.kituri.tankmmdatabase.controller;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.kituri.app.data.ListEntry;
import com.kituri.tankmmdatabase.data.equip.EquipData;
import com.kituri.tankmmdatabase.data.equip.EquipDataBase;
import com.kituri.tankmmdatabase.data.equip.EquipSearchData;

public class EquipManager {
	
	private EquipManager(){
		
	}

	static public final int PAGE_LIMIT = 20;
	

	/**
	 * 条件查询，使用单一查询条件
	 * */
	static public List<EquipData> conditionalQuery(EquipSearchData searchData){
		
		List<EquipData> equipDatas = new ArrayList<EquipData>();
		
		switch (searchData.getEquipType()) {
		//炮座
		case EquipDataBase.TYPE.GUNREST:
			
			break;
		//改装
		case EquipDataBase.TYPE.REFIT:
			
			break;
		//外身
		case EquipDataBase.TYPE.OUTSIDE_BODY:
			
			break;
		//内舱
		case EquipDataBase.TYPE.INTERNAL_CABINS:
			
			break;
		//内壁
		case EquipDataBase.TYPE.INNER_WALL:
			
			break;
		//炮架
		case EquipDataBase.TYPE.GUN_CARRIAGE:
			
			break;
		//特殊
		case EquipDataBase.TYPE.SPECIAL:
			
			break;
		}
		return equipDatas;
		//return getTestEquipList(searchData);
	}
	
//	static private List<EquipData> getTestEquipList(EquipSearchData searchData){
//		List<EquipData> lists = new ArrayList<EquipData>();
//		for(int i = 0; i < 10; i++){
//			lists.add(getRandomEquipData(searchData));
//		}
//		return lists;		
//	}
//	
//	static private EquipData getRandomEquipData(EquipSearchData searchData){
//		EquipData data = new EquipData();
//		data.setEquipName("测试装备" + RandomUtils.randomMinMax(1, 99));
//		data.setEquipType(searchData.getEquipType());
//		
//		List<EquipSpecialData> equipSpecialDatas = new ArrayList<EquipSpecialData>();
//		equipSpecialDatas.add(new EquipSpecialData(0, "稳定", "从接近阶段开始提高命中5点"));
//		data.setSpecialDatas(equipSpecialDatas);
//		
//		List<TankStatisticData> statisticDatas = new ArrayList<TankStatisticData>();
//		statisticDatas.add(new TankStatisticData(RandomUtils.randomMinMax(0, 2), RandomUtils.randomMinMax(100, 999)));
//		statisticDatas.add(new TankStatisticData(RandomUtils.randomMinMax(0, 2) + 3, RandomUtils.randomMinMax(100, 999)));
//		statisticDatas.add(new TankStatisticData(RandomUtils.randomMinMax(0, 2) + 6, 0 - RandomUtils.randomMinMax(10, 99)));		
//		data.setStatisticDatas(statisticDatas);
//		
//		List<EquipDropData> dropDatas = new ArrayList<EquipDropData>();
//		dropDatas.add(new EquipDropData("13-4 绝望之地"));
//		dropDatas.add(new EquipDropData("14-4 孤篷自振"));
//		data.setDropDatas(dropDatas);
//		
//		return data;
//	}
	
	static public List<EquipSearchData> getEquipFilterListForDialog(Context context, EquipSearchData data){
		List<EquipSearchData> list = new ArrayList<EquipSearchData>();
		for(int i = 0; i < EquipDataBase.TYPE.ALL_STRING.length; i++){
			EquipSearchData sData = new EquipSearchData(data.getSelect(), EquipDataBase.TYPE.ALL[i],
					EquipDataBase.TYPE.ALL_STRING[i]);		
			list.add(sData);
		}
		return list;
	}
	
	static public ListEntry getEquipFilterListEntryForDialog(Context context, EquipSearchData data){
		ListEntry list = new ListEntry();
		for(int i = 0; i < EquipDataBase.TYPE.ALL_STRING.length; i++){
			EquipSearchData sData = new EquipSearchData(data.getSelect(), EquipDataBase.TYPE.ALL[i],
					EquipDataBase.TYPE.ALL_STRING[i]);		
			list.add(sData);
		}
		return list;
	}
	
}
