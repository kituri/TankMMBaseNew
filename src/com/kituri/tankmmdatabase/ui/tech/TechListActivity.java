package com.kituri.tankmmdatabase.ui.tech;

import java.util.List;

import com.kituri.app.controller.EntryAdapter;
import com.kituri.app.data.Entry;
import com.kituri.app.widget.SelectionListener;
import com.kituri.app.widget.dialog.CustomDialog;
import com.kituri.tankmmdatabase.KituriTankMMApplication;
import com.kituri.tankmmdatabase.R;
import com.kituri.tankmmdatabase.controller.TechManager;
import com.kituri.tankmmdatabase.data.tech.TechSearchData;
import com.kituri.tankmmdatabase.data.tech.TechTypeData;
import com.kituri.tankmmdatabase.model.Intent;
import com.kituri.tankmmdatabase.ui.common.BaseActivity;
import com.kituri.tankmmdatabase.widget.dialog.DialogTechTypeFilter;
import com.kituri.tankmmdatabase.widget.tech.ItemTypeTech;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class TechListActivity extends BaseActivity implements SelectionListener<Entry>, OnClickListener {

	public TechListActivity() {
		super(R.layout.activity_tech_type_list);
		// TODO Auto-generated constructor stub
	}

	private ListView lv_tech_list;
	private TextView tv_search;
	private CustomDialog mCustomDialog;
	private DialogTechTypeFilter mDialogSearchFilter;
	
	private EntryAdapter mAdapter;
	private TechSearchData mSearchData;

	@Override
	protected void getData() {
		// TODO Auto-generated method stub
		mSearchData = (TechSearchData) getIntent().getSerializableExtra(Intent.EXTRA_TECH_SEARCH_DATA);
	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		setTitle(R.string.cap_tech_list_title);
		setHomeAction(false);
		lv_tech_list = (ListView) findViewById(R.id.lv_tech_list);
		tv_search = (TextView) findViewById(R.id.tv_search);
		
		mDialogSearchFilter = new DialogTechTypeFilter(this);
		mCustomDialog = new CustomDialog(this, mDialogSearchFilter);
		
		mAdapter = new EntryAdapter(this);
		lv_tech_list.setAdapter(mAdapter);
		mDialogSearchFilter.setSelectionListener(this);
		mAdapter.setSelectionListener(this);
		tv_search.setOnClickListener(this);	
		findViewById(R.id.tv_tech_emu).setOnClickListener(this);
		
		setData(mSearchData);
	}

	private void setData(TechSearchData searchData){
		List<TechSearchData> lists = TechManager.getTechFilterListForDialog(this, searchData);
		for(TechSearchData data : lists){
			if(searchData.getSelect() == data.getType()){
				mSearchData = data;
			}
		}
		tv_search.setText(mSearchData.getTypeName());
		queryTechList(mSearchData);
	}
	
	private void queryTechList(TechSearchData searchData) {
		mAdapter.clear();
		List<TechTypeData> list = TechManager.getTechTypeListEntryForList(this, searchData);
		for (TechTypeData entry : list) {
			entry.setViewName(ItemTypeTech.class.getName());
			mAdapter.add(entry);
		}
		//Toast.makeText(this, searchData.getTypeName(), Toast.LENGTH_SHORT).show();
	}


	@Override
	public void onSelectionChanged(Entry item, boolean selected) {
		// TODO Auto-generated method stub
		if(item == null){
			return;
		}
		String action = item.getIntent().getAction();
		if(action.equals(Intent.ACTION_TECH_TYPE_DETAIL)){
			KituriTankMMApplication.gotoTechTypeDetail(this, (TechTypeData) item);
		}else if(action.equals(Intent.ACTION_CONDITIONAL_QUERY)){
			mCustomDialog.dismiss();
			mSearchData = (TechSearchData)item;
			setData(mSearchData);
		}else if(action.equals(Intent.ACTION_DIALOG_DISMISS)){
			mCustomDialog.dismiss();
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.tv_search:
			mDialogSearchFilter.populate(TechManager.getTechFilterListEntryForDialog(this, mSearchData));
			mCustomDialog.show();
			break;
		case R.id.tv_tech_emu:
			KituriTankMMApplication.gotoTechEmu(this);
			break;

		default:
			break;
		}
	}

}
