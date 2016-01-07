package com.kituri.tankmmdatabase.data.comparator;

import java.util.Comparator;

import com.kituri.tankmmdatabase.data.tank.TankData;
import com.kituri.tankmmdatabase.data.tank.TankDataBase;
import com.kituri.tankmmdatabase.data.tank.TankSearchFilterData;


public class ComparatorTankDefault implements Comparator<TankData> {
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
	public int compare(TankData tank01, TankData tank02) {
		// TODO Auto-generated method stub
		//entry01.entryCompare();
		long m1 = tank01.getTankStar();
		long m2 = tank02.getTankStar();
		
		int result = 0;
		if (m1 < m2) {
			result = 1;
		}
		if (m1 > m2) {
			result = -1;
		}
		if(m1 == m2){
			if(tank01.getTankType() < tank02.getTankType()){
				result = -1;
			}
			if(tank01.getTankType() > tank02.getTankType()){
				result = 1;
			}
		}
		return result;
	}

}
