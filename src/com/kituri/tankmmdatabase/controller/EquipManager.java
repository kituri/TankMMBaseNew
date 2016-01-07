package com.kituri.tankmmdatabase.controller;

import java.util.ArrayList;
import java.util.List;

import com.kituri.app.data.ListEntry;
import com.kituri.tankmmdatabase.data.equip.CostResData;
import com.kituri.tankmmdatabase.data.equip.EquipAllCostData;
import com.kituri.tankmmdatabase.data.equip.EquipCountData;
import com.kituri.tankmmdatabase.data.equip.EquipData;
import com.kituri.tankmmdatabase.data.equip.EquipDataBase;
import com.kituri.tankmmdatabase.data.equip.EquipDropData;
import com.kituri.tankmmdatabase.data.equip.EquipLvUpCostData;
import com.kituri.tankmmdatabase.data.equip.EquipSearchData;
import com.kituri.tankmmdatabase.data.equip.EquipSpecialData;
import com.kituri.tankmmdatabase.data.tank.TankStatisticData;
import com.kituri.tankmmdatabase.utils.RandomUtils;

import android.content.Context;

public class EquipManager {
	
	private EquipManager(){
		
	}

	static public final int PAGE_LIMIT = 20;
	
	
	public static ArrayList<CostResData> getCostResList(EquipAllCostData costData){
		ArrayList<CostResData> list = new ArrayList<CostResData>();
		if(costData.getIsSP()){
			if(costData.getS1Counts() != 0){
				CostResData data = new CostResData(CostResData.RES_TYPE_EQUIP_TYPE_01,
						costData.getS1CountsNor());
				list.add(data);
			}
			if(costData.getS1Counts() != 0){
				CostResData data = new CostResData(CostResData.RES_TYPE_EQUIP_TYPE_07,
						costData.getS1CountsSP());
				list.add(data);
			}
		}else{
			if(costData.getS1Counts() != 0){
				CostResData data = new CostResData(CostResData.RES_TYPE_EQUIP_TYPE_01,
						costData.getS1Counts());
				list.add(data);
			}
		}
		if(costData.getCostPoint() != 0){
			CostResData data = new CostResData(CostResData.RES_TYPE_TECH_POINT, costData.getCostPoint());
			list.add(data);
		}
		if(costData.getN1Maps() != 0){
			CostResData data = new CostResData(CostResData.RES_TYPE_FILE_N01, costData.getN1Maps());
			list.add(data);
		}
		if(costData.getN2Maps() != 0){
			CostResData data = new CostResData(CostResData.RES_TYPE_FILE_N02, costData.getN2Maps());
			list.add(data);
		}
		if(costData.getN3Maps() != 0){
			CostResData data = new CostResData(CostResData.RES_TYPE_FILE_N03, costData.getN3Maps());
			list.add(data);
		}
		if(costData.getN4Maps() != 0){
			CostResData data = new CostResData(CostResData.RES_TYPE_FILE_N04, costData.getN4Maps());
			list.add(data);
		}
		return list;		
	}
	
