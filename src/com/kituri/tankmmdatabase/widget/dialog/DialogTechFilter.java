package com.kituri.tankmmdatabase.widget.dialog;

import com.kituri.app.controller.EntryAdapter;
import com.kituri.app.data.Entry;
import com.kituri.app.data.ListEntry;
import com.kituri.app.widget.Populatable;
import com.kituri.app.widget.Selectable;
import com.kituri.app.widget.SelectionListener;
import com.kituri.tankmmdatabase.R;
import com.kituri.tankmmdatabase.model.Intent;
import com.kituri.tankmmdatabase.widget.GridViewWithHeaderAndFooter;
import com.kituri.tankmmdatabase.widget.tech.ItemFilterTech;
import com.kituri.tankmmdatabase.widget.tech.ItemFilterTechType;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class DialogTechFilter extends RelativeLayout implements Selectable<Entry>, View.OnClickListener, SelectionListener<Entry>, Populatable<ListEntry>{

	private SelectionListener<Entry> mListener;
	
	private ListView lv_filter;
	//private ItemFilterTank m_if_all;
	private EntryAdapter mAdapter;
	
	private ListEntry mData;
	
	public DialogTechFilter(Context context){
		this(context,null);
	}
	
	public DialogTechFilter(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	private void initView() {
		View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_tech_filter, null);
		lv_filter = (ListView) view.findViewById(R.id.lv_filter);
		mAdapter = new EntryAdapter(getContext());
		mAdapter.setSelectionListener(this);
		view.findViewById(R.id.iv_close).setOnClickListener(this);
		lv_filter.setAdapter(mAdapter);
		this.addView(view, new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
	}
	
	private void setData(ListEntry datas){
		mAdapter.clear();
		for(Entry entry : datas.getEntries()){
			entry.setViewName(ItemFilterTech.class.getName());
			mAdapter.add(entry);
		}
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.iv_close:
			if(mListener != null){
				mData.setIntent(new com.kituri.tankmmdatabase.model.Intent(
						com.kituri.tankmmdatabase.model.Intent.ACTION_DIALOG_DISMISS));
				mListener.onSelectionChanged(mData, true);
			}
			break;

		default:
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
	public void onSelectionChanged(Entry item, boolean selected) {
		// TODO Auto-generated method stub
		if(item == null){
			return;
		}
		//if(item.getIntent().getAction().equals(Intent.ACTION_CONDITIONAL_QUERY)){
			if(mListener != null){
				mListener.onSelectionChanged(item, true);
			}
		//}
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
