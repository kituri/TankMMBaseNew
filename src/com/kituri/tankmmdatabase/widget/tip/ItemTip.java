package com.kituri.tankmmdatabase.widget.tip;

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
import com.kituri.tankmmdatabase.data.tip.Tip;
import com.kituri.tankmmdatabase.model.Intent;
import com.kituri.tankmmdatabase.utils.TransformerUtils;


public class ItemTip extends RelativeLayout implements Populatable<Entry>, Selectable<Entry>, View.OnClickListener {
	
	private Tip mData;
	private SelectionListener<Entry> mListener;

	private TextView tv_name;
	
	public ItemTip(Context context) {
		this(context, null);
	}

	public ItemTip(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public ItemTip(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView();
	}
	
	private void initView() {
		if (isInEditMode()) {
			return;
		}
		View convertView = LayoutInflater.from(this.getContext()).
				inflate(R.layout.item_tip, null);
		tv_name = (TextView) convertView.findViewById(R.id.tv_name);
		this.addView(convertView, new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT));
		setOnClickListener(null);
		convertView.findViewById(R.id.v_bk).setOnClickListener(this);
	}
	
	private void setData(Tip data) {
		//iv_equip.set
		tv_name.setText(data.getTipName());
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
		mData = (Tip) data;
		setData(mData);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(mListener == null){
			return;
		}		
		mData.setIntent(new Intent(Intent.ACTION_WEB));
		mListener.onSelectionChanged(mData, true);		
	}	
}
