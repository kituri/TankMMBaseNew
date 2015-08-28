package com.kituri.tankmmdatabase.ui.tech;

import android.widget.GridView;

import com.kituri.app.controller.EntryAdapter;
import com.kituri.app.data.Entry;
import com.kituri.app.widget.Selectable;
import com.kituri.app.widget.SelectionListener;
import com.kituri.tankmmdatabase.R;
import com.kituri.tankmmdatabase.data.tech.TechPageData;
import com.kituri.tankmmdatabase.model.Intent;
import com.kituri.tankmmdatabase.ui.common.BaseFragment;
import com.kituri.tankmmdatabase.widget.tech.ItemTech;

public class TechFragment extends BaseFragment implements Selectable<Entry>, SelectionListener<Entry>{

	private GridView gv_tech;
	private EntryAdapter mAdapter;
	private TechPageData mPageData;
	
	private SelectionListener<Entry> mListener;
	
	public TechFragment() {
		super(R.layout.fragment_tech);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		gv_tech = (GridView) findViewById(R.id.gv_tech);		
		mAdapter = new EntryAdapter(getActivity());
		gv_tech.setAdapter(mAdapter);
		mAdapter.setSelectionListener(this);
		mPageData = (TechPageData) getArguments().getSerializable(Intent.EXTRA_TECH_PAGE_DATA);			
		setData(mPageData);
	}

	private void setData(TechPageData datas){
		for(Entry entry : datas.getEntries()){
			entry.setViewName(ItemTech.class.getName());
			mAdapter.add(entry);
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
		String action = item.getIntent().getAction();
		if(action.equals(Intent.ACTION_TECH_DETAIL)){
			//Toast.makeText(getActivity(), ((TechData)item).getTechName(), Toast.LENGTH_SHORT).show();
			if(mListener != null){
				mListener.onSelectionChanged(item, true);
			}
		}
	}
	
}
