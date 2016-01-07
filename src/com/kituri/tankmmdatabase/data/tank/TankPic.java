package com.kituri.tankmmdatabase.data.tank;

import com.kituri.app.data.Entry;

/**
 * 钢舞姬图片数据
 */
public class TankPic extends Entry{

	/**
	 * 
	 */
	private static final long serialVersionUID = -721682370625582863L;

	public String getHeadPicPath() {
		return headPicPath;
	}

	public void setHeadPicPath(String headPicPath) {
		this.headPicPath = headPicPath;
	}

	public String getHeadPicTag() {
		return headPicTag;
	}

	public void setHeadPicTag(String headPicTag) {
		this.headPicTag = headPicTag;
	}

	private String headPicPath;
	
	private String headPicTag;
	
}
