package com.kituri.tankmmdatabase.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.kituri.app.push.PsPushUserData;
import com.kituri.app.utils.FileUtils;
import com.kituri.tankmmdatabase.KituriTankMMApplication;
import com.kituri.tankmmdatabase.R;
import com.kituri.tankmmdatabase.data.comparator.ComparatorTankDefault;
import com.kituri.tankmmdatabase.data.comparator.ComparatorStatistic;
import com.kituri.tankmmdatabase.data.equip.EquipData;
import com.kituri.tankmmdatabase.data.equip.EquipDataBase;
import com.kituri.tankmmdatabase.data.tank.TankData;
import com.kituri.tankmmdatabase.data.tank.TankDataBase;
import com.kituri.tankmmdatabase.data.tank.TankPic;
import com.kituri.tankmmdatabase.data.tank.TankSearchFilterData;
import com.kituri.tankmmdatabase.data.tank.TankStatisticData;
import com.kituri.tankmmdatabase.data.tech.BodyWorkTechData;
import com.kituri.tankmmdatabase.data.tech.BulletTechData;
import com.kituri.tankmmdatabase.data.tech.EngineTechData;
import com.kituri.tankmmdatabase.data.tech.ShieldTechData;
import com.kituri.tankmmdatabase.data.tech.TechData;
import com.kituri.tankmmdatabase.ui.setting.SettingActivity;
import com.kituri.tankmmdatabase.utils.MUtils;
import com.kituri.tankmmdatabase.utils.Utils;

