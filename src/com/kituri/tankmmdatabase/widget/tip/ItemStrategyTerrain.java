package com.kituri.tankmmdatabase.widget.tip;

import com.kituri.app.controller.EntryAdapter;
import com.kituri.app.data.Entry;
import com.kituri.app.widget.Populatable;
import com.kituri.app.widget.Selectable;
import com.kituri.app.widget.SelectionListener;
import com.kituri.tankmmdatabase.R;
import com.kituri.tankmmdatabase.data.tank.TankDataBase;
import com.kituri.tankmmdatabase.data.tank.TankSearchFilterItemData;
import com.kituri.tankmmdatabase.data.tip.StrategyTerrainData;
import com.kituri.tankmmdatabase.data.tip.TerrainData;
import com.kituri.tankmmdatabase.model.Intent;
import com.kituri.tankmmdatabase.utils.TransformerUtils;
import com.kituri.tankmmdatabase.utils.Utils;
import com.kituri.tankmmdatabase.widget.tank.ItemFilterTank;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class ItemStrategyTerrain extends RelativeLayout implements Populatable<Entry>, Selectable<Entry>, SelectionListener<Entry> {
	
	private StrategyTerrainData mData;
	private SelectionListener<Entry> mListener;

	private TextView tv_stage;
	private GridView gv_terrain;
	private EntryAdapter mAdapter;
	
	public ItemStrategyTerrain(Context context) {
		this(context, null);
	}

	public ItemStrategyTerrain(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public ItemStrategyTerrain(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView();
	}
	
	private void initView() {
		if (isInEditMode()) {
			return;
		}
		View convertView = LayoutInflater.from(this.getContext()).
				inflate(R.layout.item_strategy_terrain, null);
		tv_stage = (TextView) convertView.findViewById(R.id.tv_stage);
		gv_terrain = (GridView) convertView.findViewById(R.id.gv_terrain);
		mAdapter = new EntryAdapter(getContext());
		
		this.addView(convertView, new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));		
		gv_terrain.setAdapter(mAdapter);
		mAdapter.setSelectionListener(this);
	}
	
	private void setData(StrategyTerrainData data) {
		//iv_equip.set
		tv_stage.setText(data.getStageName());
		setAdapter(gv_terrain, mAdapter);
//		Utils.setAssetsImage(getContext(), ((TerrainData)data.getEntries().get(0)).getPngPath(), iv_terrain01);

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
		mData = (StrategyTerrainData) data;
		setData(mData);
	}
	
	private void setAdapter(GridView gv, EntryAdapter adapter){
		
		if (gv != null) {
			int size = mData.getEntries().size();// 加的1是那个“全部”
			int length = 180;
			float density = 1;
			int gridviewWidth = (int) (size * (length) * density);
			int itemWidth = (int) (length * density);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(gridviewWidth,
					LinearLayout.LayoutParams.MATCH_PARENT);
			gv.setLayoutParams(params); // 设置GirdView布局参数,横向布局的关键
			gv.setColumnWidth(itemWidth); // 设置列表项宽
			// gv.setHorizontalSpacing(5); // 设置列表项水平间距
			gv.setStretchMode(GridView.NO_STRETCH);
			gv.setNumColumns(size); // 设置列数量=列表集合数
		} 
		

		adapter.clear();		
		for(Entry entry : mData.getEntries()){
			entry.setViewName(ItemTerrain.class.getName());
			adapter.add(entry);
		}
	}

	@Override
	public void onSelectionChanged(Entry item, boolean selected) {
		// TODO Auto-generated method stub
		if(mListener == null){
			return;
		}
		mListener.onSelectionChanged(item, true);	
	}
	
	
	
}
