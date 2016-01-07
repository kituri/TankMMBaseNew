package com.kituri.tankmmdatabase.data.tip;

import com.kituri.app.data.Entry;
import com.kituri.app.data.ListEntry;

public class StrategyTerrainData extends ListEntry{

	/**
	 * 推图地形数据
	 */
	private static final long serialVersionUID = -1215415027556645035L;
	
	private String stageName;

	public String getStageName() {
		return stageName;
	}

	public void setStageName(String stageName) {
		this.stageName = stageName;
	}
	
}
