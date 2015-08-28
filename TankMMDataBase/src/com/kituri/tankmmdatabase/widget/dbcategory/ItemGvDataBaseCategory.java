package com.kituri.tankmmdatabase.widget.dbcategory;

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
import com.kituri.tankmmdatabase.model.Intent;


public class ItemGvDataBaseCategory extends RelativeLayout implements Populatable<Entry>, Selectable<Entry>, View.OnClickListener {
	
	private DataBaseCategoryData mData;
	private SelectionListener<Entry> mListener;
	
	private ImageView iv_logo;
	private TextView tv_name;
	
	public ItemGvDataBaseCategory(Context context) {
		this(context, null);
	}

	public ItemGvDataBaseCategory(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public ItemGvDataBaseCategory(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView();
	}
	
	private void initView() {
		if (isInEditMode()) {
			return;
		}
		View convertView = LayoutInflater.from(this.getContext()).
				inflate(R.layout.item_gv_database_category, null);
		iv_logo = (ImageView) convertView.findViewById(R.id.iv_logo);
		tv_name =  (TextView) convertView.findViewById(R.id.tv_name);
		this.addView(convertView, new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
		this.setOnClickListener(this);
	}
	
	private void setData(DataBaseCategoryData obj) {
		iv_logo.setImageResource(obj.getResId());
		tv_name.setText(obj.getName());
		Drawable drawable = iv_logo.getDrawable();
		if (drawable == null){
			return;
		}
		if(obj.getEnabled()){
			drawable.clearColorFilter();
			iv_logo.setImageDrawable(drawable);
		}else{
			drawable.setColorFilter(Color.argb(180, 0, 0, 0), Mode.DARKEN); // 此处值可自行调整
			iv_logo.setImageDrawable(drawable);
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
		mData = (DataBaseCategoryData) data;
		setData(mData);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(!mData.getEnabled()){
			Toast.makeText(getContext(), getContext().getString(R.string.msg_is_disabled),
					Toast.LENGTH_SHORT).show();
			return;
		}
		if(mListener == null){
			return;
		}		
		mData.setIntent(new Intent(Intent.ACTION_DATABASE_CATEGORY));
		mListener.onSelectionChanged(mData, true);		
	}	
}
