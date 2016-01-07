package com.kituri.tankmmdatabase.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.kituri.app.data.ListEntry;
import com.kituri.app.push.PsPushUserData;
import com.kituri.app.utils.FileUtils;
import com.kituri.tankmmdatabase.KituriTankMMApplication;
import com.kituri.tankmmdatabase.R;
import com.kituri.tankmmdatabase.data.tank.TankData;
import com.kituri.tankmmdatabase.data.tank.TankStatisticData;
import com.kituri.tankmmdatabase.data.tech.BulletTechData;
import com.kituri.tankmmdatabase.data.tech.ShieldTechData;
import com.kituri.tankmmdatabase.data.tech.TechDataBase;
import com.kituri.tankmmdatabase.data.tech.TechSpecialData;
import com.kituri.tankmmdatabase.ui.setting.SettingActivity;
import com.kituri.tankmmdatabase.utils.MUtils;
import com.kituri.tankmmdatabase.utils.Utils;

import android.content.Context;
import android.text.TextUtils;

public class BulletManager {
	
	private BulletManager(){
		
	}	
	
	
	static public void init(Context context){
		//KituriTankMMApplication.
		KituriTankMMApplication.bullets.clear();
		ArrayList<BulletTechData> bullets = parseBullet(context);
		for(BulletTechData bullet : bullets){
			KituriTankMMApplication.bullets.add(bullet);
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
	
	static private ArrayList<BulletTechData> parseBullet(Context context){
		ArrayList<BulletTechData> bullets = new ArrayList<BulletTechData>();
		InputStream in = null;
		try {
			in = context.getAssets().open("tm.krw");
			BufferedReader reader = MUtils.getAsReader(FileUtils.readInStream(in));
//			in = context.getAssets().open("tank_bullet_test.csv");
//			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String line = null;
			String []temp = null;
			Boolean bFristFlag = true;
			int count = 0;
			String[] arrayMainGun = context.getResources().getStringArray(R.array.bullet_manager_main_gun);
			while ((line = reader.readLine()) != null) {
				// Log.e("", "Line is "+line);
				if(bFristFlag){
					bFristFlag = false;
					continue;
				}
				count++;
				if(count == 75){
					count = 75;
				}
				line = Utils.getLanguageString(line);
				temp = line.split(",");
				if (temp != null && temp.length == 38) {
					BulletTechData bullet = new BulletTechData();
					bullet.setTechId(Integer.parseInt(temp[0]));
					bullet.setTechName(temp[22]);
					List<TankStatisticData> statisticDatas = new ArrayList<TankStatisticData>();
					addStatistic(statisticDatas, temp[2], temp[3]);
					addStatistic(statisticDatas, temp[4], temp[5]);
					addStatistic(statisticDatas, temp[6], temp[7]);
					bullet.setStatisticDatas(statisticDatas);
					ArrayList<String> tags = new ArrayList<String>();
					for(int i = 13; i < 21; i++){
						tags.add(temp[i]);
					}
					bullet.setTag(tags);
					bullet.setSpecialDatas(getSpecialData(temp[23]));
					bullet.setRank(Integer.parseInt(temp[24]));
					bullet.setCostMoney(Integer.parseInt(temp[35]));
					//基础的4个属性设置
					//科技名字，例：综合车身、标准弹（AP） 小分类中的type
					//private String typeName;
					//typeName的内部属性
					//private int type;
					//private String icon;
					//all里的总的type ，如 "主炮", "防护", "车体", "引擎", "索敌"
					//private int allType;
					//TECH_MAIN_GUN
					
					if(temp[36].equals(arrayMainGun[0])){
						bullet.setTypeName(TechDataBase.TECH_MAIN_GUN.ALL_STRING[0]);
						bullet.setType(TechDataBase.TECH_MAIN_GUN.ALL[0]);
						bullet.setIcon(TechDataBase.TECH_MAIN_GUN.ALL_ICONS[0]);
					}else if(temp[36].equals(arrayMainGun[1])){
						bullet.setTypeName(TechDataBase.TECH_MAIN_GUN.ALL_STRING[1]);
						bullet.setType(TechDataBase.TECH_MAIN_GUN.ALL[1]);
						bullet.setIcon(TechDataBase.TECH_MAIN_GUN.ALL_ICONS[1]);
					}else if(temp[36].equals(arrayMainGun[2])){
						bullet.setTypeName(TechDataBase.TECH_MAIN_GUN.ALL_STRING[2]);
						bullet.setType(TechDataBase.TECH_MAIN_GUN.ALL[2]);
						bullet.setIcon(TechDataBase.TECH_MAIN_GUN.ALL_ICONS[2]);
					}else if(temp[36].equals(arrayMainGun[3])){
						bullet.setTypeName(TechDataBase.TECH_MAIN_GUN.ALL_STRING[3]);
						bullet.setType(TechDataBase.TECH_MAIN_GUN.ALL[3]);
						bullet.setIcon(TechDataBase.TECH_MAIN_GUN.ALL_ICONS[3]);
					}else if(temp[36].equals(arrayMainGun[4])){
						bullet.setTypeName(TechDataBase.TECH_MAIN_GUN.ALL_STRING[4]);
						bullet.setType(TechDataBase.TECH_MAIN_GUN.ALL[4]);
						bullet.setIcon(TechDataBase.TECH_MAIN_GUN.ALL_ICONS[4]);
					}else if(temp[36].equals(arrayMainGun[5])){
						bullet.setTypeName(TechDataBase.TECH_MAIN_GUN.ALL_STRING[5]);
						bullet.setType(TechDataBase.TECH_MAIN_GUN.ALL[5]);
						bullet.setIcon(TechDataBase.TECH_MAIN_GUN.ALL_ICONS[5]);
					}else if(temp[36].equals(arrayMainGun[6])){
						bullet.setTypeName(TechDataBase.TECH_MAIN_GUN.ALL_STRING[6]);
						bullet.setType(TechDataBase.TECH_MAIN_GUN.ALL[6]);
						bullet.setIcon(TechDataBase.TECH_MAIN_GUN.ALL_ICONS[6]);
					}
					bullet.setAllType(TechDataBase.TYPE.TYPE_MAIN_GUN);
					bullet.setTechIcon(temp[37]);				
					bullets.add(bullet);
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
		return bullets;
	}
	
	/**
	 * 条件查询，根据单个坦克查防护
	 * */
	static public ListEntry conditionalQuery(TankData tankData){
		Boolean spMode = PsPushUserData.getData(KituriTankMMApplication.getInstance(),
				SettingActivity.TAG_DECORATED_MODE, false);
		ListEntry shields = new ListEntry();
		for(BulletTechData bullet : KituriTankMMApplication.bullets){
			for(String tag : bullet.getTags()){
				for(String tankTurret : tankData.getTankTurrets()){
					if(tankTurret.equals(tag)){
						if(tankData.getTankStar() >= bullet.getRank() || spMode){
							shields.add(bullet);
						}
					}
				}
			}
		}
		return shields;
	}
	
}
