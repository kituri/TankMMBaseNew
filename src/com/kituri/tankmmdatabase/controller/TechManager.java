package com.kituri.tankmmdatabase.controller;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.kituri.app.data.ListEntry;
import com.kituri.tankmmdatabase.data.tank.TankStatisticData;
import com.kituri.tankmmdatabase.data.tech.TechData;
import com.kituri.tankmmdatabase.data.tech.TechDataBase;
import com.kituri.tankmmdatabase.data.tech.TechPageData;
import com.kituri.tankmmdatabase.data.tech.TechSearchData;
import com.kituri.tankmmdatabase.data.tech.TechSpecialData;
import com.kituri.tankmmdatabase.data.tech.TechTypeData;
import com.kituri.tankmmdatabase.utils.RandomUtils;

public class TechManager {
	
	private TechManager(){
		
	}

	static public final int PAGE_LIMIT = 20;
	

	
	
	/**
	 * 条件查询，使用单一查询条件
	 * 根据 TechTypeData 查询 TechData列表
	 * 例：根据标准弹（AP）来查询 AP列表(3张)
	 * */
	static public List<TechPageData> conditionalQuery(Context context, TechTypeData searchData){
		TechDataBase dataBase = new TechDataBase(searchData);	
		List<TechPageData> lists = new ArrayList<TechPageData>();	
		for(int[][] ranks : dataBase.getRANKS()){
			TechPageData pageData = new TechPageData();
			for (int[] i : ranks){
			     for (int j : i){
			    	 pageData.add(conditionalQuery(j, searchData));
			     }
			}
			lists.add(pageData);
		}		
		return lists;
	}
	
	static private TechData conditionalQuery(int id, TechTypeData searchData){
		
		TechData data = new TechData();
		data.setIcon(searchData.getIcon());
		data.setTechId(id);
		data.setAllType(searchData.getAllType());
		data.setType(searchData.getType());
		data.setTechName(searchData.getTypeName() + id);
		getRandomTechData(data);
		return data;
	}
	
//	static private List<TechData> getTestTechList(TechTypeData searchData){
//		
//		List<TechData> lists = new ArrayList<TechData>();
//		for(int i = 0; i < 10; i++){
//			lists.add(getRandomTechData(searchData));
//		}
//		return lists;		
//	
//	}
//	
	
	static private void getRandomTechData(TechData data){
		
		List<TechSpecialData> techSpecialDatas = new ArrayList<TechSpecialData>();
		techSpecialDatas.add(new TechSpecialData(0, "稳定", "从接近阶段开始提高命中5点"));
		data.setSpecialDatas(techSpecialDatas);
		
		List<TankStatisticData> statisticDatas = new ArrayList<TankStatisticData>();
		statisticDatas.add(new TankStatisticData(RandomUtils.randomMinMax(0, 2), RandomUtils.randomMinMax(1000, 9999)));
		statisticDatas.add(new TankStatisticData(RandomUtils.randomMinMax(0, 2) + 3, RandomUtils.randomMinMax(100, 999)));
		statisticDatas.add(new TankStatisticData(RandomUtils.randomMinMax(0, 2) + 6, RandomUtils.randomMinMax(10, 99)));		
		data.setStatisticDatas(statisticDatas);
	}
	
	/**
	 * 查询筛选条件，根据不完整的一个Search类型来查询所有种类
	 * 例：获取列表--> "主炮", "防护", "车体", "引擎", "索敌"
	 * */
	static public List<TechSearchData> getTechFilterListForDialog(Context context, TechSearchData data){
		List<TechSearchData> list = new ArrayList<TechSearchData>();
		for(int i = 0; i < TechDataBase.TYPE.ALL_STRING.length; i++){
			TechSearchData sData = new TechSearchData(data.getSelect(), TechDataBase.TYPE.ALL[i],
					TechDataBase.TYPE.ALL_STRING[i]);		
			list.add(sData);
		}
		return list;
	}
	
	/**
	 * 查询筛选条件，根据一个Search类型来查询所有种类
	 * 例：获取列表--> "主炮", "防护", "车体", "引擎", "索敌"
	 * */
	static public ListEntry getTechFilterListEntryForDialog(Context context, TechSearchData data){
		ListEntry list = new ListEntry();
		for(int i = 0; i < TechDataBase.TYPE.ALL_STRING.length; i++){
			TechSearchData sData = new TechSearchData(data.getSelect(), TechDataBase.TYPE.ALL[i],
					TechDataBase.TYPE.ALL_STRING[i]);		
			list.add(sData);
		}
		return list;
	}
	
	/**
	 * 查询筛选条件，根据一个Search类型来查询所有小分类
	 * 例：根据主炮，来获取“标准弹”、“破甲弹”
	 * */
	static public List<TechTypeData> getTechTypeListEntryForList(Context context, TechSearchData data){
		List<TechTypeData> list = new ArrayList<TechTypeData>();
		TechDataBase dataBase = new TechDataBase(data);
		for(int i = 0; i < dataBase.getALL().size(); i++){
			TechTypeData sData = new TechTypeData(data.getType(), dataBase.getALL().get(i),
					dataBase.getALL_STRING().get(i), dataBase.getALL_ICONS().get(i));		
			list.add(sData);
		}
		return list;
	}
	
}
