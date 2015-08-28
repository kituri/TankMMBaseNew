package com.kituri.tankmmdatabase.ui.equip;

import java.util.List;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.GridView;
import android.widget.TextView;

import com.kituri.app.controller.EntryAdapter;
import com.kituri.app.data.Entry;
import com.kituri.app.widget.SelectionListener;
import com.kituri.app.widget.dialog.CustomDialog;
import com.kituri.tankmmdatabase.R;
import com.kituri.tankmmdatabase.controller.EquipManager;
import com.kituri.tankmmdatabase.data.equip.EquipData;
import com.kituri.tankmmdatabase.data.equip.EquipSearchData;
import com.kituri.tankmmdatabase.model.Intent;
import com.kituri.tankmmdatabase.ui.common.BaseActivity;
import com.kituri.tankmmdatabase.widget.dialog.DialogEquipDetail;
import com.kituri.tankmmdatabase.widget.dialog.DialogSearchEquipFilter;
import com.kituri.tankmmdatabase.widget.equip.ItemEquipList;

public class EquipListActivity extends BaseActivity implements SelectionListener<Entry>, OnClickListener {

	public EquipListActivity() {
		super(R.layout.activity_equip_list);
		// TODO Auto-generated constructor stub
	}

	private GridView gv_equip_list;
	private TextView tv_search;
	private CustomDialog mCustomDialog;
	private CustomDialog mDetailDialog;
	private DialogSearchEquipFilter mDialogSearchFilter;
	private DialogEquipDetail mDialogEquipDetail;
	
	private EntryAdapter mAdapter;
	private EquipSearchData mSearchData;

	@Override
	protected void getData() {
		// TODO Auto-generated method stub
		mSearchData = (EquipSearchData) getIntent().getSerializableExtra(Intent.EXTRA_EQUIP_SEARCH_DATA);
	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		setTitle(R.string.cap_equip_list_title);
		setHomeAction(false);
		gv_equip_list = (GridView) findViewById(R.id.gv_equip_list);
		tv_search = (TextView) findViewById(R.id.tv_search);
		
		mDialogSearchFilter = new DialogSearchEquipFilter(this);
		mDialogEquipDetail = new DialogEquipDetail(this);
		mCustomDialog = new CustomDialog(this, mDialogSearchFilter);
		mDetailDialog = new CustomDialog(this, mDialogEquipDetail);
		
		mAdapter = new EntryAdapter(this);
		gv_equip_list.setAdapter(mAdapter);
		mDialogSearchFilter.setSelectionListener(this);
		mDetailDialog.setSelectionListener(this);
		mAdapter.setSelectionListener(this);
		tv_search.setOnClickListener(this);		
		
		setData(mSearchData);
	}

	private void setData(EquipSearchData searchData){
		List<EquipSearchData> lists = EquipManager.getEquipFilterListForDialog(this, searchData);
		for(EquipSearchData data : lists){
			if(searchData.getSelect() == data.getEquipType()){
				mSearchData = data;
			}
		}
		tv_search.setText(mSearchData.getEquipTypeName());
		queryEquipList(mSearchData);
	}
	
	private void queryEquipList(EquipSearchData searchData) {
		List<EquipData> list = EquipManager.conditionalQuery(searchData);
		for (EquipData entry : list) {
			entry.setViewName(ItemEquipList.class.getName());
			mAdapter.add(entry);
		}
	}


	@Override
	public void onSelectionChanged(Entry item, boolean selected) {
		// TODO Auto-generated method stub
		if(item == null){
			return;
		}
		String action = item.getIntent().getAction();
		if(action.equals(Intent.ACTION_EQUIP_DETAIL)){
			mDialogEquipDetail.populate((EquipData)item);
			mDetailDialog.show();
		}else if(action.equals(Intent.ACTION_CONDITIONAL_QUERY)){
			mCustomDialog.dismiss();
			mSearchData = (EquipSearchData)item;
			mAdapter.clear();
			setData(mSearchData);
		}else if(action.equals(Intent.ACTION_DIALOG_DISMISS)){
			mDetailDialog.dismiss();
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.tv_search:
			mDialogSearchFilter.populate(EquipManager.getEquipFilterListEntryForDialog(this, mSearchData));
			mCustomDialog.show();
			break;

		default:
			break;
		}
	}

}
