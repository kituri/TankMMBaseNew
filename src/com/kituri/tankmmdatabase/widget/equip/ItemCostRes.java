package com.kituri.tankmmdatabase.widget.equip;

import com.kituri.app.data.Entry;
import com.kituri.app.widget.Populatable;
import com.kituri.app.widget.Selectable;
import com.kituri.app.widget.SelectionListener;
import com.kituri.tankmmdatabase.R;
import com.kituri.tankmmdatabase.data.equip.CostResData;
import com.kituri.tankmmdatabase.data.equip.EquipCountData;
import com.kituri.tankmmdatabase.model.Intent;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class ItemCostRes extends RelativeLayout implements Populatable<Entry>{
	
	private CostResData mData;

	private ImageView iv_icon;
	private TextView tv_count;	
	private ImageView iv_sp;
	
	public ItemCostRes(Context context) {
		this(context, null);
	}

	public ItemCostRes(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public ItemCostRes(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView();
	}
	
	private void initView() {
		if (isInEditMode()) {
			return;
		}
		View convertView = LayoutInflater.from(this.getContext()).
				inflate(R.layout.item_cost_res, null);
		tv_count =  (TextView) convertView.findViewById(R.id.tv_count);
		iv_icon = (ImageView) convertView.findViewById(R.id.iv_icon);
		iv_sp = (ImageView) convertView.findViewById(R.id.iv_sp);
		this.addView(convertView);
		setOnClickListener(null);
	}
	
	private void setData(CostResData data) {
		//iv_equip.set
		tv_count.setText(String.valueOf(data.getResCount()));
		iv_sp.setVisibility(View.GONE);
		switch (data.getResType()) {
		case CostResData.RES_TYPE_TECH_POINT:
			iv_icon.setImageResource(R.drawable.icon_res_tech_point);
			break;
		case CostResData.RES_TYPE_FILE_N01:
			iv_icon.setImageResource(R.drawable.icon_res_file_n01);
			break;
		case CostResData.RES_TYPE_FILE_N02:
			iv_icon.setImageResource(R.drawable.icon_res_file_n02);
			break;
		case CostResData.RES_TYPE_FILE_N03:
			iv_icon.setImageResource(R.drawable.icon_res_file_n03);
			break;
		case CostResData.RES_TYPE_FILE_N04:
			iv_icon.setImageResource(R.drawable.icon_res_file_n04);
			break;
		case CostResData.RES_TYPE_EQUIP_TYPE_01:
			iv_sp.setVisibility(View.VISIBLE);
			iv_sp.setImageResource(R.drawable.icon_equip_type_01);
			iv_icon.setImageResource(R.drawable.icon_equip_00);
			break;
		case CostResData.RES_TYPE_EQUIP_TYPE_07:
			iv_sp.setVisibility(View.VISIBLE);
			iv_sp.setImageResource(R.drawable.icon_equip_type_07);
			iv_icon.setImageResource(R.drawable.icon_equip_00);
			break;
		
				
		}
	}

	@Override
	public void populate(Entry data) {
		// TODO Auto-generated method stub
		if (data == null) {
			return;
		}
		mData = (CostResData) data;
		setData(mData);
	}
}
