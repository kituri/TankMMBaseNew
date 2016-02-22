package com.kituri.tankmmdatabase.ui.tech;

import java.util.List;

import com.kituri.app.controller.EntryAdapter;
import com.kituri.app.data.Entry;
import com.kituri.app.ui.BaseFragmentActivity;
import com.kituri.app.widget.SelectionListener;
import com.kituri.app.widget.dialog.CustomDialog;
import com.kituri.tankmmdatabase.KituriTankMMApplication;
import com.kituri.tankmmdatabase.R;
import com.kituri.tankmmdatabase.controller.TechManager;
import com.kituri.tankmmdatabase.data.tech.TechSearchData;
import com.kituri.tankmmdatabase.data.tech.TechTypeData;
import com.kituri.tankmmdatabase.model.Intent;
import com.kituri.tankmmdatabase.widget.dialog.DialogTechNumberPicker;
import com.kituri.tankmmdatabase.widget.dialog.DialogTechTypeFilter;
import com.kituri.tankmmdatabase.widget.tech.ItemTypeTech;
import com.kituri.app.model.JsonModel;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;

//科技模拟器
public class TechEmuActivity extends BaseFragmentActivity implements SelectionListener<Entry>, OnClickListener {

	
	private ListView lv_tech_list;
	private TextView tv_search;
	private CustomDialog mCustomDialog;
	private DialogTechTypeFilter mDialogSearchFilter;
	
	private CustomDialog mCdNumberPicker;
	private DialogTechNumberPicker mDialogTechNumberPicker;
	
	private EntryAdapter mAdapter;
	private TechSearchData mSearchData;

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		setTitle(R.string.cap_tech_emu_title);
		setHomeAction(false);
		lv_tech_list = (ListView) findViewById(R.id.lv_tech_list);
		tv_search = (TextView) findViewById(R.id.tv_search);
		
		mDialogSearchFilter = new DialogTechTypeFilter(this);
		mCustomDialog = new CustomDialog(this, mDialogSearchFilter);
		mDialogTechNumberPicker = new DialogTechNumberPicker(this);
		mCdNumberPicker = new CustomDialog(this, mDialogTechNumberPicker);
		
		mAdapter = new EntryAdapter(this);
		lv_tech_list.setAdapter(mAdapter);
		findViewById(R.id.tv_view).setOnClickListener(this);
		findViewById(R.id.tv_share).setOnClickListener(this);
		mDialogSearchFilter.setSelectionListener(this);
		mCdNumberPicker.setSelectionListener(this);
		mAdapter.setSelectionListener(this);
		tv_search.setOnClickListener(this);			
		//TechManager.techInitEmu(this);
		setData(mSearchData);
	}

	private void setData(TechSearchData searchData){
		//sData.setAllLv(PsPushUserData.getData(context, sData.getTag(), 1));
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
		List<TechTypeData> list = TechManager.getTechTypeListEntryForList(this, searchData, true);
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
			//KituriTankMMApplication.gotoTechTypeDetail(this, (TechTypeData) item);
			mDialogTechNumberPicker.populate((TechTypeData) item);
			mCdNumberPicker.show();
			setData(mSearchData);
		}else if(action.equals(Intent.ACTION_CONDITIONAL_QUERY)){
			mCustomDialog.dismiss();
			mSearchData = (TechSearchData)item;
			setData(mSearchData);
		}else if(action.equals(Intent.ACTION_DIALOG_DISMISS)){
			mCustomDialog.dismiss();
			mCdNumberPicker.dismiss();
		}else if(action.equals(Intent.ACTION_TECH_EMU_UPDATE)){
			TechManager.techEmuTechUpdate(this, (TechTypeData) item);
			mCdNumberPicker.dismiss();
			setData(mSearchData);
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
//		case R.id.tv_setdata:
//			TechManager.techEmuSave(this);
//			Toast.makeText(this, getString(R.string.msg_opeartion_is_ok), Toast.LENGTH_SHORT).show();
//			break;
		case R.id.tv_share:
			KituriTankMMApplication.gotoTechShare(this, true);
			break;
		case R.id.tv_view:
			KituriTankMMApplication.gotoTechShare(this, false);
			//Toast.makeText(this, TechManager.getShareContent(this), Toast.LENGTH_LONG).show();
			break;
		default:
			break;
		}
	}

	@Override
	public int getLayoutID() {
		// TODO Auto-generated method stub
		return R.layout.activity_tech_emu;
	}

	@Override
	public void initDataBundle(Bundle bundle) {
		// TODO Auto-generated method stub
		mSearchData = (TechSearchData) bundle.getSerializable(Intent.EXTRA_TECH_SEARCH_DATA);
	}

	@Override
	protected JsonModel initJsonModel() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
