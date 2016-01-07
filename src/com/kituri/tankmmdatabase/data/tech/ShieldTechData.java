package com.kituri.tankmmdatabase.data.tech;

import java.util.ArrayList;

/**
 * 防护科技数据
 */
public class ShieldTechData extends TechData{

	private static final long serialVersionUID = 3672726097211956879L;
	
	//能从钢舞姬的表中匹配到防护表中的tag
	private ArrayList<String> tags;

	public ArrayList<String> getTags() {
		return tags;
	}

	public void setTag(ArrayList<String> tags) {
		this.tags = tags;
	}

}
