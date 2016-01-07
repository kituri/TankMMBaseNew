package com.kituri.tankmmdatabase.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.kituri.app.data.ListEntry;
import com.kituri.app.push.PsPushUserData;
import com.kituri.app.utils.FileUtils;
import com.kituri.tankmmdatabase.KituriTankMMApplication;
import com.kituri.tankmmdatabase.data.tank.TankData;
import com.kituri.tankmmdatabase.data.tank.TankStatisticData;
import com.kituri.tankmmdatabase.data.tech.EngineTechData;
import com.kituri.tankmmdatabase.data.tech.TechDataBase;
import com.kituri.tankmmdatabase.data.tech.TechSpecialData;
import com.kituri.tankmmdatabase.ui.setting.SettingActivity;
import com.kituri.tankmmdatabase.utils.MUtils;
import com.kituri.tankmmdatabase.utils.Utils;

import android.content.Context;
import android.text.TextUtils;

public class EngineManager {
	
	private EngineManager(){
		
	}	
	
	
	static public void init(Context context){
		//KituriTankMMApplication.
		KituriTankMMApplication.engines.clear();
		ArrayList<EngineTechData> engines = parseEngines(context);
		for(EngineTechData engine : engines){
			KituriTankMMApplication.engines.add(engine);
		}
	}
	
	//属性，火力，命中等
	static private void addStatistic(List<TankStatisticData> statisticDatas,
			String statisticName, String statisticValue){
		if(!TextUtils.isEmpty(statisticName)){
			TankStatisticData statistic = TechManager.getTankStatisticData(
					statisticName, statisticValue);
			if(statistic != null){
				statisticDatas.add(statistic);
			}			
		}
	}
	
	//特殊能力，抗暴露，抗燥热等
	static private List<TechSpecialData> getSpecialData(String specialName){
		List<TechSpecialData> specialDatas = new ArrayList<TechSpecialData>();
		if(!TextUtils.isEmpty(specialName)){
			String[] specialsArray = specialName.split(" ");
			for(String sp : specialsArray){
				specialDatas.add(new TechSpecialData(sp));
			}
		}
		return specialDatas;
	}
	
	static private ArrayList<EngineTechData> parseEngines(Context context){
		ArrayList<EngineTechData> engineLists = new ArrayList<EngineTechData>();
		InputStream in = null;
		try {
			in = context.getAssets().open("te.krw");
			BufferedReader reader = MUtils.getAsReader(FileUtils.readInStream(in));
//			in = context.getAssets().open("tech_engine_test.csv");
//			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String line = null;
			String []temp = null;
			Boolean bFristFlag = true;
			while ((line = reader.readLine()) != null) {
				// Log.e("", "Line is "+line);
				if(bFristFlag){
					bFristFlag = false;
					continue;
				}
				line = Utils.getLanguageString(line);
				temp = line.split(",");
				if (temp != null && temp.length == 37) {
					EngineTechData engine = new EngineTechData();
					engine.setTechId(Integer.parseInt(temp[0]));
					engine.setTechName(temp[1]);
					List<TankStatisticData> statisticDatas = new ArrayList<TankStatisticData>();
					addStatistic(statisticDatas, temp[2], temp[3]);
					addStatistic(statisticDatas, temp[4], temp[5]);
					addStatistic(statisticDatas, temp[6], temp[7]);
					addStatistic(statisticDatas, temp[8], temp[9]);
					addStatistic(statisticDatas, temp[10], temp[11]);
					engine.setStatisticDatas(statisticDatas);
					engine.setTag(temp[13]);
					engine.setSpecialDatas(getSpecialData(temp[23]));
					engine.setRank(Integer.parseInt(temp[24]));
					engine.setCostMoney(Integer.parseInt(temp[35]));
					//基础的4个属性设置
					//科技名字，例：综合车身、标准弹（AP） 小分类中的type
					//private String typeName;
					//typeName的内部属性
					//private int type;
					//private String icon;
					//all里的总的type ，如 "主炮", "防护", "车体", "引擎", "索敌"
					//private int allType;
					engine.setTypeName(TechDataBase.TECH_ENGINE.ALL_STRING[0]);
					engine.setType(TechDataBase.TECH_ENGINE.ALL[0]);
					engine.setAllType(TechDataBase.TYPE.TYPE_ENGINE);
					engine.setIcon(TechDataBase.TECH_ENGINE.ALL_ICONS[0]);
					engine.setTechIcon(temp[36]);		
					engineLists.add(engine);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return engineLists;
	}
	
	/**
	 * 条件查询，根据单个坦克查引擎
	 * */
	static public ListEntry conditionalQuery(TankData tankData){
		Boolean spMode = PsPushUserData.getData(KituriTankMMApplication.getInstance(),
				SettingActivity.TAG_DECORATED_MODE, false);
		ListEntry engines = new ListEntry();
		for(EngineTechData engine : KituriTankMMApplication.engines){
			if(engine.getTag().equals(tankData.getTankEngine()) ){
				if(tankData.getTankStar() >= engine.getRank() || spMode){
					engines.add(engine);
				}				
			}
		}
		return engines;
	}
	
}
