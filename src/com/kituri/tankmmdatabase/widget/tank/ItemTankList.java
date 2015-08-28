package com.kituri.tankmmdatabase.widget.tank;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kituri.app.data.Entry;
import com.kituri.app.widget.Populatable;
import com.kituri.app.widget.Selectable;
import com.kituri.app.widget.SelectionListener;
import com.kituri.tankmmdatabase.R;
import com.kituri.tankmmdatabase.data.tank.TankData;
import com.kituri.tankmmdatabase.model.Intent;
import com.kituri.tankmmdatabase.utils.TransformerUtils;


public class ItemTankList extends RelativeLayout implements Populatable<Entry>, Selectable<Entry>, View.OnClickListener {
	
	private TankData mData;
	private SelectionListener<Entry> mListener;
	private View v_star_lv;
	private ImageView iv_type;
	private TextView tv_class;
	//private ImageView iv_star_lv01;
	private ImageView iv_star_lv02;
	private ImageView iv_star_lv03;

	
	public ItemTankList(Context context) {
		this(context, null);
	}

	public ItemTankList(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public ItemTankList(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView();
	}
	
	private void initView() {
		if (isInEditMode()) {
			return;
		}
		View convertView = LayoutInflater.from(this.getContext()).
				inflate(R.layout.item_tank_list, null);
		v_star_lv = convertView.findViewById(R.id.v_star_lv);
		iv_type = (ImageView) convertView.findViewById(R.id.iv_type);
		tv_class = (TextView) convertView.findViewById(R.id.tv_class);
		//iv_star_lv01 = (ImageView) convertView.findViewById(R.id.iv_star_lv01);
		iv_star_lv02 = (ImageView) convertView.findViewById(R.id.iv_star_lv02);
		iv_star_lv03 = (ImageView) convertView.findViewById(R.id.iv_star_lv03);
		
		this.addView(convertView, new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
		this.setOnClickListener(this);
	}
	
	private void setData(TankData obj) {
		tv_class.setText(obj.getTankClass());
		iv_type.setBackgroundResource(TransformerUtils.getTankTypeIconResId(getContext(), obj));
		switch (obj.getTankStar()) {
		case 1:
			v_star_lv.setBackgroundResource(R.drawable.bg_tank_star_level_01);
			iv_star_lv02.setVisibility(View.INVISIBLE);
			iv_star_lv03.setVisibility(View.INVISIBLE);			
			break;
		case 2:
			v_star_lv.setBackgroundResource(R.drawable.bg_tank_star_level_02);
			iv_star_lv02.setVisibility(View.VISIBLE);
			iv_star_lv03.setVisibility(View.INVISIBLE);	
			break;
		case 3:
			v_star_lv.setBackgroundResource(R.drawable.bg_tank_star_level_03);
			iv_star_lv02.setVisibility(View.VISIBLE);
			iv_star_lv03.setVisibility(View.VISIBLE);	
			break;
		default:
			v_star_lv.setBackgroundResource(R.drawable.bg_tank_star_level_01);
			iv_star_lv02.setVisibility(View.INVISIBLE);
			iv_star_lv03.setVisibility(View.INVISIBLE);	
			break;
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
		mData = (TankData) data;
		setData(mData);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(mListener == null){
			return;
		}		
		mData.setIntent(new Intent(Intent.ACTION_TANK_DETAIL));
		mListener.onSelectionChanged(mData, true);		
	}	
}
