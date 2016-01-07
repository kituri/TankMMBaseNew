package com.kituri.tankmmdatabase.data.comparator;

import java.util.Comparator;

import com.kituri.tankmmdatabase.data.DataBaseCategoryData;

public class ComparatorCategory implements Comparator<DataBaseCategoryData> {
	// public int compare(Object arg0, Object arg1) {
	// User user0=(User)arg0;
	// User user1=(User)arg1;
	//
	// //首先比较年龄，如果年龄相同，则比较名字
	//
	// int flag=user0.getAge().compareTo(user1.getAge());
	// if(flag==0){
	// return user0.getName().compareTo(user1.getName());
	// }else{
	// return flag;
	// }
	// }

	@Override
	public int compare(DataBaseCategoryData data01, DataBaseCategoryData data02) {
		// TODO Auto-generated method stub
		int result = 0;
		if (data01.getEnabled() && data02.getEnabled()) {
			return result;
		}
		if (!data01.getEnabled() && data02.getEnabled()) {
			result = 1;
		}
		if (data01.getEnabled() && !data02.getEnabled()) {
			result = -1;
		}
		return result;
	}

}
