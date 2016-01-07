package com.kituri.tankmmdatabase.data.tip;

import com.kituri.app.data.Entry;

public class Tip extends Entry{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3470240220939589868L;

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTipName() {
		return tipName;
	}
	public void setTipName(String tipName) {
		this.tipName = tipName;
	}
	private String url;
	private String tipName;
	
}
