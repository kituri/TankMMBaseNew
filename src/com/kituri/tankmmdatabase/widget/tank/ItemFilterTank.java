package com.kituri.tankmmdatabase.widget.tank;

import com.kituri.app.data.Entry;
import com.kituri.app.widget.Populatable;
import com.kituri.app.widget.Selectable;
import com.kituri.app.widget.SelectionListener;
import com.kituri.tankmmdatabase.R;
import com.kituri.tankmmdatabase.data.tank.TankSearchFilterItemData;
import com.kituri.tankmmdatabase.model.Intent;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class ItemFilterTank extends RelativeLayout implements Populatable<Entry>, Selectable<Entry>, View.OnClickListener {
	
	private TankSearchFilterItemData mData;
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
	
	private void setData(TankSearchFilterItemData result) {
		//tv_selected.setSelected(selected);
		//TankSearchResult result = DataTypeTransformer.transform(getContext(), obj);
		if(result.getSelect() == result.getFilterValue()){
			//tv_selected.setVisibility(View.VISIBLE);
			tv_name.setSelected(true);
		}else{
			//tv_selected.setVisibility(View.INVISIBLE);
			tv_name.setSelected(false);
		}
		
		tv_name.setText(result.getFilterName());
		if(result.getIcon() != 0){
			//result.getIcon()
			tv_name.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(result.getIcon()), null, null, null);		
		}else{
			tv_name.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);		
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
		mData = (TankSearchFilterItemData) data;
		setData(mData);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(mListener == null){
			return;
		}		
		mData.setIntent(new Intent(Intent.ACTION_CONDITIONAL_QUERY));
		mData.setSelect(mData.getFilterValue());
		mListener.onSelectionChanged(mData, true);		
	}	
}
