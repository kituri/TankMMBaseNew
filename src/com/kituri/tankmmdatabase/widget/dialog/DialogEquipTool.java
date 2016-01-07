package com.kituri.tankmmdatabase.widget.dialog;

import java.util.ArrayList;

import com.kituri.app.controller.EntryAdapter;
import com.kituri.app.data.Entry;
import com.kituri.app.widget.Populatable;
import com.kituri.app.widget.Selectable;
import com.kituri.app.widget.SelectionListener;
import com.kituri.tankmmdatabase.R;
import com.kituri.tankmmdatabase.controller.EquipManager;
import com.kituri.tankmmdatabase.controller.TechManager;
import com.kituri.tankmmdatabase.data.equip.CostResData;
import com.kituri.tankmmdatabase.data.equip.EquipAllCostData;
import com.kituri.tankmmdatabase.data.tank.TankStatisticData;
import com.kituri.tankmmdatabase.data.tech.TechData;
import com.kituri.tankmmdatabase.data.tech.TechSpecialData;
import com.kituri.tankmmdatabase.model.Intent;
import com.kituri.tankmmdatabase.utils.Utils;
import com.kituri.tankmmdatabase.widget.equip.ItemCostRes;
import com.kituri.tankmmdatabase.widget.tank.ItemTankAstatistic;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DialogEquipTool extends RelativeLayout implements Selectable<Entry>, View.OnClickListener, Populatable<EquipAllCostData>{

	private SelectionListener<Entry> mListener;
	
	private GridView gv_res;
	private TextView tv_name;
	private EntryAdapter mAdapter;
	
	private EquipAllCostData mData;
	
	public DialogEquipTool(Context context){
		this(context,null);
	}
	
	public DialogEquipTool(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	private void initView() {
		View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_equip_tool, null);
		tv_name = (TextView) view.findViewById(R.id.tv_name);
		gv_res = (GridView) view.findViewById(R.id.gv_res);
		
		mAdapter = new EntryAdapter(getContext());
		gv_res.setAdapter(mAdapter);
		
		view.findViewById(R.id.btn_ok).setOnClickListener(this);
		this.addView(view);
	}
	
	private void setData(EquipAllCostData data){
		ArrayList<CostResData> list = EquipManager.getCostResList(data);
		mAdapter.clear();
		if(list.size() > 0){
			for(CostResData costResData : list){
				costResData.setViewName(ItemCostRes.class.getName());
				mAdapter.add(costResData);
			}
		}else{
			dismiss();
		}
		if(data.getIsSP()){
			tv_name.setText(String.format(getContext().getString(
					R.string.cap_dialog_create_equip_title_sp), data.getMakeCount(), data.getLv()));
		}else{
			tv_name.setText(String.format(getContext().getString(
					R.string.cap_dialog_create_equip_title_nor), data.getMakeCount(), data.getLv()));
		}

	}
	
	private void dismiss(){
		if(mListener == null){
			return;
		}
		
		mData.setIntent(new Intent(Intent.ACTION_DIALOG_DISMISS));
		mListener.onSelectionChanged(mData, true);
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		dismiss();
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
	public void populate(EquipAllCostData data) {
		// TODO Auto-generated method stub
		if(data != null){
			mData = data;
			setData(mData);
		}
	}
	
}
