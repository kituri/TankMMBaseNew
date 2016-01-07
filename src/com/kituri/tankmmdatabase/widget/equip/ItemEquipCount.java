package com.kituri.tankmmdatabase.widget.equip;

import com.kituri.app.data.Entry;
import com.kituri.app.widget.Populatable;
import com.kituri.app.widget.Selectable;
import com.kituri.app.widget.SelectionListener;
import com.kituri.tankmmdatabase.R;
import com.kituri.tankmmdatabase.data.equip.EquipCountData;
import com.kituri.tankmmdatabase.model.Intent;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class ItemEquipCount extends RelativeLayout implements Populatable<Entry>, Selectable<Entry>, View.OnClickListener {
	
	private EquipCountData mData;
	
	private SelectionListener<Entry> mListener;

	private TextView tv_count;
	private TextView tv_lv;	
	private ImageView iv_sp;
	
	public ItemEquipCount(Context context) {
		this(context, null);
	}

	public ItemEquipCount(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public ItemEquipCount(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView();
	}
	
	private void initView() {
		if (isInEditMode()) {
			return;
		}
		View convertView = LayoutInflater.from(this.getContext()).
				inflate(R.layout.item_equip_count, null);
		tv_lv = (TextView) convertView.findViewById(R.id.tv_lv);
		tv_count = (TextView) convertView.findViewById(R.id.tv_count);
		iv_sp = (ImageView) convertView.findViewById(R.id.iv_sp);
		this.addView(convertView);
		setOnClickListener(null);
		convertView.findViewById(R.id.iv_icon).setOnClickListener(this);
	}
	
	private void setData(EquipCountData data) {
		//iv_equip.set
		tv_count.setText(String.valueOf(data.getCounts()));
		tv_lv.setText(String.format(getContext().getString(
				R.string.cap_equip_lv), data.getLv()));
		if(data.getIsSp()){
			iv_sp.setImageResource(R.drawable.icon_equip_type_07);
		}else{
			iv_sp.setImageResource(R.drawable.icon_equip_type_01);
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
		mData = (EquipCountData) data;
		setData(mData);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(mListener == null){
			return;
		}
		mData.setIntent(new Intent(Intent.ACTION_EQUIP_DETAIL));
		mListener.onSelectionChanged(mData, true);
	}	
}
