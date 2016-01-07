package com.kituri.tankmmdatabase.data.tip;

import java.util.ArrayList;

import com.kituri.app.data.Entry;
import com.kituri.tankmmdatabase.data.tank.TankData;

public class TerrainData extends Entry{

	/**
	 * 地形数据
	 */
	private static final long serialVersionUID = -626238274851774483L;

	public String getTerrainName() {
		return terrainName;
	}
	public void setTerrainName(String terrainName) {
		this.terrainName = terrainName;
	}
	public int getMixRange() {
		return mixRange;
	}
	public void setMixRange(int mixRange) {
		this.mixRange = mixRange;
	}
	public int getMaxRange() {
		return maxRange;
	}
	public void setMaxRange(int maxRange) {
		this.maxRange = maxRange;
	}

	public String getPngPath() {
		return pngPath;
	}
	public void setPngPath(String pngPath) {
		this.pngPath = pngPath;
	}
	public ArrayList<String> getDebuff() {
		return debuff;
	}
	public void setDebuff(ArrayList<String> debuff) {
		this.debuff = debuff;
	}
	public ArrayList<String> getDebuffInfo() {
		return debuffInfo;
	}
	public void setDebuffInfo(ArrayList<String> debuffInfo) {
		this.debuffInfo = debuffInfo;
	}
	public TankData getTankData() {
		return tankData;
	}
	public void setTankData(TankData tankData) {
		this.tankData = tankData;
	}
	public ArrayList<String> getDebuffAttribute() {
		return debuffAttribute;
	}
	public void setDebuffAttribute(ArrayList<String> debuffAttribute) {
		this.debuffAttribute = debuffAttribute;
	}
	
	private String terrainName;
	private int mixRange;
	private int maxRange;
	private ArrayList<String> debuff;
	private ArrayList<String> debuffInfo;
	private ArrayList<String> debuffAttribute;
	private String pngPath;
	private TankData tankData;

	
}
