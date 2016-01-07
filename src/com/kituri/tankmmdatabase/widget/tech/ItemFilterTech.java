package com.kituri.tankmmdatabase.widget.tech;

import com.kituri.app.controller.EntryAdapter;
import com.kituri.app.data.Entry;
import com.kituri.app.widget.Populatable;
import com.kituri.app.widget.Selectable;
import com.kituri.app.widget.SelectionListener;
import com.kituri.tankmmdatabase.R;
import com.kituri.tankmmdatabase.data.tank.TankStatisticData;
import com.kituri.tankmmdatabase.data.tech.TechData;
import com.kituri.tankmmdatabase.data.tech.TechSpecialData;
import com.kituri.tankmmdatabase.model.Intent;
import com.kituri.tankmmdatabase.utils.Utils;
import com.kituri.tankmmdatabase.widget.tank.ItemTankAstatistic;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ItemFilterTech extends RelativeLayout implements Selectable<Entry>, View.OnClickListener, Populatable<TechData>, SelectionListener<Entry>{

	private SelectionListener<Entry> mListener;
	
	private GridView gv_statistics;
	private TextView tv_name;
	private TextView tv_special;
	private ImageView iv_tech_type;
	private ImageView iv_tech;
	private ImageView iv_rank_lv;
	private EntryAdapter mAdapter;
	
	private TechData mData;
	
	public ItemFilterTech(Context context){
		this(context,null);
	}
	
	public ItemFilterTech(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	private void initView() {
		View view = LayoutInflater.from(getContext()).inflate(R.layout.item_tech_filter, null);
		gv_statistics = (GridView) view.findViewById(R.id.gv_statistics);
		tv_name = (TextView) view.findViewById(R.id.tv_name);
		tv_special = (TextView) view.findViewById(R.id.tv_special);
		iv_tech_type = (ImageView) view.findViewById(R.id.iv_tech_type);
		iv_rank_lv = (ImageView) view.findViewById(R.id.iv_rank_lv);
		iv_tech = (ImageView) view.findViewById(R.id.iv_tech);
		mAdapter = new EntryAdapter(getContext());
		gv_statistics.setAdapter(mAdapter);
		mAdapter.setSelectionListener(this);
		setOnClickListener(this);
		//lv_statistics.setOnClickListener(this);
		//view.findViewById(R.id.btn_ok).setOnClickListener(this);
		this.addView(view);
	}
	
	private void setData(TechData data){
		mAdapter.clear();
		if(data.getStatisticDatas() != null && data.getStatisticDatas().size() > 0){
			for(TankStatisticData statisticData : data.getStatisticDatas()){
				statisticData.setViewName(ItemTankAstatistic.class.getName());
				mAdapter.add(statisticData);
			}
		}
		tv_name.setText(data.getTechName());
		if(data.getSpecialDatas() != null && data.getSpecialDatas().size() > 0){
			StringBuffer sb = new StringBuffer();
			for(TechSpecialData specialData : data.getSpecialDatas()){
				sb.append(String.format(getContext().getString(R.string.cap_tech_sp_name),
						specialData.getName()));
				//sb.append(specialData.getDescription() + "\n");
			}
			tv_special.setText(sb.toString());
		}
		if(TextUtils.isEmpty(data.getTechIcon())){
			iv_tech.setImageResource(getContext().getResources().getIdentifier(data.getIcon(),
					"drawable", getContext().getPackageName()));
		}else{
			Utils.setAssetsImage(getContext(), data.getTechIcon(), iv_tech);
		}

		switch (data.getRank()) {
		case 1:
			iv_rank_lv.setImageResource(R.drawable.icon_rank_lv_01);
			break;
		case 2:
			iv_rank_lv.setImageResource(R.drawable.icon_rank_lv_02);
			break;
		case 3:
			iv_rank_lv.setImageResource(R.drawable.icon_rank_lv_03);
			break;
		default:
			iv_rank_lv.setImageResource(R.drawable.icon_rank_lv_01);
			break;
		}
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(mListener != null){
			mData.setIntent(new Intent(Intent.ACTION_TECH_TYPE_DETAIL));
			mListener.onSelectionChanged(mData, true);
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
	public void populate(TechData data) {
		// TODO Auto-generated method stub
		if(data != null){
			mData = data;
			setData(mData);
		}
	}

	@Override
	public void onSelectionChanged(Entry item, boolean selected) {
		// TODO Auto-generated method stub
		if(mListener != null){
			mData.setIntent(new Intent(Intent.ACTION_TECH_TYPE_DETAIL));
			mListener.onSelectionChanged(mData, true);
		}
	}
	
}
