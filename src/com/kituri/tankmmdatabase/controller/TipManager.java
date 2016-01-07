package com.kituri.tankmmdatabase.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.kituri.app.data.ListEntry;
import com.kituri.app.utils.FileUtils;
import com.kituri.tankmmdatabase.KituriTankMMApplication;
import com.kituri.tankmmdatabase.R;
import com.kituri.tankmmdatabase.data.tank.TankData;
import com.kituri.tankmmdatabase.data.tank.TankStatisticData;
import com.kituri.tankmmdatabase.data.tech.BodyWorkTechData;
import com.kituri.tankmmdatabase.data.tech.EngineTechData;
import com.kituri.tankmmdatabase.data.tech.TechSpecialData;
import com.kituri.tankmmdatabase.data.tip.StrategyTerrainData;
import com.kituri.tankmmdatabase.data.tip.TerrainData;
import com.kituri.tankmmdatabase.data.tip.Tip;
import com.kituri.tankmmdatabase.utils.MUtils;
import com.kituri.tankmmdatabase.utils.Utils;

import android.content.Context;


public class TipManager {
	
	private TipManager(){
		
	}
	

	
	static public List<TankStatisticData> getTerrainStatisticList(TerrainData terrain){
		List<TankStatisticData> list = new ArrayList<TankStatisticData>();
		ListEntry bodyworks = BodyWorkManager.conditionalQuery(terrain.getTankData());
		ListEntry engines = EngineManager.conditionalQuery(terrain.getTankData());
		BodyWorkTechData bodyWork = (BodyWorkTechData) bodyworks.getEntries().get(bodyworks.getEntries().size() - 1);
		EngineTechData engine = (EngineTechData) engines.getEntries().get(engines.getEntries().size() - 1);
		
		for (int i = 0; i < terrain.getDebuff().size(); i++) {
			for (TechSpecialData specialData : bodyWork.getSpecialDatas()) {
				if (terrain.getDebuff().get(i).equals(specialData.getName())) {
					TankStatisticData statistic = getTankStatisticData(terrain.getDebuffAttribute().get(i));
					if(statistic != null){
						list.add(getTankStatisticData(terrain.getDebuffAttribute().get(i)));
					}	
				}
			}
		}

		for (int i = 0; i < terrain.getDebuff().size(); i++) {
			for (TechSpecialData specialData : engine.getSpecialDatas()) {
				if (terrain.getDebuff().get(i).equals(specialData.getName())) {
					TankStatisticData statistic = getTankStatisticData(terrain.getDebuffAttribute().get(i));
					if(statistic != null){
						list.add(getTankStatisticData(terrain.getDebuffAttribute().get(i)));
					}
				}
			}
		}
		return list;
	}
	
	static public Boolean[] getTerrainResistanceList(TerrainData terrain){
		Boolean[] list = {false, false, false};
		if(terrain.getTankData() == null){
			return list;
		}
		ListEntry bodyworks = BodyWorkManager.conditionalQuery(terrain.getTankData());
		ListEntry engines = EngineManager.conditionalQuery(terrain.getTankData());
		BodyWorkTechData bodyWork = (BodyWorkTechData) bodyworks.getEntries().get(bodyworks.getEntries().size() - 1);
		EngineTechData engine = (EngineTechData) engines.getEntries().get(engines.getEntries().size() - 1);
		
		for (int i = 0; i < terrain.getDebuff().size(); i++) {
			for (TechSpecialData specialData : bodyWork.getSpecialDatas()) {
				if (terrain.getDebuff().get(i).equals(specialData.getName())) {
					TankStatisticData statistic = getTankStatisticData(terrain.getDebuffAttribute().get(i));
					if(statistic != null){
						list[i] = true;
					}
				}
			}
		}

		for (int i = 0; i < terrain.getDebuff().size(); i++) {
			for (TechSpecialData specialData : engine.getSpecialDatas()) {
				if (terrain.getDebuff().get(i).equals(specialData.getName())) {
					TankStatisticData statistic = getTankStatisticData(terrain.getDebuffAttribute().get(i));
					if(statistic != null){
						list[i] = true;
					}
				}
			}
		}
		return list;
	}
	
//  <string-array name="tip_manager_terrain_attribute">
//  <item>火力</item>
//  <item>穿甲</item>
//  <item>命中</item>
//  <item>耐久</item>
//  <item>装甲</item>
//  <item>闪避</item>
//  <item>隐蔽</item>
//  <item>侦察</item>
//  <item>被伤害</item>
//  <item>阻燃</item>
//  <item>被暴击率</item>        
//</string-array>
	
	/**
	 * 形参为“火力”“装甲”等
	 *  
	 **/
	static public TankStatisticData getTankStatisticData(String attribute){
		String[] array = KituriTankMMApplication.getInstance().getResources().getStringArray(
				R.array.tip_manager_terrain_attribute);
		for(int i = 0; i < array.length; i++){
			if(attribute.equals(array[i])){
				return new TankStatisticData(i, TankStatisticData.STATISTIC_VALUE_NULL);
			}
		}
		return null;
	}