	 /***
		 *传一个 
		 */
		private static EquipAllCostData getEquipCountList(final int equipLv,
				ArrayList<EquipCountData> list,
				ArrayList<EquipCountData> listSP,
				Boolean isSP){
			EquipAllCostData allCost = new EquipAllCostData();
			//Boolean isSp = listSp != null ? true : false;
//			if(equipLv == 1){
//				allCost.setS1Counts(1);
//				if(isSp){
//					allCost.setS1CountsNor(1);	
//				}else{
//					allCost.setS1CountsSP(1);	
//				}
//				
//				return allCost;
//			}
//			ArrayList<EquipCountData> list = new ArrayList<EquipCountData>();
//			ArrayList<EquipCountData> listSp = new ArrayList<EquipCountData>();
//			for(EquipCountData data: listSrc){
//				if(data.getIsSp()){
//					listSp.add(data);
//				}else{
//					list.add(data);
//				}
//			}
			
			int point = 0;
			int pointNor = 0;
			int pointSp = 0;
			int n1 = 0;
			int n2 = 0;
			int n3 = 0;
			int n4 = 0;
			int s1count = 1;
			int s1Norcount = 0;
			int s1SpCount = 1;

			ArrayList<EquipLvUpCostData> costList = getEquipLvUpCostList();
			// = getEquipLvUpCostData(equipLv, costList).getEquipCounts();
//			int[] sCountsArray = new int[equipLv + 1];
//			int[] sCountsArraySP = new int[equipLv + 1];
//			int[] sCountsArrayNor = new int[equipLv + 1];
			
			//setsCountsArraySP
			for(int i = equipLv; i > 1; i--){
				EquipLvUpCostData nowCost = getEquipLvUpCostData(i, costList);
				if(nowCost == null){
					break;
				}
				int cost = nowCost.getCostPoint();
				int count = nowCost.getEquipCounts();
				int countSp = nowCost.getSpEquipCounts();
				
				s1count = s1count * count;
				s1SpCount = s1SpCount * countSp;
				s1Norcount = s1count - s1SpCount;
				
				int ss1count = s1count;
				int ss1SpCount = s1SpCount;
				int sss1count = s1Norcount;
				
				if(isSP){
					if(listSP != null){
						EquipCountData eEquipCount = getEquipCountData(i - 1, listSP);
						if(eEquipCount != null){
							if(eEquipCount.getCounts() != 0){
								int countS = eEquipCount.getCounts();
								eEquipCount.setCounts(countS - s1SpCount > 0 ? countS - s1SpCount : 0);
								if(countS > s1SpCount){
									countS = s1SpCount;
								}
								s1SpCount-=countS;
								s1count-=countS;
								s1Norcount = s1count - s1SpCount;
							}
						}
					}
				}

				
				if(list != null){
					EquipCountData eEquipCount = getEquipCountData(i - 1, list);;
					if(eEquipCount != null){
						if(eEquipCount.getCounts() != 0){
							int countN = eEquipCount.getCounts();
							eEquipCount.setCounts(countN - s1Norcount > 0 ? countN - s1Norcount : 0);
							if(isSP){
								if(countN > s1Norcount){
									countN = s1Norcount;
								}
								s1Norcount-=countN;
								s1count-=countN;
								s1SpCount = s1count - s1Norcount;
							}else{
								if(countN > s1count){
									countN = s1count;
								}
								s1count-=countN;
								s1SpCount = 0;
								s1Norcount = 0;
							}
						}
					}
				}
				
				
				int costPoint = cost * ss1count / count;
				point += costPoint;			

				
				switch (nowCost.getMapLv()) {
				case 1:
					n1 = nowCost.getMapCounts() * ss1count / count;
					break;
					
				case 2:
					n2 = nowCost.getMapCounts() * ss1count / count;
					break;
					
				case 3:
					n3 = nowCost.getMapCounts() * ss1count / count;
					break;
					
				case 4:
					n4 = nowCost.getMapCounts() * ss1count / count;
					break;
				}
			}
			
//			sCountsArray[1] = sCountsArray[1] * 3;
//			sCountsArraySP[1] = sCountsArraySP[1] * 3;
//			sCountsArrayNor[1] = sCountsArrayNor[1] * 3;
			
			allCost.setCostPoint(point);
//			allCost.setCostPointNor(pointNor);
//			allCost.setCostPointSP(pointSp);
			allCost.setS1Counts(s1count);
			allCost.setS1CountsNor(s1Norcount);
			allCost.setS1CountsSP(s1SpCount);
			allCost.setN1Maps(n1);
			allCost.setN2Maps(n2);
			allCost.setN3Maps(n3);
			allCost.setN4Maps(n4);
//			allCost.setsCountsArray(sCountsArray);
//			allCost.setsCountsArraySP(sCountsArraySP);
//			allCost.setsCountsArrayNor(sCountsArrayNor);
			return allCost;
		}

		
		static public EquipAllCostData calculationCost(final int equipLv, ArrayList<EquipCountData> listSrc, Boolean isSP,
				int count){
			
			ArrayList<EquipCountData> list = new ArrayList<EquipCountData>();
			ArrayList<EquipCountData> listSp = new ArrayList<EquipCountData>();
			for(EquipCountData data: listSrc){
				if(data.getIsSp()){
					listSp.add((EquipCountData) data.clone());
				}else{
					list.add((EquipCountData) data.clone());
				}
			}
			EquipAllCostData allCost = new EquipAllCostData();
			for(int i = 0; i < count; i++){
				plus(allCost, getEquipCountList(equipLv, list, listSp, isSP), isSP);
			}
			allCost.setLv(equipLv);
			allCost.setIsSP(isSP);
			allCost.setMakeCount(count);
			return allCost;
		}
		
		static public void plus(EquipAllCostData src, EquipAllCostData plus, Boolean isSp){
			src.setCostPoint(src.getCostPoint() + plus.getCostPoint());
			src.setN1Maps(src.getN1Maps() + plus.getN1Maps());
			src.setN2Maps(src.getN2Maps() + plus.getN2Maps());
			src.setN3Maps(src.getN3Maps() + plus.getN3Maps());
			src.setN4Maps(src.getN4Maps() + plus.getN4Maps());
			src.setS1Counts(src.getS1Counts() + plus.getS1Counts());
			if(isSp){
				src.setCostPointSP(src.getCostPointSP() + plus.getCostPointSP());
				src.setS1CountsSP(src.getS1CountsSP() + plus.getS1CountsSP());
				src.setCostPointNor(src.getCostPointNor() + plus.getCostPointNor());
				src.setS1CountsNor(src.getS1CountsNor() + plus.getS1CountsNor());
			}
		}
		
