package com.kituri.tankmmdatabase.data.tech;

/**
 * 引擎科技数据
 */
public class EngineTechData extends TechData{

	private static final long serialVersionUID = 3672726097211956879L;
	
	//能从钢舞姬的表中匹配到引擎表中的tag
	private String tag;

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	//
}
