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
import com.kituri.tankmmdatabase.data.tank.TankSearchData;
import com.kituri.tankmmdatabase.data.tank.TankSearchResult;
import com.kituri.tankmmdatabase.model.DataTypeTransformer;
import com.kituri.tankmmdatabase.model.Intent;


public class ItemFilterTank extends RelativeLayout implements Populatable<Entry>, Selectable<Entry>, View.OnClickListener {
	
	private TankSearchResult mData;
	private SelectionListener<Entry> mListener;
	
	private TextView tv_name;
	private TextView tv_selected;
	
	public ItemFilterTank(Context context) {
		this(context, null);
	}

	public ItemFilterTank(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public ItemFilterTank(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView();
	}
	
	private void initView() {
		if (isInEditMode()) {
			return;
		}
		View convertView = LayoutInflater.from(this.getContext()).
				inflate(R.layout.item_filter, null);
		tv_selected =  (TextView) convertView.findViewById(R.id.tv_selected);
		tv_name =  (TextView) convertView.findViewById(R.id.tv_name);
		this.addView(convertView, new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
		tv_name.setOnClickListener(this);
	}
	
	private void setData(TankSearchResult result) {
		//tv_selected.setSelected(selected);
		//TankSearchResult result = DataTypeTransformer.transform(getContext(), obj);
		if(result.getSelect() == result.getQueryValue()){
			//tv_selected.setVisibility(View.VISIBLE);
			tv_name.setSelected(true);
		}else{
			//tv_selected.setVisibility(View.INVISIBLE);
			tv_name.setSelected(false);
		}
		
		tv_name.setText(result.getName());
		if(result.getIconResId() != 0){
			tv_name.setCompoundDrawables(null, getResources().getDrawable(result.getIconResId()), null, null);		
		}else{
			tv_name.setCompoundDrawables(null, null, null, null);		
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
		mData = (TankSearchResult) data;
		setData(mData);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(mListener == null){
			return;
		}		
		mData.setIntent(new Intent(Intent.ACTION_CONDITIONAL_QUERY));
		mData.setSelect(mData.getQueryValue());
		mListener.onSelectionChanged(mData, true);		
	}	
}
