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
import com.kituri.tankmmdatabase.data.tank.TankDataBase;
import com.kituri.tankmmdatabase.data.tank.TankStatisticData;
import com.kituri.tankmmdatabase.data.tech.BodyWorkTechData;
import com.kituri.tankmmdatabase.data.tech.BulletTechData;
import com.kituri.tankmmdatabase.data.tech.EngineTechData;
import com.kituri.tankmmdatabase.data.tech.ShieldTechData;
import com.kituri.tankmmdatabase.data.tech.TechData;
import com.kituri.tankmmdatabase.data.tech.TechDataBase;
import com.kituri.tankmmdatabase.data.tech.TechPageData;
import com.kituri.tankmmdatabase.data.tech.TechSearchData;
import com.kituri.tankmmdatabase.data.tech.TechSpecialData;
import com.kituri.tankmmdatabase.data.tech.TechTypeData;
import com.kituri.tankmmdatabase.ui.metaphysics.MetaphysicsActivity;
import com.kituri.tankmmdatabase.utils.MUtils;
import com.kituri.tankmmdatabase.utils.Utils;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class TechManager {

	private TechManager() {

	}

	static public final int PAGE_LIMIT = 20;

	static public void init(Context context) {
		// abilitys
		KituriTankMMApplication.techs.clear();
		ArrayList<TechSpecialData> abilitys = parseAbility(context);
		for (TechSpecialData ability : abilitys) {
			KituriTankMMApplication.techs.add(ability);
		}
	}

	static private ArrayList<TechSpecialData> parseAbility(Context context) {
		ArrayList<TechSpecialData> abilitys = new ArrayList<TechSpecialData>();
		InputStream in = null;
		try {
			in = context.getAssets().open("ta.krw");
			BufferedReader reader = MUtils.getAsReader(FileUtils.readInStream(in));
//			in = context.getAssets().open("tech_ability_test.csv");
//			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String line = null;
			String[] temp = null;
			Boolean bFristFlag = true;
			while ((line = reader.readLine()) != null) {
				// Log.e("", "Line is "+line);
				if(bFristFlag){
					bFristFlag = false;
					continue;
				}
				line = Utils.getLanguageString(line);
				temp = line.split(",");
				if (temp != null && temp.length == 2) {
					abilitys.add(new TechSpecialData(temp[0], temp[1]));
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
		return abilitys;
	}

	/**
	 * 条件查询，使用单一查询条件 根据 TechTypeData 查询 TechData列表 例：根据标准弹（AP）来查询 AP列表(3张)
	 */
	static public List<TechPageData> conditionalQuery(Context context, TechTypeData searchData) {
		TechDataBase dataBase = new TechDataBase(searchData);
		List<TechPageData> lists = new ArrayList<TechPageData>();
		for (int[][] ranks : dataBase.getRANKS()) {
			TechPageData pageData = new TechPageData();
			for (int[] i : ranks) {
				for (int j : i) {
					pageData.add(conditionalQuery(j, searchData));
				}
			}
			lists.add(pageData);
		}
		return lists;
	}

	static private TechData conditionalQuery(int id, TechTypeData searchData) {

		String tag = null;
		TechData techData = null;

		switch (searchData.getAllType()) {
		case TechTypeData.TYPE_ENGINE:
			for (EngineTechData engine : KituriTankMMApplication.engines) {
				if (engine.getTechId() == id) {
					techData = (TechData) engine.clone();
					tag = engine.getTechName();
				}
			}
			break;
		case TechTypeData.TYPE_BODYWORK:
			for (BodyWorkTechData bodyWork : KituriTankMMApplication.bodyworks) {
				if (bodyWork.getTechId() == id) {
					techData = (TechData) bodyWork.clone();
					tag = bodyWork.getTechName();
				}
			}
			break;
		case TechTypeData.TYPE_SHIELD:
			for (ShieldTechData shield : KituriTankMMApplication.shields) {
				if (shield.getTechId() == id) {
					techData = (TechData) shield.clone();
					tag = shield.getTechName();
				}
			}
			break;
		case TechTypeData.TYPE_MAIN_GUN:
			for (BulletTechData bullet : KituriTankMMApplication.bullets) {
				if (bullet.getTechId() == id) {
					techData = (TechData) bullet.clone();
					tag = bullet.getTechName();
				}
			}
			break;
		}

		if (techData == null) {
			techData = new TechData();
			techData.setIcon(searchData.getIcon());
			techData.setTechId(id);
			techData.setAllType(searchData.getAllType());
			techData.setType(searchData.getType());
			techData.setTechName(searchData.getTypeName() + id);
			return techData;
		}

		if (searchData instanceof TechData) {
			if (((TechData) searchData).getTechName().equals(tag)) {
				techData.setIsSelect(true);
			} else {
				techData.setIsSelect(false);
			}
		} else {
			techData.setIsSelect(false);
		}
		return techData;

	}

	// static private List<TechData> getTestTechList(TechTypeData searchData){
	//
	// List<TechData> lists = new ArrayList<TechData>();
	// for(int i = 0; i < 10; i++){
	// lists.add(getRandomTechData(searchData));
	// }
	// return lists;
	//
	// }
	//

	// static private void getRandomTechData(TechData data){
	//
	// List<TechSpecialData> techSpecialDatas = new
	// ArrayList<TechSpecialData>();
	// techSpecialDatas.add(new TechSpecialData(0, "稳定", "从接近阶段开始提高命中5点"));
	// data.setSpecialDatas(techSpecialDatas);
	//
	// List<TankStatisticData> statisticDatas = new
	// ArrayList<TankStatisticData>();
	// statisticDatas.add(new TankStatisticData(RandomUtils.randomMinMax(0, 2),
	// RandomUtils.randomMinMax(1000, 9999)));
	// statisticDatas.add(new TankStatisticData(RandomUtils.randomMinMax(0, 2) +
	// 3, RandomUtils.randomMinMax(100, 999)));
	// statisticDatas.add(new TankStatisticData(RandomUtils.randomMinMax(0, 2) +
	// 6, RandomUtils.randomMinMax(10, 99)));
	// data.setStatisticDatas(statisticDatas);
	// }

	/**
	 * 查询筛选条件，根据不完整的一个Search类型来查询所有种类 例：获取列表--> "主炮", "防护", "车体", "引擎", "索敌"
	 */
	static public List<TechSearchData> getTechFilterListForDialog(Context context, TechSearchData data) {
		List<TechSearchData> list = new ArrayList<TechSearchData>();
		for (int i = 0; i < TechDataBase.TYPE.ALL_STRING.length; i++) {
			TechSearchData sData = new TechSearchData(data.getSelect(), TechDataBase.TYPE.ALL[i],
					TechDataBase.TYPE.ALL_STRING[i]);
			list.add(sData);
		}
		return list;
	}

	/**
	 * 查询筛选条件，根据一个Search类型来查询所有种类 例：获取列表--> "主炮", "防护", "车体", "引擎", "索敌"
	 */
	static public ListEntry getTechFilterListEntryForDialog(Context context, TechSearchData data) {
		ListEntry list = new ListEntry();
		for (int i = 0; i < TechDataBase.TYPE.ALL_STRING.length; i++) {
			TechSearchData sData = new TechSearchData(data.getSelect(), TechDataBase.TYPE.ALL[i],
					TechDataBase.TYPE.ALL_STRING[i]);
			// sData.setAllLv(PsPushUserData.getData(context, sData.getTag(),
			// 1));
			list.add(sData);
		}
		return list;
	}

	/**
	 * 查询筛选条件，根据一个Search类型来查询所有小分类 例：根据主炮，来获取“标准弹”、“破甲弹”
	 */
	static public List<TechTypeData> getTechTypeListEntryForList(Context context, TechSearchData data) {
		return getTechTypeListEntryForList(context, data, false);
	}

	/**
	 * 查询筛选条件，根据一个Search类型来查询所有小分类(是否显示等级，显示等级是在模拟器专用的) 例：根据主炮，来获取“标准弹”、“破甲弹”
	 */
	static public List<TechTypeData> getTechTypeListEntryForList(Context context, TechSearchData data, Boolean isShow) {
		List<TechTypeData> list = new ArrayList<TechTypeData>();
		TechDataBase dataBase = new TechDataBase(data);
		for (int i = 0; i < dataBase.getALL().size(); i++) {
			TechTypeData sData = new TechTypeData(data.getType(), dataBase.getALL().get(i),
					dataBase.getALL_STRING().get(i), dataBase.getALL_ICONS().get(i));
			if (isShow) {
				sData.setAllLv1(PsPushUserData.getData(context, sData.getTag(1), 0));
				sData.setAllLv2(PsPushUserData.getData(context, sData.getTag(2), 0));
				sData.setAllLv3(PsPushUserData.getData(context, sData.getTag(3), 0));
			}
			list.add(sData);
		}
		return list;
	}

	static public ArrayList<TechTypeData> getAllTechType(Context context) {
		ArrayList<TechTypeData> typeDataList = new ArrayList<TechTypeData>();

		for (int i = 0; i < TechDataBase.TYPE.ALL_STRING.length; i++) {
			TechSearchData sData = new TechSearchData(0, TechDataBase.TYPE.ALL[i], TechDataBase.TYPE.ALL_STRING[i]);
			List<TechTypeData> datas = getTechTypeListEntryForList(context, sData);
			for (TechTypeData type : datas) {
				typeDataList.add(type);
			}
		}
		return typeDataList;
	}
	
	static public void techEmuTechUpdate(Context context, TechTypeData data) {
		//PsPushUserData.setData(context, data.getNowTag(), data.getAllLv());
		PsPushUserData.setData(context, data.getTag(1), data.getAllLv1());
		PsPushUserData.setData(context, data.getTag(2), data.getAllLv2());
		PsPushUserData.setData(context, data.getTag(3), data.getAllLv3());
	}

	//此处修改后即为空实现
//	static public void techEmuSave(Context context) {		
//		ArrayList<TechTypeData> typeDataList = getAllTechType(context);
//		for (TechTypeData typeData : typeDataList) {
//			PsPushUserData.setData(context, typeData.getTag(),
//					PsPushUserData.getData(context, typeData.getNowTag(), 0));
//		}
//	}

	//此处修改后即为空实现
//	static public void techInitEmu(Context context) {
//		ArrayList<TechTypeData> typeDataList = getAllTechType(context);
//		for (TechTypeData typeData : typeDataList) {
//			PsPushUserData.setData(context, typeData.getNowTag(),
//					PsPushUserData.getData(context, typeData.getTag(), 0));
//		}
//	}
	
//	static public int getAllLv(Context context, TechTypeData typeData){
//		return PsPushUserData.getData(context, typeData.getTag(1), 0) + 
//				PsPushUserData.getData(context, typeData.getTag(2), 0) + 
//				PsPushUserData.getData(context, typeData.getTag(3), 0);
//		
//	}
	
	static public StringBuffer getShareContent(Context context) {
		ArrayList<TechTypeData> typeDataList = getAllTechType(context);
		for (TechTypeData typeData : typeDataList) {
			typeData.setAllLv1(PsPushUserData.getData(context, typeData.getTag(1), 0));
			typeData.setAllLv2(PsPushUserData.getData(context, typeData.getTag(2), 0));
			typeData.setAllLv3(PsPushUserData.getData(context, typeData.getTag(3), 0));
		}
		StringBuffer sb = new StringBuffer();
		String title = String.format(context.getString(R.string.msg_tech_btn_share_title),
				PsPushUserData.getData(context, MetaphysicsActivity.METAPHYSICS_NAME, ""));
		sb.append(title);
		sb.append("\n");
		for (int i = 0; i < TechDataBase.TYPE.ALL_STRING.length; i++) {
			sb.append(TechDataBase.TYPE.ALL_STRING[i] + ":\n");
			for (TechTypeData type : typeDataList) {
				if (type.getAllType() == TechDataBase.TYPE.ALL[i]) {
					sb.append(type.getTypeName() + " : " + type.getAllLv() + "  \n");
				}
			}
			sb.append("\n");
		}
		return sb;
	}
	
	static public void share(Context context) {
		String title = String.format(context.getString(R.string.msg_tech_btn_share_title),
		PsPushUserData.getData(context, MetaphysicsActivity.METAPHYSICS_NAME, ""));
		StringBuffer sb = getShareContent(context);
		sb.append("------");
		sb.append(context.getString(R.string.msg_share_title));
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.msg_share_subject));
		intent.putExtra(Intent.EXTRA_TEXT, sb.toString());
		intent.putExtra(Intent.EXTRA_TITLE, title);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(Intent.createChooser(intent, context.getString(R.string.msg_share_title)));

	}

	static public TankStatisticData getTankStatisticData(String statisticName, String statisticValue) {
		for (int i = 0; i < TankDataBase.TANK_STATISTIC.ALL_STRING.length; i++) {
			if (TankDataBase.TANK_STATISTIC.ALL_STRING[i].equals(statisticName)) {
				return new TankStatisticData(TankDataBase.TANK_STATISTIC.ALL[i], Integer.parseInt(statisticValue));
			}
		}
		return null;
	}

	static public String getAbilityDescription(String abilityName) {
		for (TechSpecialData ab : KituriTankMMApplication.techs) {
			if (ab.getName().indexOf(abilityName) >= 0) {
				return ab.getDescription();
			}
		}
		return "";
	}

}
