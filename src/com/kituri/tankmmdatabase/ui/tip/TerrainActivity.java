package com.kituri.tankmmdatabase.ui.tip;

import java.util.ArrayList;

import com.kituri.app.controller.EntryAdapter;
import com.kituri.app.data.Entry;
import com.kituri.app.data.ListEntry;
import com.kituri.app.widget.SelectionListener;
import com.kituri.app.widget.dialog.CustomDialog;
import com.kituri.tankmmdatabase.KituriTankMMApplication;
import com.kituri.tankmmdatabase.R;
import com.kituri.tankmmdatabase.data.tank.TankData;
import com.kituri.tankmmdatabase.data.tip.TerrainData;
import com.kituri.tankmmdatabase.model.Intent;
import com.kituri.tankmmdatabase.ui.common.BaseActivity;
import com.kituri.tankmmdatabase.widget.dialog.DialogTerrainDetail;
import com.kituri.tankmmdatabase.widget.tip.ItemStrategyTerrain;
import com.kituri.tankmmdatabase.widget.tip.ItemTerrain;

import android.view.View;
import android.widget.GridView;
import android.widget.ListView;

public class TerrainActivity extends BaseActivity implements SelectionListener<Entry> {

	private GridView gv_terrain;
	private ListView lv_terrain;
	private EntryAdapter mGvAdapter;
	private EntryAdapter mLvAdapter;

	private CustomDialog mDetailDialog;
	private DialogTerrainDetail mDialogTerrain;
	private TankData mData;
	private ListEntry terrains;
	
	public TerrainActivity() {
		super(R.layout.activity_terrain);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void getData() {
		// TODO Auto-generated method stub
		mData = (TankData) getIntent().getSerializableExtra(Intent.EXTRA_TANK_DATA);
		terrains = (ListEntry) getIntent().getSerializableExtra(Intent.EXTRA_TERRAIN_LIST);
				//com.kituri.tankmmdatabase.model.Intent.EXTRA_TERRAIN_LIST
	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		if(mData == null){
			setTitle(R.string.cap_terrain);
		}else{
			setTitle(mData.getTankClass() + "  " +getString(R.string.cap_terrain_resistance));
		}
		
		setHomeAction(false);
		gv_terrain = (GridView) findViewById(R.id.gv_terrain);
		lv_terrain = (ListView) findViewById(R.id.lv_terrain);
		mGvAdapter = new EntryAdapter(this);
		mLvAdapter = new EntryAdapter(this);
		gv_terrain.setAdapter(mGvAdapter);
		lv_terrain.setAdapter(mLvAdapter);
		mDialogTerrain = new DialogTerrainDetail(this);
		mDetailDialog = new CustomDialog(this, mDialogTerrain);

		mDetailDialog.setSelectionListener(this);
		mGvAdapter.setSelectionListener(this);
		mLvAdapter.setSelectionListener(this);
		initData();
	}

	private void initData() {
		// TODO Auto-generated method stub
		mGvAdapter.clear();
		mLvAdapter.clear();
		if(terrains == null){
			lv_terrain.setVisibility(View.GONE);
			ArrayList<TerrainData> terrains = new ArrayList<TerrainData>();
			for (TerrainData data : KituriTankMMApplication.terrains) {
				terrains.add((TerrainData) data.clone());
			}
			for (TerrainData data : terrains) {
				data.setViewName(ItemTerrain.class.getName());	
				if(mData != null){
					data.setTankData(mData);	
				}				
				mGvAdapter.add(data);
			}
		}else{
			gv_terrain.setVisibility(View.GONE);
			for (Entry data : terrains.getEntries()) {
				data.setViewName(ItemStrategyTerrain.class.getName());					
				mLvAdapter.add(data);
			}
		}	

	}

	@Override
	public void onSelectionChanged(Entry item, boolean selected) {
		// TODO Auto-generated method stub
		if (item == null) {
			return;
		}
		String action = item.getIntent().getAction();
		if (action.equals(Intent.ACTION_TERRAIN_DETAIL)) {
			mDialogTerrain.populate((TerrainData) item);
			mDetailDialog.show();			
		} else if (action.equals(Intent.ACTION_DIALOG_DISMISS)) {
			mDetailDialog.dismiss();
		}
	}

}
