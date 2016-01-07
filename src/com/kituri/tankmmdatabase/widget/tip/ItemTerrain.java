package com.kituri.tankmmdatabase.widget.tip;

import java.util.List;

import com.kituri.app.controller.EntryAdapter;
import com.kituri.app.data.Entry;
import com.kituri.app.widget.Populatable;
import com.kituri.app.widget.Selectable;
import com.kituri.app.widget.SelectionListener;
import com.kituri.tankmmdatabase.R;
import com.kituri.tankmmdatabase.controller.TankManager;
import com.kituri.tankmmdatabase.controller.TipManager;
import com.kituri.tankmmdatabase.data.tank.TankStatisticData;
import com.kituri.tankmmdatabase.data.tip.TerrainData;
import com.kituri.tankmmdatabase.model.Intent;
import com.kituri.tankmmdatabase.utils.Utils;
import com.kituri.tankmmdatabase.widget.tank.ItemTankAstatistic;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;


public class ItemTerrain extends RelativeLayout implements Populatable<Entry>, Selectable<Entry>, View.OnClickListener {
	
	private TerrainData mData;
	private SelectionListener<Entry> mListener;

	private ImageView iv_terrain;
	private GridView gv_statistics;
	
	private EntryAdapter mAdapter;
	
	public ItemTerrain(Context context) {
		this(context, null);
	}

	public ItemTerrain(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public ItemTerrain(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView();
	}
	
	private void initView() {
		if (isInEditMode()) {
			return;
		}
		View convertView = LayoutInflater.from(this.getContext()).
				inflate(R.layout.item_terrain, null);
		iv_terrain = (ImageView) convertView.findViewById(R.id.iv_terrain);
		gv_statistics = (GridView) convertView.findViewById(R.id.gv_statistics);
		mAdapter = new EntryAdapter(getContext());
		gv_statistics.setAdapter(mAdapter);
		this.addView(convertView, new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT));
		setOnClickListener(null);
		iv_terrain.setOnClickListener(this);
	}
	
	private void setData(TerrainData data) {
		//iv_equip.set
		Utils.setAssetsImage(getContext(), data.getPngPath(), iv_terrain);
		mAdapter.clear();
		if(data.getTankData() != null){
			List<TankStatisticData> statisticList = TipManager.getTerrainStatisticList(data);
			for(TankStatisticData statisticData : statisticList){
				statisticData.setViewName(ItemTankAstatistic.class.getName());
				mAdapter.add(statisticData);
			}
		}
	}
	
	@Override
	public void setSelectable(boolean selectable) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setXSelected(boolean selected) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setSelectionListener(SelectionListener<Entry> mListener) {
		// TODO Auto-generated method stub
		this.mListener = mListener;
	}

	@Override
	public void populate(Entry data) {
		// TODO Auto-generated method stub
		if (data == null) {
			return;
		}
		mData = (TerrainData) data;
		setData(mData);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(mListener == null){
			return;
		}		
		mData.setIntent(new Intent(Intent.ACTION_TERRAIN_DETAIL));
		mListener.onSelectionChanged(mData, true);		
	}	
}
