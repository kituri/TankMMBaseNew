package com.kituri.tankmmdatabase.data.tank;


public class TankSearchResult extends TankSearchData{
	

	
	public TankSearchResult(TankSearchData data) {
		super(data);
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = -8920190169267861122L;

	public int getIconResId() {
		return iconResId;
	}
	public void setIconResId(int iconResId) {
		this.iconResId = iconResId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	private int iconResId;
	private String name;

}
