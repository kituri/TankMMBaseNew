package com.kituri.tankmmdatabase.widget.tech;

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
import com.kituri.tankmmdatabase.data.tech.TechData;
import com.kituri.tankmmdatabase.data.tech.TechTypeData;
import com.kituri.tankmmdatabase.model.Intent;
import com.kituri.tankmmdatabase.utils.TransformerUtils;


public class ItemTypeTech extends RelativeLayout implements Populatable<Entry>, Selectable<Entry>, View.OnClickListener {
	
	private TechTypeData mData;
	private SelectionListener<Entry> mListener;
	
	private ImageView iv_tech_type;
	private TextView tv_name;
	
	public ItemTypeTech(Context context) {
		this(context, null);
	}

	public ItemTypeTech(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public ItemTypeTech(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView();
	}
	
	private void initView() {
		if (isInEditMode()) {
			return;
		}
		View convertView = LayoutInflater.from(this.getContext()).
				inflate(R.layout.item_tech_list, null);
		iv_tech_type =  (ImageView) convertView.findViewById(R.id.iv_tech_type);
		tv_name = (TextView) convertView.findViewById(R.id.tv_name);
		this.addView(convertView, new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT));
		setOnClickListener(null);
		convertView.findViewById(R.id.v_bk).setOnClickListener(this);
	}
	
	private void setData(TechTypeData data) {
		//iv_equip.set
		iv_tech_type.setImageResource(getContext().getResources().
				getIdentifier(data.getIcon(), "drawable", getContext().getPackageName()));
		tv_name.setText(data.getTypeName());
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
		mData = (TechTypeData) data;
		setData(mData);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(mListener == null){
			return;
		}		
		mData.setIntent(new Intent(Intent.ACTION_TECH_TYPE_DETAIL));
		mListener.onSelectionChanged(mData, true);		
	}	
}
