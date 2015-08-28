package com.kituri.tankmmdatabase.ui.tank;

import java.util.List;

import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.kituri.app.controller.EntryAdapter;
import com.kituri.tankmmdatabase.R;
import com.kituri.tankmmdatabase.controller.TankManager;
import com.kituri.tankmmdatabase.data.tank.TankData;
import com.kituri.tankmmdatabase.data.tank.TankStatisticData;
import com.kituri.tankmmdatabase.model.Intent;
import com.kituri.tankmmdatabase.ui.common.BaseActivity;
import com.kituri.tankmmdatabase.utils.TransformerUtils;
import com.kituri.tankmmdatabase.widget.tank.ItemTankAstatistic;

public class TankDetailActivity extends BaseActivity {

	private TankData mTankData;
	
	private ImageView iv_nationality;
	private ImageView iv_type;
	private TextView tv_name;
	private TextView tv_class;
	private GridView gv_statistics;
	private TextView tv_acqierement;
	private TextView tv_equipmentslots;
	private TextView tv_engine;
	private TextView tv_bodywork;
	
	private EntryAdapter mAdapter;
	
	public TankDetailActivity() {
		super(R.layout.activity_tank_detail);
		// TODO Auto-generated constructor stub
	}


	@Override
	protected void getData() {
		// TODO Auto-generated method stub
		mTankData = (TankData) getIntent().getSerializableExtra(Intent.EXTRA_TANK_DATA);
	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		setTitle(R.string.cap_tank_detail_title);
		iv_nationality = (ImageView) findViewById(R.id.iv_nationality);
		iv_type = (ImageView) findViewById(R.id.iv_type);
		tv_name = (TextView) findViewById(R.id.tv_name);
		tv_class = (TextView) findViewById(R.id.tv_class);
		tv_acqierement = (TextView) findViewById(R.id.tv_acqierement);
		tv_equipmentslots = (TextView) findViewById(R.id.tv_equipmentslots);
		tv_engine = (TextView) findViewById(R.id.tv_engine);
		tv_bodywork = (TextView) findViewById(R.id.tv_bodywork);		
		gv_statistics = (GridView) findViewById(R.id.gv_statistics);
		mAdapter = new EntryAdapter(this);
		gv_statistics.setAdapter(mAdapter);
		setData(mTankData);
	}

	private void setData(TankData data) {
		List<TankStatisticData> statisticList = TankManager.getTankStatisticList(data);
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
		tv_equipmentslots.setText(String.format(getString(R.string.cap_tank_statistics_tankequipmentslots) ,
				data.getTankEquipmentSlots()));
		tv_engine.setText(String.format(getString(R.string.cap_tank_statistics_engine) ,
				data.getTankEngine()));
		tv_bodywork.setText(String.format(getString(R.string.cap_tank_statistics_bodywork) ,
				data.getTankBodywork()));
	}
	
}
