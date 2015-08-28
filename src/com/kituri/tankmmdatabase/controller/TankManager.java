package com.kituri.tankmmdatabase.controller;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.kituri.tankmmdatabase.data.TestTankData;
import com.kituri.tankmmdatabase.data.tank.TankData;
import com.kituri.tankmmdatabase.data.tank.TankDataBase;
import com.kituri.tankmmdatabase.data.tank.TankFilterList;
import com.kituri.tankmmdatabase.data.tank.TankSearchData;
import com.kituri.tankmmdatabase.data.tank.TankSearchResult;
import com.kituri.tankmmdatabase.data.tank.TankStatisticData;
import com.kituri.tankmmdatabase.utils.RandomUtils;

public class TankManager {

	private TankManager(){
		
	}
	
	static public final int PAGE_LIMIT = 20;
	
	static public List<TankStatisticData> getTankStatisticList(TankData data){
		List<TankStatisticData> list = new ArrayList<TankStatisticData>();
		list.add(new TankStatisticData(TankStatisticData.STATISTIC_FIRE, data.getTankFire()));
		list.add(new TankStatisticData(TankStatisticData.STATISTIC_ARMOURPIERCING, data.getTankArmourPiercing()));
		list.add(new TankStatisticData(TankStatisticData.STATISTIC_HIT, data.getTankHit()));
		list.add(new TankStatisticData(TankStatisticData.STATISTIC_DURABLE, data.getTankDurable()));
		list.add(new TankStatisticData(TankStatisticData.STATISTIC_ARMOUR, data.getTankArmour()));
		list.add(new TankStatisticData(TankStatisticData.STATISTIC_DODGE, data.getTankDodge()));
		list.add(new TankStatisticData(TankStatisticData.STATISTIC_HIDE, data.getTankHide()));
		list.add(new TankStatisticData(TankStatisticData.STATISTIC_SPOT, data.getTankSpot()));
		list.add(new TankStatisticData(TankStatisticData.STATISTIC_RANGE, data.getTankRange()));
		return list;
	}
	
	/**
	 * 条件查询，使用单一查询条件
	 * */
	static public List<TankData> conditionalQuery(TankSearchData searchData){
		return getTestList();
	}
	
	
	static private List<TankData> getTestList(){
		List<TankData> lists = new ArrayList<TankData>();
		for(int i = 0; i < 10; i++){
			lists.add(getRandomTankMM());
		}
		return lists;
	}
	
	
	static private TankData getRandomTankMM(){
		TankData data = new TankData();
		data.setTankArmour(RandomUtils.randomMinMax(1000, 2000));
		data.setTankArmourPiercing(RandomUtils.randomMinMax(1000, 2000));
		data.setTankClass(RandomUtils.randomMinMax(TestTankData.testTanks));
		data.setTankDodge(RandomUtils.randomMinMax(1000, 2000));
		data.setTankDurable(RandomUtils.randomMinMax(1000, 2000));
		data.setTankFire(RandomUtils.randomMinMax(1000, 2000));
		data.setTankHide(RandomUtils.randomMinMax(1000, 2000));
		data.setTankHit(RandomUtils.randomMinMax(1000, 2000));
		data.setTankName("莫夏·梅列茨安娜");
		data.setTankNationality(RandomUtils.randomMinMax(TankDataBase.TANK_NATIONAL.ALL_STRING));
		data.setTankRange(RandomUtils.randomMinMax(1000, 2000));
		data.setTankSpot(RandomUtils.randomMinMax(1000, 2000));
		data.setTankStar(RandomUtils.randomMinMax(1, 3));
		data.setTankType(RandomUtils.randomMinMax(0, 5));
		data.setTankAcqierement("料理3  护理1");
		data.setTankEquipmentSlots("炮座槽2 改装槽1 外身槽2 内壁槽1 特殊槽1");
		data.setTankEngine("抗暴露 抗风沙");
		data.setTankBodywork("抗积雪 广阔适应");		
		return data;
	}
	
	/**
	 * 
	 * */
	static public TankFilterList getTankFilterListForDialog(Context context, TankSearchData data){
		TankFilterList list = new TankFilterList();
		//TankSearchResult to = new TankSearchResult();
		//ALL和其他种类都是一种类型，用来映射其他搜索对象
		list.setTankSearchData(data);
		TankDataBase dataBase = new TankDataBase(data.getQueryKey());
		for(int i = 0; i < dataBase.getALL().size(); i++){
			TankSearchResult to = new TankSearchResult(data);
			to.setQueryValue(dataBase.getALL().get(i));
			to.setIconResId(context.getResources().getIdentifier(
					dataBase.getALL_ICONS().get(i), "drawable", context.getPackageName()));
			to.setName(dataBase.getALL_STRING().get(i));
			list.add(to);
		}
		return list;
	}
	
	static public TankSearchResult getTankSearchResultForDialogAll(Context context, TankFilterList list){
		TankSearchResult to = null;
		if(list.getEntries().size() != 0){
			to = new TankSearchResult((TankSearchData) list.getEntries().get(0));
			to.setName(TankDataBase.TANK_DEFAULT_ALL.ALL_STRING);
			to.setQueryValue(TankDataBase.TANK_DEFAULT_ALL.ALL);
		}
		return to;
	}
	

	
}
