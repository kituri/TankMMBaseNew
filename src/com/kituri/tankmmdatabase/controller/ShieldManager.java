package com.kituri.tankmmdatabase.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.Context;
import android.text.TextUtils;

import com.kituri.app.data.ListEntry;
import com.kituri.app.push.PsPushUserData;
import com.kituri.app.utils.FileUtils;
import com.kituri.tankmmdatabase.KituriTankMMApplication;
import com.kituri.tankmmdatabase.data.comparator.ComparatorStatistic;
import com.kituri.tankmmdatabase.data.tank.TankData;
import com.kituri.tankmmdatabase.data.tank.TankDataBase;
import com.kituri.tankmmdatabase.data.tank.TankSearchFilterData;
import com.kituri.tankmmdatabase.data.tank.TankStatisticData;
import com.kituri.tankmmdatabase.data.tech.BodyWorkTechData;
import com.kituri.tankmmdatabase.data.tech.EngineTechData;
import com.kituri.tankmmdatabase.data.tech.ShieldTechData;
import com.kituri.tankmmdatabase.data.tech.TechData;
import com.kituri.tankmmdatabase.data.tech.TechDataBase;
import com.kituri.tankmmdatabase.data.tech.TechPageData;
import com.kituri.tankmmdatabase.data.tech.TechSearchData;
import com.kituri.tankmmdatabase.data.tech.TechSpecialData;
import com.kituri.tankmmdatabase.data.tech.TechTypeData;
import com.kituri.tankmmdatabase.ui.setting.SettingActivity;
import com.kituri.tankmmdatabase.utils.MUtils;
import com.kituri.tankmmdatabase.utils.RandomUtils;
import com.kituri.tankmmdatabase.utils.Utils;

public class ShieldManager {
	
	private ShieldManager(){
		
	}	
	
	
	static public void init(Context context){
		//KituriTankMMApplication.
		KituriTankMMApplication.shields.clear();
		ArrayList<ShieldTechData> shields = parseShield(context);
		for(ShieldTechData shield : shields){
			KituriTankMMApplication.shields.add(shield);
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
	
	static private ArrayList<ShieldTechData> parseShield(Context context){
		ArrayList<ShieldTechData> shields = new ArrayList<ShieldTechData>();
		InputStream in = null;
		try {
			in = context.getAssets().open("ts.krw");
			BufferedReader reader = MUtils.getAsReader(FileUtils.readInStream(in));
//			in = context.getAssets().open("tech_shield_test.csv");
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
				if (temp != null && temp.length == 38) {
					ShieldTechData shield = new ShieldTechData();
					shield.setTechId(Integer.parseInt(temp[0]));
					shield.setTechName(temp[23]);
					List<TankStatisticData> statisticDatas = new ArrayList<TankStatisticData>();
					addStatistic(statisticDatas, temp[2], temp[3]);
					addStatistic(statisticDatas, temp[4], temp[5]);
					addStatistic(statisticDatas, temp[6], temp[7]);
					addStatistic(statisticDatas, temp[8], temp[9]);
					shield.setStatisticDatas(statisticDatas);
					ArrayList<String> tags = new ArrayList<String>();
					for(int i = 10; i < 23; i++){
						tags.add(temp[i]);
					}
					shield.setTag(tags);
					shield.setSpecialDatas(getSpecialData(temp[24]));
					shield.setRank(Integer.parseInt(temp[25]));
					shield.setCostMoney(Integer.parseInt(temp[36]));
					//基础的4个属性设置
					//科技名字，例：综合车身、标准弹（AP） 小分类中的type
					//private String typeName;
					//typeName的内部属性
					//private int type;
					//private String icon;
					//all里的总的type ，如 "主炮", "防护", "车体", "引擎", "索敌"
					//private int allType;
					if(shield.getTechName().startsWith("重装")){
						shield.setTypeName(TechDataBase.TECH_SHIELD.ALL_STRING[1]);
						shield.setType(TechDataBase.TECH_SHIELD.ALL[1]);
						shield.setIcon(TechDataBase.TECH_SHIELD.ALL_ICONS[1]);
					}else if(shield.getTechName().startsWith("中型")){
						shield.setTypeName(TechDataBase.TECH_SHIELD.ALL_STRING[0]);
						shield.setType(TechDataBase.TECH_SHIELD.ALL[0]);
						shield.setIcon(TechDataBase.TECH_SHIELD.ALL_ICONS[0]);
					}else if(shield.getTechName().startsWith("轻薄")){
						shield.setTypeName(TechDataBase.TECH_SHIELD.ALL_STRING[2]);
						shield.setType(TechDataBase.TECH_SHIELD.ALL[2]);
						shield.setIcon(TechDataBase.TECH_SHIELD.ALL_ICONS[2]);
					}
					shield.setAllType(TechDataBase.TYPE.TYPE_SHIELD);
					shield.setTechIcon(temp[37]);
					shields.add(shield);
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
		return shields;
	}
	
	/**
	 * 条件查询，根据单个坦克查防护
	 * */
	static public ListEntry conditionalQuery(TankData tankData){
		Boolean spMode = PsPushUserData.getData(KituriTankMMApplication.getInstance(),
				SettingActivity.TAG_DECORATED_MODE, false);
		ListEntry shields = new ListEntry();
		for(ShieldTechData shield : KituriTankMMApplication.shields){
			for(String tag : shield.getTags()){
				for(String tankShield : tankData.getTankShield()){
					if(tankShield.equals(tag)){
						if(tankData.getTankStar() >= shield.getRank() || spMode){
							shields.add(shield);
						}
					}
				}
			}

		}
		return shields;
	}
	
}
