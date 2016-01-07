package com.kituri.tankmmdatabase.data.comparator;

import java.util.Comparator;

import com.kituri.tankmmdatabase.data.tank.TankData;
import com.kituri.tankmmdatabase.data.tank.TankDataBase;
import com.kituri.tankmmdatabase.data.tank.TankSearchFilterData;


public class ComparatorStatistic implements Comparator<TankData> {
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
	private TankSearchFilterData mData;

	public ComparatorStatistic(TankSearchFilterData tankSearchData){
		this.mData = tankSearchData;
	}
	
	@Override
	public int compare(TankData tank01, TankData tank02) {
		// TODO Auto-generated method stub
		//entry01.entryCompare();
		long m1 = 0;
		long m2 = 0;
		
		switch (mData.getStatistic()) {
		case TankDataBase.TANK_STATISTIC.FIRE:
			m1 = tank01.getTankFire();
			m2 = tank02.getTankFire();
			break;
		case TankDataBase.TANK_STATISTIC.ARMOUR_PIERCING:
			m1 = tank01.getTankArmourPiercing();
			m2 = tank02.getTankArmourPiercing();
			break;
		case TankDataBase.TANK_STATISTIC.HIT:
			m1 = tank01.getTankHit();
			m2 = tank02.getTankHit();
			break;
		case TankDataBase.TANK_STATISTIC.DURABLE:
			m1 = tank01.getTankDurable();
			m2 = tank02.getTankDurable();
			break;
		case TankDataBase.TANK_STATISTIC.ARMOUR:
			m1 = tank01.getTankArmour();
			m2 = tank02.getTankArmour();
			break;
		case TankDataBase.TANK_STATISTIC.DODGE:
			m1 = tank01.getTankDodge();
			m2 = tank02.getTankDodge();
			break;
		case TankDataBase.TANK_STATISTIC.HIDE:
			m1 = tank01.getTankHide();
			m2 = tank02.getTankHide();
			break;
		case TankDataBase.TANK_STATISTIC.SPOT:
			m1 = tank01.getTankSpot();
			m2 = tank02.getTankSpot();
			break;
		}
		int result = 0;
		if (m1 < m2) {
			result = 1;
		}
		if (m1 > m2) {
			result = -1;
		}
		return result;
	}

}
