package com.kituri.tankmmdatabase.widget.tech;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kituri.app.data.Entry;
import com.kituri.app.widget.Populatable;
import com.kituri.app.widget.Selectable;
import com.kituri.app.widget.SelectionListener;
import com.kituri.tankmmdatabase.R;
import com.kituri.tankmmdatabase.data.equip.EquipSearchData;
import com.kituri.tankmmdatabase.data.tech.TechSearchData;
import com.kituri.tankmmdatabase.model.Intent;
import com.kituri.tankmmdatabase.utils.TransformerUtils;


public class ItemFilterTechType extends RelativeLayout implements Populatable<Entry>, Selectable<Entry>, View.OnClickListener {
	
	private TechSearchData mData;
	private SelectionListener<Entry> mListener;
	
	private TextView tv_name;
	private TextView tv_selected;
	
	public ItemFilterTechType(Context context) {
		this(context, null);
	}

	public ItemFilterTechType(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public ItemFilterTechType(Context context, AttributeSet attrs, int defStyle) {
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
		tv_name.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
	}
	
	private void setData(TechSearchData result) {
		//tv_selected.setSelected(selected);
		//TankSearchResult result = DataTypeTransformer.transform(getContext(), obj);
		if(result.getSelect() == result.getType()){
			//tv_selected.setVisibility(View.VISIBLE);
			tv_name.setSelected(true);
		}else{
			//tv_selected.setVisibility(View.INVISIBLE);
			tv_name.setSelected(false);
		}
		
		tv_name.setText(result.getTypeName());
//		int resId = TransformerUtils.getEquipIconResId(getContext(), result);
//		if(resId != 0){
//			tv_name.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(resId), null, null, null);		
//		}else{
//			tv_name.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);		
//		}		
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
		mData = (TechSearchData) data;
		setData(mData);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(mListener == null){
			return;
		}		
		mData.setIntent(new Intent(Intent.ACTION_CONDITIONAL_QUERY));
		mData.setSelect(mData.getType());
		mListener.onSelectionChanged(mData, true);		
	}	
}
