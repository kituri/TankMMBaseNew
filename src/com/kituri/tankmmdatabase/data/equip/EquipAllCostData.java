package com.kituri.tankmmdatabase.data.equip;

import com.kituri.app.data.Entry;
/**
 * 某个级别装备的总开销类
 */
public class EquipAllCostData extends Entry{

	private static final long serialVersionUID = -1584553380358777891L;
	
	public int getS1Counts() {
		return s1Counts;
	}

	public void setS1Counts(int s1Counts) {
		this.s1Counts = s1Counts;
	}

	public int getN1Maps() {
		return n1Maps;
	}

	public void setN1Maps(int n1Maps) {
		this.n1Maps = n1Maps;
	}

	public int getN2Maps() {
		return n2Maps;
	}

	public void setN2Maps(int n2Maps) {
		this.n2Maps = n2Maps;
	}

	public int getN3Maps() {
		return n3Maps;
	}

	public void setN3Maps(int n3Maps) {
		this.n3Maps = n3Maps;
	}

	public int getN4Maps() {
		return n4Maps;
	}

	public void setN4Maps(int n4Maps) {
		this.n4Maps = n4Maps;
	}

	public int getCostPoint() {
		return costPoint;
	}

	public void setCostPoint(int costPoint) {
		this.costPoint = costPoint;
	}

	public int getS1CountsSP() {
		return s1CountsSP;
	}

	public void setS1CountsSP(int s1CountsSP) {
		this.s1CountsSP = s1CountsSP;
	}
	
	public int getS1CountsNor() {
		return s1CountsNor;
	}

	public void setS1CountsNor(int s1CountsNor) {
		this.s1CountsNor = s1CountsNor;
	}
	
	public int getCostPointNor() {
		return costPointNor;
	}

	public void setCostPointNor(int costPointNor) {
		this.costPointNor = costPointNor;
	}

	public int getCostPointSP() {
		return costPointSP;
	}

	public void setCostPointSP(int costPointSP) {
		this.costPointSP = costPointSP;
	}
	
	public int[] getsCountsArray() {
		return sCountsArray;
	}

	public void setsCountsArray(int[] sCountsArray) {
		this.sCountsArray = sCountsArray;
	}
	
	public int[] getsCountsArraySP() {
		return sCountsArraySP;
	}

	public void setsCountsArraySP(int[] sCountsArraySP) {
		this.sCountsArraySP = sCountsArraySP;
	}

	public int[] getsCountsArrayNor() {
		return sCountsArrayNor;
	}

	public void setsCountsArrayNor(int[] sCountsArrayNor) {
		this.sCountsArrayNor = sCountsArrayNor;
	}
	
	public Boolean getIsSP() {
		return isSP;
	}

	public void setIsSP(Boolean isSP) {
		this.isSP = isSP;
	}

	public int getLv() {
		return lv;
	}

	public void setLv(int lv) {
		this.lv = lv;
	}
	
	public int getMakeCount() {
		return makeCount;
	}

	public void setMakeCount(int makeCount) {
		this.makeCount = makeCount;
	}
	
	//制作该装备的数量
	private int makeCount;

	//S1装备数量消耗
	private int s1Counts;
	private int s1CountsSP;
	private int s1CountsNor;


	//图纸消耗
	private int n1Maps;
	private int n2Maps;
	private int n3Maps;
	private int n4Maps;
	
	//密码子
	private int costPoint;
	private int costPointNor;
	private int costPointSP;
	
	//各个等级的消耗个数 index = 等级
	private int[] sCountsArray;
	private int[] sCountsArraySP;
	private int[] sCountsArrayNor;


	//是否带有特殊合成
	private Boolean isSP;
	//合成的级别
	private int lv;
}