		static public void subtraction(EquipAllCostData src, EquipAllCostData sub, Boolean isSp){
			src.setCostPoint(src.getCostPoint() - sub.getCostPoint());
			src.setN1Maps(src.getN1Maps() - sub.getN1Maps());
			src.setN2Maps(src.getN2Maps() - sub.getN2Maps());
			src.setN3Maps(src.getN3Maps() - sub.getN3Maps());
			src.setN4Maps(src.getN4Maps() - sub.getN4Maps());
			src.setS1Counts(src.getS1Counts() - sub.getS1Counts());
			if(isSp){
				src.setCostPointSP(src.getCostPointSP() - sub.getCostPointSP());
				src.setS1CountsSP(src.getS1CountsSP() - sub.getS1CountsSP());
			//}else{
				src.setCostPointNor(src.getCostPointNor() - sub.getCostPointNor());
				src.setS1CountsNor(src.getS1CountsNor() - sub.getS1CountsNor());
			}
		}
		
		static private EquipCountData getEquipCountData(final int equipLv, ArrayList<EquipCountData>  list){
			for(EquipCountData data : list){
				if(data.getLv() == equipLv){
					return data;
				}
			}
			return null;
		}
		
		static private EquipLvUpCostData getEquipLvUpCostData(final int equipLv, ArrayList<EquipLvUpCostData> list){
			for(EquipLvUpCostData data : list){
				if(data.getEquipLv() == equipLv){
					return data;
				}
			}
			return null;
		}
		
		static private ArrayList<EquipLvUpCostData> getEquipLvUpCostList(){
			ArrayList<EquipLvUpCostData> list = new ArrayList<EquipLvUpCostData>();
			for(int i = 2; i < 11; i ++){
				EquipLvUpCostData data = new EquipLvUpCostData();
				data.setEquipLv(i);
				data.setEquipCounts(3);
				data.setMapCounts(0);
				data.setMapLv(0);
				
				switch (data.getEquipLv()) {
				case 2:
					data.setCostPoint(40);
					break;
				case 3:
					data.setCostPoint(80);
					break;
				case 4:
					data.setCostPoint(160);
					break;
				case 5:
					data.setCostPoint(320);
					break;
				case 6:
					data.setCostPoint(640);
					break;
				case 7:
					data.setCostPoint(1280);
					data.setMapLv(1);
					data.setMapCounts(80);
					data.setEquipCounts(2);
					break;
				case 8:
					data.setCostPoint(2560);
					data.setMapLv(2);
					data.setMapCounts(105);
					data.setEquipCounts(2);
					break;
				case 9:
					data.setCostPoint(5120);
					data.setMapLv(3);
					data.setMapCounts(145);
					data.setEquipCounts(2);
					break;
				case 10:
					data.setCostPoint(10240);
					data.setMapLv(4);
					data.setMapCounts(195);
					data.setEquipCounts(2);
					break;
				}
				list.add(data);
			}
			
			return list;
		}
		
		
		
		
		static public ArrayList<EquipCountData> getEquipCountList(){
			ArrayList<EquipCountData> list = new ArrayList<EquipCountData>();
			for(int i = 1; i < 9; i++){
				EquipCountData data = new EquipCountData();
				data.setLv(i);
				data.setCounts(0);
				data.setIsSp(false);
				list.add(data);
			}
			for(int i = 1; i < 9; i++){
				EquipCountData data = new EquipCountData();
				data.setLv(i);
				data.setCounts(0);
				data.setIsSp(true);
				list.add(data);
			}
			return list;
		}

	/**
	 * 条件查询，使用单一查询条件
	 * */
	static public List<EquipData> conditionalQuery(EquipSearchData searchData){
		
		List<EquipData> equipDatas = new ArrayList<EquipData>();
		
//		//select sql_select=new select();
//		
//		switch (searchData.getEquipType()) {
//		//炮座
//		case EquipDataBase.TYPE.GUNREST:
//			sql_select.set_equip_type(EquipDataBase.TYPE.GUNREST);
//			break;
//		//改装
//		case EquipDataBase.TYPE.REFIT:
//			sql_select.set_equip_type(EquipDataBase.TYPE.REFIT);			
//			break;
//		//外身
//		case EquipDataBase.TYPE.OUTSIDE_BODY:			
//			sql_select.set_equip_type(EquipDataBase.TYPE.OUTSIDE_BODY);
//			break;
//		//内舱
//		case EquipDataBase.TYPE.INTERNAL_CABINS:			
//			sql_select.set_equip_type(EquipDataBase.TYPE.INTERNAL_CABINS);
//			break;
//		//内壁
//		case EquipDataBase.TYPE.INNER_WALL:			
//			sql_select.set_equip_type(EquipDataBase.TYPE.INNER_WALL);
//			break;
//		//炮架
//		case EquipDataBase.TYPE.GUN_CARRIAGE:		
//			sql_select.set_equip_type(EquipDataBase.TYPE.GUN_CARRIAGE);
//			break;
//		//特殊
//		case EquipDataBase.TYPE.SPECIAL:		
//			sql_select.set_equip_type(EquipDataBase.TYPE.SPECIAL);
//			break;
//		}
//		
//		equipDatas=sql_select.getEquipList();
		
		return equipDatas;
//		return getTestEquipList(searchData);
	}
//	
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
//	
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
