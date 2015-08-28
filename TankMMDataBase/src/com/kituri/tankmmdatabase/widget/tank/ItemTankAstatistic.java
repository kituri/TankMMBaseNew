package com.kituri.tankmmdatabase.widget.tank;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kituri.app.data.Entry;
import com.kituri.app.widget.Populatable;
import com.kituri.app.widget.Selectable;
import com.kituri.app.widget.SelectionListener;
import com.kituri.tankmmdatabase.R;
import com.kituri.tankmmdatabase.data.DataBaseCategoryData;
import com.kituri.tankmmdatabase.data.tank.TankData;
import com.kituri.tankmmdatabase.data.tank.TankStatisticData;
import com.kituri.tankmmdatabase.model.Intent;


public class ItemTankAstatistic extends RelativeLayout implements Populatable<Entry>, Selectable<Entry>, View.OnClickListener {
	
	private TankStatisticData mData;
	private SelectionListener<Entry> mListener;
	private ImageView iv_statistic;
	private TextView tv_statistic;

	
	public ItemTankAstatistic(Context context) {
		this(context, null);
	}

	public ItemTankAstatistic(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public ItemTankAstatistic(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView();
	}
	
	private void initView() {
		if (isInEditMode()) {
			return;
		}
		View convertView = LayoutInflater.from(this.getContext()).
				inflate(R.layout.item_tank_statistic, null);
		iv_statistic = (ImageView) convertView.findViewById(R.id.iv_statistic);
		tv_statistic = (TextView) convertView.findViewById(R.id.tv_statistic);

		this.addView(convertView, new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
		this.setOnClickListener(this);
	}
	
	private void setData(TankStatisticData obj) {
		if(obj.getValue() >= 0){
			tv_statistic.setTextColor(Color.WHITE);
		}else{
			tv_statistic.setTextColor(Color.RED);
		}
		switch (obj.getStatistic()) {
		case TankStatisticData.STATISTIC_ARMOUR:
			tv_statistic.setText(String.format(getContext().getString(R.string.cap_tank_statistics_armour),
					obj.getValue()));
			iv_statistic.setImageResource(R.drawable.icon_tank_statistics_armour);
			break;
		case TankStatisticData.STATISTIC_ARMOURPIERCING:
			tv_statistic.setText(String.format(getContext().getString(R.string.cap_tank_statistics_armourpiercing),
					obj.getValue()));
			iv_statistic.setImageResource(R.drawable.icon_tank_statistics_armourpiercing);
			break;
		case TankStatisticData.STATISTIC_DODGE:
			tv_statistic.setText(String.format(getContext().getString(R.string.cap_tank_statistics_dodge),
					obj.getValue()));
			iv_statistic.setImageResource(R.drawable.icon_tank_statistics_dodge);
			break;
		case TankStatisticData.STATISTIC_DURABLE:
			tv_statistic.setText(String.format(getContext().getString(R.string.cap_tank_statistics_durable),
					obj.getValue()));
			iv_statistic.setImageResource(R.drawable.icon_tank_statistics_durable);
			break;
		case TankStatisticData.STATISTIC_FIRE:
			tv_statistic.setText(String.format(getContext().getString(R.string.cap_tank_statistics_fire),
					obj.getValue()));
			iv_statistic.setImageResource(R.drawable.icon_tank_statistics_fire);
			break;
		case TankStatisticData.STATISTIC_HIDE:
			tv_statistic.setText(String.format(getContext().getString(R.string.cap_tank_statistics_hide),
					obj.getValue()));
			iv_statistic.setImageResource(R.drawable.icon_tank_statistics_hide);
			break;
		case TankStatisticData.STATISTIC_HIT:
			tv_statistic.setText(String.format(getContext().getString(R.string.cap_tank_statistics_hit),
					obj.getValue()));
			iv_statistic.setImageResource(R.drawable.icon_tank_statistics_hit);
			break;
		case TankStatisticData.STATISTIC_RANGE:
			tv_statistic.setText(String.format(getContext().getString(R.string.cap_tank_statistics_range),
					obj.getValue()));
			iv_statistic.setImageResource(R.drawable.icon_tank_statistics_range);
			break;
		case TankStatisticData.STATISTIC_SPOT:
			tv_statistic.setText(String.format(getContext().getString(R.string.cap_tank_statistics_spot),
					obj.getValue()));
			iv_statistic.setImageResource(R.drawable.icon_tank_statistics_spot);
			break;
		default:
			tv_statistic.setText("");
			iv_statistic.setImageBitmap(null);
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
		mData = (TankStatisticData) data;
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
