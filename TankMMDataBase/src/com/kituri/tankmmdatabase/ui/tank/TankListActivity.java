package com.kituri.tankmmdatabase.ui.tank;

import java.util.List;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.GridView;
import android.widget.TextView;

import com.kituri.app.controller.EntryAdapter;
import com.kituri.app.data.Entry;
import com.kituri.app.widget.SelectionListener;
import com.kituri.app.widget.dialog.CustomDialog;
import com.kituri.tankmmdatabase.KituriTankMMApplication;
import com.kituri.tankmmdatabase.R;
import com.kituri.tankmmdatabase.controller.TankManager;
import com.kituri.tankmmdatabase.data.tank.TankData;
import com.kituri.tankmmdatabase.data.tank.TankDataBase;
import com.kituri.tankmmdatabase.data.tank.TankSearchData;
import com.kituri.tankmmdatabase.data.tank.TankSearchResult;
import com.kituri.tankmmdatabase.model.Intent;
import com.kituri.tankmmdatabase.ui.common.BaseActivity;
import com.kituri.tankmmdatabase.widget.dialog.DialogSearchTankFilter;
import com.kituri.tankmmdatabase.widget.tank.ItemTankList;

public class TankListActivity extends BaseActivity implements SelectionListener<Entry>, OnClickListener {

	public TankListActivity() {
		super(R.layout.activity_tank_list);
		// TODO Auto-generated constructor stub
	}

	private GridView gv_tank_list;
	private TextView tv_search;
	private CustomDialog mCustomDialog;
	private DialogSearchTankFilter mDialogSearchFilter;
	
	private EntryAdapter mAdapter;
	private TankSearchData mSearchData;

	@Override
	protected void getData() {
		// TODO Auto-generated method stub
		mSearchData = (TankSearchData) getIntent().getSerializableExtra(Intent.EXTRA_TANK_SEARCH_DATA);
	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		setTitle(R.string.cap_tank_list_title);
		setHomeAction(false);
		gv_tank_list = (GridView) findViewById(R.id.gv_tank_list);
		tv_search = (TextView) findViewById(R.id.tv_search);
		tv_search.setText(TankDataBase.TANK_DEFAULT_ALL.ALL_STRING);
		mDialogSearchFilter = new DialogSearchTankFilter(this);
		mCustomDialog = new CustomDialog(this, mDialogSearchFilter);
		
		mAdapter = new EntryAdapter(this);
		gv_tank_list.setAdapter(mAdapter);
		mDialogSearchFilter.setSelectionListener(this);
		mAdapter.setSelectionListener(this);
		tv_search.setOnClickListener(this);		
		setData(mSearchData);
	}

	private void setData(TankSearchData searchData){
		if(searchData instanceof TankSearchResult){
			tv_search.setText(((TankSearchResult)searchData).getName());
		}		
		queryTankMMList(searchData);
	}
	
	private void queryTankMMList(TankSearchData searchData) {
		 List<TankData> list = TankManager.conditionalQuery(searchData);
		for (TankData entry : list) {
			entry.setViewName(ItemTankList.class.getName());
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
		if(action.equals(Intent.ACTION_TANK_DETAIL)){
			KituriTankMMApplication.gotoTankDetail(this, (TankData)item);
		}else if(action.equals(Intent.ACTION_CONDITIONAL_QUERY)){
			mCustomDialog.dismiss();
			//Toast.makeText(this, ((TankSearchResult)item).getName(), Toast.LENGTH_LONG).show();
			//mSearchData.setSelect(((TankSearchResult)item).getQueryValue());
			mSearchData = (TankSearchResult)item;
			mAdapter.clear();
			setData(mSearchData);
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.tv_search:
			mDialogSearchFilter.populate(TankManager.getTankFilterListForDialog(this, mSearchData));
			mCustomDialog.show();
			break;

		default:
			break;
		}
	}

}
