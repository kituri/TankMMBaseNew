package com.kituri.tankmmdatabase.ui.tip;

import java.util.ArrayList;

import com.kituri.app.controller.EntryAdapter;
import com.kituri.app.data.Entry;
import com.kituri.app.widget.SelectionListener;
import com.kituri.tankmmdatabase.KituriTankMMApplication;
import com.kituri.tankmmdatabase.R;
import com.kituri.tankmmdatabase.controller.TipManager;
import com.kituri.tankmmdatabase.data.tip.Tip;
import com.kituri.tankmmdatabase.ui.common.BaseActivity;
import com.kituri.tankmmdatabase.widget.tip.ItemTip;

import android.widget.ListView;

public class TipActivity extends BaseActivity implements SelectionListener<Entry>{

	private ListView lv_tips;
	private EntryAdapter mAdapter;
	
	public TipActivity() {
		super(R.layout.activity_tips);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void getData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		setTitle(R.string.cap_database_category_tips);
		setHomeAction(false);
		lv_tips = (ListView) findViewById(R.id.lv_tips);
		mAdapter = new EntryAdapter(this);
		lv_tips.setAdapter(mAdapter);
		
		mAdapter.setSelectionListener(this);
		initTips();
	}

	private void initTips() {
		// TODO Auto-generated method stub
		ArrayList<Tip> tips = TipManager.getTipsList(this);
		for(Tip tip : tips){
			tip.setViewName(ItemTip.class.getName());
			mAdapter.add(tip);
		}

	}

	@Override
	public void onSelectionChanged(Entry item, boolean selected) {
		// TODO Auto-generated method stub
		if(item == null){
			return;
		}
		String tipName = ((Tip)item).getTipName();
		if(tipName.equals(getString(R.string.cap_official_strategy_title))){
			//官方攻略
			KituriTankMMApplication.gotoOfficialStrategy(this);
		}else if(tipName.equals(getString(R.string.cap_terrain_strategy))){
			//地图攻略
			KituriTankMMApplication.gotoTerrainStrategy(this);
		}else if(tipName.equals(getString(R.string.cap_terrain_strategy_sp))){
			//T图攻略
			KituriTankMMApplication.gotoSpTerrainStrategy(this);
		}else if(tipName.equals(getString(R.string.cap_terrain))){
			//地形一览
			KituriTankMMApplication.gotoTerrain(this, null);
		}
	}

}
