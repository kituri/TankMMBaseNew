package com.kituri.tankmmdatabase.ui.tech;

import java.util.List;

import com.kituri.app.controller.EntryAdapter;
import com.kituri.app.push.PsPushUserData;
import com.kituri.app.ui.BaseFragmentActivity;
import com.kituri.tankmmdatabase.R;
import com.kituri.tankmmdatabase.controller.TechManager;
import com.kituri.tankmmdatabase.data.tech.TechDataBase;
import com.kituri.tankmmdatabase.data.tech.TechSearchData;
import com.kituri.tankmmdatabase.data.tech.TechTypeData;
import com.kituri.tankmmdatabase.model.Intent;
import com.kituri.tankmmdatabase.ui.metaphysics.MetaphysicsActivity;
import com.kituri.tankmmdatabase.widget.tech.ItemTypeShare;
import com.kituri.app.model.JsonModel;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

//分享页面
public class TechShareActivity extends BaseFragmentActivity implements OnClickListener {


	private TextView tv_name;
	private View ll_root;
	
	private EntryAdapter mAdapterMainGun;
	private EntryAdapter mAdapterShield;
	private EntryAdapter mAdapterBodywork;
	private EntryAdapter mAdapterEngine;
	private EntryAdapter mAdapterSeek;

	private Boolean isShare = false;
	private Boolean shareed = false;

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		setTitle(R.string.cap_tech_btn_share);
		setHomeAction(false);
		ll_root = findViewById(R.id.ll_root);
		tv_name = (TextView) findViewById(R.id.tv_name);
		GridView gv_main_gun = (GridView) findViewById(R.id.gv_main_gun);
		GridView gv_shield = (GridView) findViewById(R.id.gv_shield);
		GridView gv_bodywork = (GridView) findViewById(R.id.gv_bodywork);
		GridView gv_engine = (GridView) findViewById(R.id.gv_engine);
		GridView gv_seek = (GridView) findViewById(R.id.gv_seek);
		
		mAdapterMainGun = new EntryAdapter(this);
		mAdapterShield = new EntryAdapter(this);
		mAdapterBodywork = new EntryAdapter(this);
		mAdapterEngine = new EntryAdapter(this);
		mAdapterSeek = new EntryAdapter(this);
		
		gv_main_gun.setAdapter(mAdapterMainGun);
		gv_shield.setAdapter(mAdapterShield);
		gv_bodywork.setAdapter(mAdapterBodywork);
		gv_engine.setAdapter(mAdapterEngine);
		gv_seek.setAdapter(mAdapterSeek);
		
		initData();
		ll_root.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if(!TechShareActivity.this.isFinishing()){
					if(isShare){
						share();
					}	
				}
			}
		}, 200);
	
	}
	
	@Override
	public void onResume(){
		super.onResume();
		if(shareed){
			Toast.makeText(this, getString(R.string.msg_share_success), Toast.LENGTH_SHORT).show();
			finish();
		}
	}
	
	private void share(){
		TechManager.share(this, ll_root);
		shareed = true;
		//finish();
	}

	private void initData(){
		String title = String.format(getString(R.string.msg_tech_btn_share_title),
				PsPushUserData.getData(this, MetaphysicsActivity.METAPHYSICS_NAME, 
						getString(R.string.app_name)));
		tv_name.setText(title);
		//添加主炮
		List<TechTypeData> mainGunList = TechManager.getTechTypeListEntryForList(this, 
				new TechSearchData(0, TechDataBase.TYPE.ALL[0],
						TechDataBase.TYPE.ALL_STRING[0]), true);
		mAdapterMainGun.clear();
		for(TechTypeData data : mainGunList){
			data.setViewName(ItemTypeShare.class.getName());
			mAdapterMainGun.add(data);
		}
		//添加防护
		List<TechTypeData> mainShield = TechManager.getTechTypeListEntryForList(this, 
				new TechSearchData(0, TechDataBase.TYPE.ALL[1],
						TechDataBase.TYPE.ALL_STRING[1]), true);
		mAdapterShield.clear();
		for(TechTypeData data : mainShield){
			data.setViewName(ItemTypeShare.class.getName());
			mAdapterShield.add(data);
		}
		//添加车身
		List<TechTypeData> mainBodywork = TechManager.getTechTypeListEntryForList(this, 
				new TechSearchData(0, TechDataBase.TYPE.ALL[2],
						TechDataBase.TYPE.ALL_STRING[2]), true);
		mAdapterBodywork.clear();
		for(TechTypeData data : mainBodywork){
			data.setViewName(ItemTypeShare.class.getName());
			mAdapterBodywork.add(data);
		}
		//添加引擎
		List<TechTypeData> mainEngine = TechManager.getTechTypeListEntryForList(this, 
				new TechSearchData(0, TechDataBase.TYPE.ALL[3],
						TechDataBase.TYPE.ALL_STRING[3]), true);
		mAdapterEngine.clear();
		for(TechTypeData data : mainEngine){
			data.setViewName(ItemTypeShare.class.getName());
			mAdapterEngine.add(data);
		}
		//添加索敌
		List<TechTypeData> mainSeek = TechManager.getTechTypeListEntryForList(this, 
				new TechSearchData(0, TechDataBase.TYPE.ALL[4],
						TechDataBase.TYPE.ALL_STRING[4]), true);
		mAdapterSeek.clear();
		for(TechTypeData data : mainSeek){
			data.setViewName(ItemTypeShare.class.getName());
			mAdapterSeek.add(data);
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.tv_share:
			TechManager.share(this);
			break;
		default:
			break;
		}
	}

	@Override
	public int getLayoutID() {
		// TODO Auto-generated method stub
		return R.layout.activity_tech_share;
	}

	@Override
	public void initDataBundle(Bundle bundle) {
		// TODO Auto-generated method stub
		isShare = (Boolean) bundle.getSerializable(Intent.EXTRA_BOOLEAN_IS_SHARE);
	}

	@Override
	protected JsonModel initJsonModel() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
