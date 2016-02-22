package com.kituri.tankmmdatabase.ui.tank;

import java.util.List;

import com.kituri.app.controller.EntryAdapter;
import com.kituri.app.data.Entry;
import com.kituri.app.data.ListEntry;
import com.kituri.app.ui.BaseFragmentActivity;
import com.kituri.app.widget.SelectionListener;
import com.kituri.app.widget.dialog.CustomDialog;
import com.kituri.tankmmdatabase.KituriTankMMApplication;
import com.kituri.tankmmdatabase.R;
import com.kituri.tankmmdatabase.controller.BodyWorkManager;
import com.kituri.tankmmdatabase.controller.BulletManager;
import com.kituri.tankmmdatabase.controller.EngineManager;
import com.kituri.tankmmdatabase.controller.ShieldManager;
import com.kituri.tankmmdatabase.controller.TankManager;
import com.kituri.tankmmdatabase.data.equip.EquipData;
import com.kituri.tankmmdatabase.data.tank.TankData;
import com.kituri.tankmmdatabase.data.tank.TankDataBase;
import com.kituri.tankmmdatabase.data.tank.TankStatisticData;
import com.kituri.tankmmdatabase.data.tech.BodyWorkTechData;
import com.kituri.tankmmdatabase.data.tech.EngineTechData;
import com.kituri.tankmmdatabase.data.tech.TechData;
import com.kituri.tankmmdatabase.data.tech.TechSpecialData;
import com.kituri.tankmmdatabase.model.Intent;
import com.kituri.tankmmdatabase.utils.TransformerUtils;
import com.kituri.tankmmdatabase.utils.Utils;
import com.kituri.tankmmdatabase.widget.dialog.DialogTechFilter;
import com.kituri.tankmmdatabase.widget.tank.ItemEquipmentSlot;
import com.kituri.tankmmdatabase.widget.tank.ItemTankAstatistic;
import com.kituri.app.model.JsonModel;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class TankDetailActivity extends BaseFragmentActivity implements SelectionListener<Entry>, OnClickListener {

	private TankData mTankData;
	
	private ImageView iv_nationality;
	private ImageView iv_type;
	private TextView tv_name;
	private TextView tv_class;
	private GridView gv_statistics;
	private TextView tv_acqierement;
	private GridView gv_equipmentslots;
	private TextView tv_engine;
	private TextView tv_bodywork;
	private ImageView iv_head;
	private TextView tv_drop;
	
	private View rl_engine_check;
	private View rl_shield_check;
	private View rl_bodywork_check;
	private View rl_maingun_check;
	private View rl_terrain_check;
	private View rl_rank_max_mode;
	
	
	private ImageView iv_shield_check;
	
	private View ll_other_detail_01;
	private TextView tv_profiles;
	private ImageView iv_live2d;
	
	private EntryAdapter mAdapter;
	private EntryAdapter mSlotsAdapter;
	
	private CustomDialog mTechFilter;
	private DialogTechFilter mDialogTechFilter;

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		setTitle(R.string.cap_tank_detail_title);
		iv_nationality = (ImageView) findViewById(R.id.iv_nationality);
		iv_type = (ImageView) findViewById(R.id.iv_type);
		tv_name = (TextView) findViewById(R.id.tv_name);
		tv_class = (TextView) findViewById(R.id.tv_class);
		tv_acqierement = (TextView) findViewById(R.id.tv_acqierement);
		gv_equipmentslots = (GridView) findViewById(R.id.gv_equipmentslots);
		tv_engine = (TextView) findViewById(R.id.tv_engine);
		tv_bodywork = (TextView) findViewById(R.id.tv_bodywork);		
		rl_engine_check = findViewById(R.id.rl_engine_check);
		rl_shield_check = findViewById(R.id.rl_shield_check);
		rl_bodywork_check = findViewById(R.id.rl_bodywork_check);
		rl_maingun_check = findViewById(R.id.rl_maingun_check);
		rl_terrain_check = findViewById(R.id.rl_terrain_check);
		rl_rank_max_mode = findViewById(R.id.rl_rank_max_mode);
		iv_shield_check = (ImageView) findViewById(R.id.iv_shield_check);
		iv_head = (ImageView) findViewById(R.id.iv_head);
		tv_profiles = (TextView) findViewById(R.id.tv_profiles);
		ll_other_detail_01 = findViewById(R.id.ll_other_detail_01);
		iv_live2d = (ImageView) findViewById(R.id.iv_live2d);	
		findViewById(R.id.ll_btn_bar).setVisibility(View.VISIBLE);
		tv_drop = (TextView) findViewById(R.id.tv_drop);
		
		gv_statistics = (GridView) findViewById(R.id.gv_statistics);

		
		mDialogTechFilter = new DialogTechFilter(this);
		mTechFilter = new CustomDialog(this, mDialogTechFilter);
		
		mAdapter = new EntryAdapter(this);
		mSlotsAdapter = new EntryAdapter(this);
		
		gv_statistics.setVisibility(View.VISIBLE);
		gv_equipmentslots.setVisibility(View.VISIBLE);
		gv_statistics.setAdapter(mAdapter);
		gv_equipmentslots.setAdapter(mSlotsAdapter);
		
		mSlotsAdapter.setSelectionListener(this);
		rl_engine_check.setOnClickListener(this);
		rl_shield_check.setOnClickListener(this);
		rl_bodywork_check.setOnClickListener(this);
		rl_maingun_check.setOnClickListener(this);
		rl_terrain_check.setOnClickListener(this);
		rl_rank_max_mode.setOnClickListener(this);
		iv_live2d.setOnClickListener(this);
		tv_engine.setOnClickListener(this);
		tv_bodywork.setOnClickListener(this);
		mTechFilter.setSelectionListener(this);
		
		
		setData(mTankData);
	}

	private void setData(TankData data) {
		Utils.setAssetsImage(this, data.getHeadPic(), iv_head);
		List<TankStatisticData> statisticList = TankManager.getTankStatisticList(data);
		mAdapter.clear();
		for(TankStatisticData statisticData : statisticList){
			statisticData.setViewName(ItemTankAstatistic.class.getName());
			mAdapter.add(statisticData);
		}
		iv_nationality.setImageResource(TransformerUtils.getTankNationalityIconResId(this, data));
		iv_type.setImageResource(TransformerUtils.getTankTypeIconResId(this, data));
		tv_name.setText(data.getTankName());
		tv_class.setText(data.getTankClass());	
		tv_acqierement.setText(String.format(getString(R.string.cap_tank_statistics_acqierement) ,
				data.getTankAcqierement()));
//		tv_equipmentslots.setText(String.format(getString(R.string.cap_tank_statistics_tankequipmentslots) ,
//				data.getTankEquipmentSlots()));
		setEquipmentSlots(data);
		
		switch (data.getTankType()) {
		case TankDataBase.TANK_TYPE.HT:
		case TankDataBase.TANK_TYPE.TD:
			iv_shield_check.setImageResource(R.drawable.icon_tech_shield_type_02);
			break;
		case TankDataBase.TANK_TYPE.MT:
		case TankDataBase.TANK_TYPE.LT:
			iv_shield_check.setImageResource(R.drawable.icon_tech_shield_type_01);
			break;
		case TankDataBase.TANK_TYPE.SPG:
		case TankDataBase.TANK_TYPE.LTD:
			iv_shield_check.setImageResource(R.drawable.icon_tech_shield_type_03);
			break;
		default:
			iv_shield_check.setImageResource(R.drawable.icon_tech_shield_type_02);
			break;
		}
		
		ListEntry engines = EngineManager.conditionalQuery(data);
		if(engines.getEntries().size() > 0){
			EngineTechData engine = (EngineTechData) engines.getEntries().get(engines.getEntries().size() - 1);
			if(engine.getSpecialDatas() != null && engine.getSpecialDatas().size() > 0){
				StringBuffer sb = new StringBuffer();
				for(TechSpecialData specialData : engine.getSpecialDatas()){
					sb.append(String.format(getString(R.string.cap_tech_sp_name),
							specialData.getName()));
					//sb.append(specialData.getDescription() + "\n");
				}
				tv_engine.setText(sb.toString());
			}
		}
		
		ListEntry bodyworks = BodyWorkManager.conditionalQuery(data);
		if(bodyworks.getEntries().size() > 0){
			BodyWorkTechData bodyWork = (BodyWorkTechData) bodyworks.getEntries().get(bodyworks.getEntries().size() - 1);
			if(bodyWork.getSpecialDatas() != null && bodyWork.getSpecialDatas().size() > 0){
				StringBuffer sb = new StringBuffer();
				for(TechSpecialData specialData : bodyWork.getSpecialDatas()){
					sb.append(String.format(getString(R.string.cap_tech_sp_name),
							specialData.getName()));
					//sb.append(specialData.getDescription() + "\n");
				}
				tv_bodywork.setText(sb.toString());
			}
		}
		if(data.getTankStar() >= 3){
			rl_rank_max_mode.setVisibility(View.GONE);
		}
//		tv_engine.setText(String.format(getString(R.string.cap_tank_statistics_engine) ,
//				data.getTankEngine()));
//		
//		tv_bodywork.setText(String.format(getString(R.string.cap_tank_statistics_bodywork) ,
//				data.getTankBodywork()));
		if(TextUtils.isEmpty(data.getProfiles()) && TextUtils.isEmpty(data.getLive2d())){
			ll_other_detail_01.setVisibility(View.GONE);
		}else{
			ll_other_detail_01.setVisibility(View.VISIBLE);
			if(TextUtils.isEmpty(data.getAge())){
				tv_profiles.setText(data.getProfiles());
			}else{
				tv_profiles.setText(String.format(getString(R.string.cap_tank_age), data.getAge())
						+ "\n" + data.getProfiles());
			}
			
			if(TextUtils.isEmpty(data.getLive2d())){
				iv_live2d.setVisibility(View.GONE);
			}else{
				iv_live2d.setVisibility(View.VISIBLE);
			}
		}
		if(TextUtils.isEmpty(data.getDrop())){
			tv_drop.setVisibility(View.GONE);
		}else{
			tv_drop.setText(String.format(getString(R.string.cap_tank_drop), data.getDrop()));
		}
	}

	
	private void setEquipmentSlots(TankData data){
		mSlotsAdapter.clear();
		List<EquipData> list = TankManager.getEquipmentSlots(data);
		for(EquipData equip : list){
			equip.setViewName(ItemEquipmentSlot.class.getName());
			mSlotsAdapter.add(equip);
		}
	}


	@Override
	public void onSelectionChanged(Entry item, boolean selected) {
		// TODO Auto-generated method stub
		if(item == null){
			return;
		}
		String action = item.getIntent().getAction();
		if(action.equals(Intent.ACTION_EQUIP_DETAIL)){
//			EquipData data = (EquipData) item;
//			KituriTankMMApplication.gotoEquipList(this,
//					new EquipSearchData(data.getEquipType()));
		}else if(action.equals(Intent.ACTION_TECH_TYPE_DETAIL)){
			KituriTankMMApplication.gotoTechTypeDetail(this, (TechData) item);
		}else if(action.equals(Intent.ACTION_DIALOG_DISMISS)){
			mTechFilter.dismiss();
		}
	}
	
	public void onDestroy(){
		if(mSlotsAdapter != null){
			mSlotsAdapter.clear();
		}
		super.onDestroy();
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.rl_engine_check:
			ListEntry engines = EngineManager.conditionalQuery(mTankData);
			mDialogTechFilter.populate(engines);
			mTechFilter.show();
			break;
		case R.id.rl_bodywork_check:
			ListEntry bodyWorks = BodyWorkManager.conditionalQuery(mTankData);
			mDialogTechFilter.populate(bodyWorks);
			mTechFilter.show();
			break;
		case R.id.rl_shield_check:
			ListEntry shields = ShieldManager.conditionalQuery(mTankData);
			mDialogTechFilter.populate(shields);
			mTechFilter.show();
			break;
		case R.id.rl_maingun_check:
			ListEntry bullets = BulletManager.conditionalQuery(mTankData);
			mDialogTechFilter.populate(bullets);
			mTechFilter.show();
			break;
		case R.id.rl_rank_max_mode:
			mTankData.setTankStar(3);
			setData(mTankData);
			Toast.makeText(this, String.format(getString(R.string.msg_max_star_mode_on_detail),
					mTankData.getTankClass()), Toast.LENGTH_SHORT).show();
			break;
		case R.id.iv_live2d:
			KituriTankMMApplication.gotoLive2D(this, mTankData);
			break;
		case R.id.tv_engine:
		case R.id.tv_bodywork:
		case R.id.rl_terrain_check:
			KituriTankMMApplication.gotoTerrain(this, mTankData);
			break;
		default:
			break;
		}
	}


	@Override
	public int getLayoutID() {
		// TODO Auto-generated method stub
		return R.layout.activity_tank_detail;
	}


	@Override
	public void initDataBundle(Bundle bundle) {
		// TODO Auto-generated method stub
		mTankData = (TankData) ((TankData) bundle.getSerializable(Intent.EXTRA_TANK_DATA)).clone();

	}


	@Override
	protected JsonModel initJsonModel() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
