package com.kituri.tankmmdatabase.widget.dialog;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.kituri.app.controller.EntryAdapter;
import com.kituri.app.data.Entry;
import com.kituri.app.data.ListEntry;
import com.kituri.app.widget.Populatable;
import com.kituri.app.widget.Selectable;
import com.kituri.app.widget.SelectionListener;
import com.kituri.tankmmdatabase.R;
import com.kituri.tankmmdatabase.controller.TankManager;
import com.kituri.tankmmdatabase.data.tank.TankFilterList;
import com.kituri.tankmmdatabase.model.Intent;
import com.kituri.tankmmdatabase.widget.GridViewWithHeaderAndFooter;
import com.kituri.tankmmdatabase.widget.equip.ItemFilterEquip;
import com.kituri.tankmmdatabase.widget.tank.ItemFilterTank;

public class DialogSearchEquipFilter extends RelativeLayout implements Selectable<Entry>, View.OnClickListener, SelectionListener<Entry>, Populatable<ListEntry>{

	private SelectionListener<Entry> mListener;
	
	private GridViewWithHeaderAndFooter gv_filter;
	//private ItemFilterTank m_if_all;
	private EntryAdapter mAdapter;
	
	private ListEntry mData;
	
	public DialogSearchEquipFilter(Context context){
		this(context,null);
	}
	
	public DialogSearchEquipFilter(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	private void initView() {
		View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_filter, null);
		gv_filter = (GridViewWithHeaderAndFooter) view.findViewById(R.id.gv_filter);
		//m_if_all = new ItemFilterTank(getContext());
		//m_if_all.setSelectionListener(this);
		mAdapter = new EntryAdapter(getContext());
		mAdapter.setSelectionListener(this);
		//gv_filter.addHeaderView(m_if_all);
		gv_filter.setAdapter(mAdapter);
		this.addView(view, new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
	}
	
	private void setData(ListEntry datas){
		//m_if_all.populate(TankManager.getTankSearchResultForDialogAll(getContext(), datas));
		mAdapter.clear();
		for(Entry entry : datas.getEntries()){
			entry.setViewName(ItemFilterEquip.class.getName());
			mAdapter.add(entry);
		}
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
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
	public void onSelectionChanged(Entry item, boolean selected) {
		// TODO Auto-generated method stub
		if(item == null){
			return;
		}
		if(item.getIntent().getAction().equals(Intent.ACTION_CONDITIONAL_QUERY)){
			if(mListener != null){
				mListener.onSelectionChanged(item, true);
			}
		}
	}

	@Override
	public void populate(ListEntry data) {
		// TODO Auto-generated method stub
		if(data != null){
			mData = data;
			setData(mData);
		}
	}
	
}
