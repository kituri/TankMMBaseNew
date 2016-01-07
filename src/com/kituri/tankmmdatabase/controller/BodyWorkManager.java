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

public class BodyWorkManager {
	
	private BodyWorkManager(){
		
	}	
	
	
	static public void init(Context context){
		//KituriTankMMApplication.
		KituriTankMMApplication.bodyworks.clear();
		ArrayList<BodyWorkTechData> bodyWorks = parseBodyWork(context);
		for(BodyWorkTechData bodyWork : bodyWorks){
			KituriTankMMApplication.bodyworks.add(bodyWork);
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
	
	static private ArrayList<BodyWorkTechData> parseBodyWork(Context context){
		ArrayList<BodyWorkTechData> bodyWorkLists = new ArrayList<BodyWorkTechData>();
		InputStream in = null;
		try {
			in = context.getAssets().open("tb.krw");
			BufferedReader reader = MUtils.getAsReader(FileUtils.readInStream(in));
//			in = context.getAssets().open("tech_bodywork_test.csv");
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
				if (temp != null && temp.length == 40) {
					BodyWorkTechData bodyWork = new BodyWorkTechData();
					bodyWork.setTechId(Integer.parseInt(temp[0]));
					bodyWork.setTechName(temp[1]);
					List<TankStatisticData> statisticDatas = new ArrayList<TankStatisticData>();
					addStatistic(statisticDatas, temp[2], temp[3]);
					addStatistic(statisticDatas, temp[4], temp[5]);
					addStatistic(statisticDatas, temp[6], temp[7]);
					addStatistic(statisticDatas, temp[8], temp[9]);
					addStatistic(statisticDatas, temp[10], temp[11]);
					addStatistic(statisticDatas, temp[12], temp[13]);
					bodyWork.setStatisticDatas(statisticDatas);
					ArrayList<String> tags = new ArrayList<String>();
					for(int i = 16; i < 26; i++){
						tags.add(temp[i]);
					}
					bodyWork.setTag(tags);
					bodyWork.setSpecialDatas(getSpecialData(temp[26]));
					bodyWork.setRank(Integer.parseInt(temp[27]));
					bodyWork.setCostMoney(Integer.parseInt(temp[38]));
					//基础的4个属性设置
					//科技名字，例：综合车身、标准弹（AP） 小分类中的type
					//private String typeName;
					//typeName的内部属性
					//private int type;
					//private String icon;
					//all里的总的type ，如 "主炮", "防护", "车体", "引擎", "索敌"
					//private int allType;
					bodyWork.setTypeName(TechDataBase.TECH_BODYWORK.ALL_STRING[0]);
					bodyWork.setType(TechDataBase.TECH_BODYWORK.ALL[0]);
					bodyWork.setAllType(TechDataBase.TYPE.TYPE_BODYWORK);
					bodyWork.setIcon(TechDataBase.TECH_BODYWORK.ALL_ICONS[0]);
					bodyWork.setTechIcon(temp[39]);	
					bodyWorkLists.add(bodyWork);
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
		return bodyWorkLists;
	}
	
	/**
	 * 条件查询，根据单个坦克查引擎
	 * */
	static public ListEntry conditionalQuery(TankData tankData){
		Boolean spMode = PsPushUserData.getData(KituriTankMMApplication.getInstance(),
				SettingActivity.TAG_DECORATED_MODE, false);
		ListEntry bodyWorks = new ListEntry();
		for(BodyWorkTechData bodyWork : KituriTankMMApplication.bodyworks){
			for(String tag : bodyWork.getTags()){
				for(String tankBodyWork : tankData.getTankBodywork()){
					if(tankBodyWork.equals(tag)){
						if(tankData.getTankStar() >= bodyWork.getRank() || spMode){
							bodyWorks.add(bodyWork);
						}
					}
				}
			}

			
			
			for(String tag : bodyWork.getTags()){
				if(tankData.getTankBodywork().equals(tag)){
					if(tankData.getTankStar() >= bodyWork.getRank() || spMode){
						bodyWorks.add(bodyWork);
					}
				}
			}

		}
		return bodyWorks;
	}
	
}