import android.content.Context;
import android.text.TextUtils;

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
	 * 条件查询，根据科技来查询
	 * */
	static public ArrayList<TankData> conditionalQuery(TechData searchData){
		Boolean spMode = PsPushUserData.getData(KituriTankMMApplication.getInstance(),
				SettingActivity.TAG_DECORATED_MODE, false);
		ArrayList<TankData> tankList = new ArrayList<TankData>();
		switch (searchData.getAllType()) {
		case TechData.TYPE_ENGINE:
			for(TankData tank : KituriTankMMApplication.tanks){
				if(tank.getTankEngine().equals(((EngineTechData)searchData).getTag())){
					if(tank.getTankStar() >= searchData.getRank() || spMode){
						tankList.add(tank);
					}
				}
			}
			break;
		case TechData.TYPE_BODYWORK:
			for(TankData tank : KituriTankMMApplication.tanks){
				for(String tag : ((BodyWorkTechData)searchData).getTags()){
					for(String tankBodywork : tank.getTankBodywork()){
						if(tankBodywork.equals(tag)){
							if(tank.getTankStar() >= searchData.getRank() || spMode){
								tankList.add(tank);
							}
						}
					}
				}
			}
			break;
		case TechData.TYPE_SHIELD:
			for(TankData tank : KituriTankMMApplication.tanks){
				for(String tag : ((ShieldTechData)searchData).getTags()){
					for(String tankShield : tank.getTankShield()){
						if(tankShield.equals(tag)){
							if(tank.getTankStar() >= searchData.getRank() || spMode){
								tankList.add(tank);
							}
						}
					}
				}
			}
			break;
		case TechData.TYPE_MAIN_GUN:
			for(TankData tank : KituriTankMMApplication.tanks){
				for(String tag : ((BulletTechData)searchData).getTags()){
					for(String turret : tank.getTankTurrets()){
						if(turret.equals(tag)){
							if( tank.getTankStar() >= searchData.getRank() || spMode){
								tankList.add(tank);
							}
						}
					}
				}
			}
			break;
		default:
			break;
		}
		return tankList;
	}
	/**
	 * 条件查询，使用多个查询条件
	 * */
	static public ArrayList<TankData> conditionalQuery(TankSearchFilterData searchData){
		ArrayList<TankData> nationalityList = new ArrayList<TankData>();
		if(searchData.getNationality() != TankSearchFilterData.TANK_ALL){
			for(TankData tank : KituriTankMMApplication.tanks){
				if(searchData.getNationality() == tank.getTankNationalityValue()){
					nationalityList.add(tank);
				}
			}
		}else{
			for(TankData tank : KituriTankMMApplication.tanks){
				nationalityList.add(tank);
			}
		}
		ArrayList<TankData> typeList = new ArrayList<TankData>();
		if(searchData.getType() != TankSearchFilterData.TANK_ALL){
			for(TankData tank : nationalityList){
				if(searchData.getType() == tank.getTankType()){
					typeList.add(tank);
				}
			}
		}else{
			for(TankData tank : nationalityList){
				typeList.add(tank);
			}
		}
		ArrayList<TankData> starList = new ArrayList<TankData>();
		if(searchData.getStar() != TankSearchFilterData.TANK_ALL){
			for(TankData tank : typeList){
				if(searchData.getStar() == tank.getTankStar()){
					starList.add(tank);
				}
			}
		}else{
			for(TankData tank : typeList){
				starList.add(tank);
			}
		}
        Collections.sort(starList, new ComparatorStatistic(searchData)); 
		return starList;
	}
	
	
	//获取装备插槽
	static public List<EquipData> getEquipmentSlots(TankData data){
		Boolean spMode = PsPushUserData.getData(KituriTankMMApplication.getInstance(),
				SettingActivity.TAG_DECORATED_MODE, false);
		List<EquipData> list = new ArrayList<EquipData>();
		String[] slots = data.getTankEquipmentSlots().split(" ");
		for(String slot : slots){
			for(int i = 0; i <  EquipDataBase.SLOT.ALL_STRING.length; i++){
				if(slot.equals(EquipDataBase.SLOT.ALL_STRING[i])){
					if(!spMode){
						if(data.getTankStar() < 2 && (i >= 2)){
							break;
						}
						if(data.getTankStar() < 3 && (i >= 5)){
							break;
						}
						if(data.getTankStar() == 1 && list.size() == 2){
							break;
						}
						if(data.getTankStar() == 2 && list.size() == 5){
							break;
						}
					}
					// "炮座", "改装", 
					//"内舱", "外身", "内壁",
					//"炮架", "特殊" 
					EquipData equip = new EquipData();
					equip.setEquipType(i);
					equip.setEquipName(slot);
					list.add(equip);
					
				}
			}
		}
		
		return list;
	}

	static public void init(Context context){
		KituriTankMMApplication.tanks.clear();
		ArrayList<TankData> tanks = parseTanks(context);
		for(TankData tank : tanks){
			KituriTankMMApplication.tanks.add(tank);
		}
	}
	
	static private ArrayList<TankPic> parseHeadPic(Context context){
		ArrayList<TankPic> tankPics = new ArrayList<TankPic>();
		ArrayList<TankPic> tankPics1 = new ArrayList<TankPic>();
		InputStream in = null;
		try {
			in = context.getAssets().open("hp.krw");
			BufferedReader reader = MUtils.getAsReader(FileUtils.readInStream(in));
//			in = context.getAssets().open("head_pic_test.csv");
//			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String line = null;
			String []temp = null;
			while ((line = reader.readLine()) != null) {
				line = Utils.getLanguageString(line);
				temp = line.split(",");
				if (temp != null && temp.length == 3) {
					TankPic tankPic = new TankPic();
					tankPic.setHeadPicPath("head/" + temp[2] + ".png");
					tankPic.setHeadPicTag(temp[0]);
					tankPics.add(tankPic);
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
		
		for(int i = tankPics.size() - 1; i >= 0; i--){
			tankPics1.add(tankPics.get(i));
		}
		
		return tankPics1;
	}
	
	static private ArrayList<TankData> parseTanks(Context context){
		ArrayList<TankData> tankLists = new ArrayList<TankData>();
		ArrayList<TankPic> tankPics = parseHeadPic(context);
		final String notLive2dTag = KituriTankMMApplication.getInstance().
					getResources().getStringArray(R.array.tank_manager_tag_not_live2d)[0];
		InputStream in = null;
		try {
			in = context.getAssets().open("tl.krw");
			BufferedReader reader = MUtils.getAsReader(FileUtils.readInStream(in));
//			in = context.getAssets().open("tank_list_test.csv");
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
				if (temp != null && temp.length == 31) {
					TankData tank = new TankData();
					tank.setTankClass(temp[1]);
					tank.setTankName(temp[2]);
					for(int i = 0; i < TankDataBase.TANK_TYPE.ALL_STRING.length; i++){
						if(temp[3].equals(TankDataBase.TANK_TYPE.ALL_STRING[i])){
							tank.setTankType(TankDataBase.TANK_TYPE.ALL[i]);
							break;
						}
					}
					for(TankPic tankPic : tankPics){
						if(tankPic.getHeadPicTag().equalsIgnoreCase(temp[21]) ||
								tankPic.getHeadPicTag().equalsIgnoreCase(temp[1])){
							tank.setHeadPic(tankPic.getHeadPicPath());
							break;
						}
					}	
					
					tank.setTankNationality(temp[4]);
					for(int i = 0; i < TankDataBase.TANK_NATIONAL.ALL_STRING.length; i++){
						if(TankDataBase.TANK_NATIONAL.ALL_STRING[i].equals(tank.getTankNationality())){
							tank.setTankNationalityValue(TankDataBase.TANK_NATIONAL.ALL[i]);
							break;
						}
					}
					tank.setAge(temp[8]);
					tank.setTankStar(Integer.parseInt(temp[9]));
					tank.setTankAcqierement(temp[10]);
					tank.setTankFire(Integer.parseInt(temp[11]));
					tank.setTankArmourPiercing(Integer.parseInt(temp[12]));
					tank.setTankHit(Integer.parseInt(temp[13]));
					tank.setTankDurable(Integer.parseInt(temp[14]));
					tank.setTankArmour(Integer.parseInt(temp[15]));
					tank.setTankDodge(Integer.parseInt(temp[16]));
					tank.setTankHide(Integer.parseInt(temp[17]));
					tank.setTankSpot(Integer.parseInt(temp[18]));
					tank.setTankEquipmentSlots(temp[19]);
					
					
					ArrayList<String> turrets = new ArrayList<String>();
					if(!TextUtils.isEmpty(temp[20])){
						String[] temps = temp[20].split(" ");
						for(String tag : temps){
							turrets.add(tag);
						}
					}

					tank.setTankTurrets(turrets);
					tank.setTankEngine(temp[25]);
					if(temp[21].indexOf(notLive2dTag) < 0){
						tank.setLive2d(temp[22]);
					}					
					tank.setProfiles(temp[28]);
					ArrayList<String> bodyworks = new ArrayList<String>();
					if(!TextUtils.isEmpty(temp[26])){
						String[] temps = temp[26].split(" ");
						for(String tag : temps){
							bodyworks.add(tag);
						}
					}
					tank.setTankBodywork(bodyworks);
					
					ArrayList<String> tags = new ArrayList<String>();
					if(!TextUtils.isEmpty(temp[23])){
						String[] temps = temp[23].split(" ");
						for(String tag : temps){
							tags.add(tag);
						}
					}
					tank.setTankShield(tags);
					tank.setDrop(temp[30]);
					tankLists.add(tank);
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
		Collections.sort(tankLists, new ComparatorTankDefault()); 
		return tankLists;
	}
	
	
}