	static public ArrayList<Tip> getTipsList(Context context){
		ArrayList<Tip> tips = new ArrayList<Tip>();
		Tip tip01 = new Tip();
		tip01.setTipName(context.getString(R.string.cap_official_strategy_title));
		Tip tip02 = new Tip();
		tip02.setTipName(context.getString(R.string.cap_terrain_strategy));
		Tip tip03 = new Tip();
		tip03.setTipName(context.getString(R.string.cap_terrain_strategy_sp));
		Tip tip04 = new Tip();
		tip04.setTipName(context.getString(R.string.cap_terrain));		
		tips.add(tip01);
		tips.add(tip02);
		tips.add(tip03);
		tips.add(tip04);
		return tips;
	}
	
	static public ListEntry getSpStrategyTerrains(Context context){
		ListEntry strategys = new ListEntry();
		InputStream in = null;
		try {
			in = context.getAssets().open("msst.krw");
			BufferedReader reader = MUtils.getAsReader(FileUtils.readInStream(in));
			//BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String line = null;
			String []temp = null;
			while ((line = reader.readLine()) != null) {
				// Log.e("", "Line is "+line);
				line = Utils.getLanguageString(line);
				temp = line.split(",");
				if (temp != null && temp.length == 7) {
					StrategyTerrainData strategyTerrain = new StrategyTerrainData();
					strategyTerrain.setStageName(temp[0]);
					for(int i = 1 ; i < temp.length; i++){
						for(TerrainData terrain : KituriTankMMApplication.terrains){
							if(terrain.getTerrainName().equals(temp[i])){
								strategyTerrain.add(terrain);
							}						
						}		
					}					
					strategys.add(strategyTerrain);
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
		return strategys;
	}
	
	
	static public ListEntry getStrategyTerrains(Context context){
		ListEntry strategys = new ListEntry();
		InputStream in = null;
		try {
			in = context.getAssets().open("mst.krw");
			BufferedReader reader = MUtils.getAsReader(FileUtils.readInStream(in));
			//BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String line = null;
			String []temp = null;
			while ((line = reader.readLine()) != null) {
				// Log.e("", "Line is "+line);
				line = Utils.getLanguageString(line);
				temp = line.split(",");
				if (temp != null && temp.length == 5) {
					StrategyTerrainData strategyTerrain = new StrategyTerrainData();
					strategyTerrain.setStageName(temp[0]);
					for(TerrainData terrain : KituriTankMMApplication.terrains){
						if(terrain.getTerrainName().equals(temp[1])){
							strategyTerrain.add(terrain);
						}						
					}
					for(TerrainData terrain : KituriTankMMApplication.terrains){
						if(terrain.getTerrainName().equals(temp[2])){
							strategyTerrain.add(terrain);
						}						
					}
					for(TerrainData terrain : KituriTankMMApplication.terrains){
						if(terrain.getTerrainName().equals(temp[3])){
							strategyTerrain.add(terrain);
						}						
					}
					for(TerrainData terrain : KituriTankMMApplication.terrains){
						if(terrain.getTerrainName().equals(temp[4])){
							strategyTerrain.add(terrain);
						}						
					}
					strategys.add(strategyTerrain);
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
		return strategys;
	}
	
	static private ArrayList<TerrainData> parseTerrains(Context context){
		ArrayList<TerrainData> terrains = new ArrayList<TerrainData>();
		InputStream in = null;
		try {
			in = context.getAssets().open("mttn.krw");
			BufferedReader reader = MUtils.getAsReader(FileUtils.readInStream(in));
//			in = context.getAssets().open("terrain_test.csv");
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
				if (temp != null && temp.length == 13) {
					TerrainData terrainData = new TerrainData();
					terrainData.setTerrainName(temp[0]);
					terrainData.setMixRange(Integer.parseInt(temp[1]));
					terrainData.setMaxRange(Integer.parseInt(temp[2]));
					
					ArrayList<String> debuff = new ArrayList<String>();
					debuff.add(temp[3]);
					debuff.add(temp[4]);
					debuff.add(temp[5]);
					terrainData.setDebuff(debuff);
					ArrayList<String> debuffInfo = new ArrayList<String>();
					debuffInfo.add(temp[6]);
					debuffInfo.add(temp[7]);
					debuffInfo.add(temp[8]);
					terrainData.setDebuffInfo(debuffInfo);
					
					ArrayList<String> debuffAttribute = new ArrayList<String>();
					debuffAttribute.add(temp[9]);
					debuffAttribute.add(temp[10]);
					debuffAttribute.add(temp[11]);
					terrainData.setDebuffAttribute(debuffAttribute);
					
					terrainData.setPngPath("terrain/" + temp[12]);
					terrains.add(terrainData);
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
		return terrains;
	}

	static public void init(Context context){
		//abilitys
		KituriTankMMApplication.terrains.clear();
		ArrayList<TerrainData> terrains = parseTerrains(context);
		for(TerrainData terrain : terrains){
			KituriTankMMApplication.terrains.add(terrain);
		}
	}
	
}
