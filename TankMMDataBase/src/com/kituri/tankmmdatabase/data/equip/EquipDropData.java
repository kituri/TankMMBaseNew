package com.kituri.tankmmdatabase.data.equip;

import com.kituri.app.data.Entry;

/**
 * 装备掉落
 */
public class EquipDropData extends Entry{

	private static final long serialVersionUID = 5092219852081843593L;

	//掉落的地图名字（卷11 善意的尽头）
	private String mapName;

	public String getMapName() {
		return mapName;
	}

	public void setMapName(String mapName) {
		this.mapName = mapName;
	}

	public EquipDropData(String mapName){
		this.mapName = mapName;
	}

}
