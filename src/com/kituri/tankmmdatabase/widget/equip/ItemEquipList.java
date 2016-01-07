package com.kituri.tankmmdatabase.widget.equip;

import android.content.Context;
import android.text.TextUtils;
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
import com.kituri.tankmmdatabase.data.equip.EquipData;
import com.kituri.tankmmdatabase.data.equip.EquipSearchData;
import com.kituri.tankmmdatabase.model.Intent;
import com.kituri.tankmmdatabase.utils.TransformerUtils;


public class ItemEquipList extends RelativeLayout implements Populatable<Entry>, Selectable<Entry>, View.OnClickListener {
	
	private EquipData mData;
	private SelectionListener<Entry> mListener;
	
	private ImageView iv_equip;
	private ImageView iv_equip_type;
	private TextView tv_name;
	
	public ItemEquipList(Context context) {
		this(context, null);
	}

	public ItemEquipList(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public ItemEquipList(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView();
	}
	
	private void initView() {
		if (isInEditMode()) {
			return;
		}
		View convertView = LayoutInflater.from(this.getContext()).
				inflate(R.layout.item_equip_list, null);
		iv_equip =  (ImageView) convertView.findViewById(R.id.iv_equip);
		iv_equip_type =  (ImageView) convertView.findViewById(R.id.iv_equip_type);
		tv_name = (TextView) convertView.findViewById(R.id.tv_name);
		this.addView(convertView, new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
		setOnClickListener(null);
		convertView.findViewById(R.id.iv_icon).setOnClickListener(this);
	}
	
	private void setData(EquipData data) {
		//iv_equip.set
		iv_equip_type.setImageResource(TransformerUtils.getEquipIconResId(getContext(), data));
		if(TextUtils.isEmpty(data.getEquipName())){
			iv_equip.setImageResource(android.R.color.transparent);
			tv_name.setText("");
		}else{
			tv_name.setText(data.getEquipName());
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
		mData = (EquipData) data;
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
