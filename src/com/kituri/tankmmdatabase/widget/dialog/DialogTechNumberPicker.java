package com.kituri.tankmmdatabase.widget.dialog;

import com.kituri.app.data.Entry;
import com.kituri.app.widget.Populatable;
import com.kituri.app.widget.Selectable;
import com.kituri.app.widget.SelectionListener;
import com.kituri.tankmmdatabase.R;
import com.kituri.tankmmdatabase.controller.TechManager;
import com.kituri.tankmmdatabase.data.tank.TankStatisticData;
import com.kituri.tankmmdatabase.data.tech.TechData;
import com.kituri.tankmmdatabase.data.tech.TechSpecialData;
import com.kituri.tankmmdatabase.data.tech.TechTypeData;
import com.kituri.tankmmdatabase.model.Intent;
import com.kituri.tankmmdatabase.widget.tank.ItemTankAstatistic;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;

//科技选择器
public class DialogTechNumberPicker  extends RelativeLayout implements Selectable<Entry>, View.OnClickListener, Populatable<TechTypeData>{

	private SelectionListener<Entry> mListener;
	
	private TextView tv_name;
	private ImageView iv_tech;
//	private NumberPicker np_01;
//	private NumberPicker np_02;
//	private NumberPicker np_03;
	private NumberPicker np_tech01;
	private NumberPicker np_tech02;
	private NumberPicker np_tech03;
	
	private TechTypeData mData;
	
	public DialogTechNumberPicker(Context context){
		this(context,null);
	}
	
	public DialogTechNumberPicker(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	private void initView() {
		View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_tech_number_picker, null);
		
		tv_name = (TextView) view.findViewById(R.id.tv_name);
		iv_tech = (ImageView) view.findViewById(R.id.iv_tech);
//		np_01 = (NumberPicker) view.findViewById(R.id.np_01);
//		np_02 = (NumberPicker) view.findViewById(R.id.np_02);
//		np_03 = (NumberPicker) view.findViewById(R.id.np_03);
		np_tech01 =  (NumberPicker) view.findViewById(R.id.np_tech01);
		np_tech02 =  (NumberPicker) view.findViewById(R.id.np_tech02);
		np_tech03 =  (NumberPicker) view.findViewById(R.id.np_tech03);
		np_tech01.setMaxValue(16);
		np_tech02.setMaxValue(16);
		np_tech03.setMaxValue(16);
		np_tech01.setMinValue(0);
		np_tech02.setMinValue(0);
		np_tech03.setMinValue(0);
		view.findViewById(R.id.btn_ok).setOnClickListener(this);
		view.findViewById(R.id.btn_close).setOnClickListener(this);
		this.addView(view);
	}
	
	private void setData(TechTypeData data){
		tv_name.setText(data.getTypeName());
		iv_tech.setImageResource(getContext().getResources().getIdentifier(data.getIcon(),
				"drawable", getContext().getPackageName()));		
		np_tech01.setValue(data.getAllLv1());
		np_tech02.setValue(data.getAllLv2());
		np_tech03.setValue(data.getAllLv3());
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(mListener == null){
			return;
		}
		switch (v.getId()) {
		case R.id.btn_ok:
			mData.setAllLv1(np_tech01.getValue());
			mData.setAllLv2(np_tech02.getValue());
			mData.setAllLv3(np_tech03.getValue());
			mData.setIntent(new Intent(Intent.ACTION_TECH_EMU_UPDATE));
			mListener.onSelectionChanged(mData, true);
			break;

		default:
			mData.setIntent(new Intent(Intent.ACTION_DIALOG_DISMISS));
			mListener.onSelectionChanged(mData, true);
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
	public void populate(TechTypeData data) {
		// TODO Auto-generated method stub
		if(data != null){
			mData = data;
			setData(mData);
		}
	}
	
}
