package com.kituri.tankmmdatabase.ui.tank;

import java.util.List;

import com.kituri.app.controller.EntryAdapter;
import com.kituri.app.data.Entry;
import com.kituri.app.widget.SelectionListener;
import com.kituri.tankmmdatabase.KituriTankMMApplication;
import com.kituri.tankmmdatabase.R;
import com.kituri.tankmmdatabase.controller.TankManager;
import com.kituri.tankmmdatabase.data.tank.TankData;
import com.kituri.tankmmdatabase.data.tank.TankDataBase;
import com.kituri.tankmmdatabase.data.tank.TankSearchFilterData;
import com.kituri.tankmmdatabase.model.Intent;
import com.kituri.tankmmdatabase.ui.common.BaseActivity;
import com.kituri.tankmmdatabase.widget.tank.ItemTankList;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class TankListActivity extends BaseActivity implements SelectionListener<Entry>, OnClickListener {

	static public final int FILTER_RESULT_CODE = 111;

	public TankListActivity() {
		super(R.layout.activity_tank_list);
		// TODO Auto-generated constructor stub
	}

	private GridView gv_tank_list;
	private TextView tv_search;
	// private CustomDialog mCustomDialog;
	// private DialogSearchTankFilter mDialogSearchFilter;

	private EntryAdapter mAdapter;

	private TankSearchFilterData mFilterData;
	// private TankSearchData mSearchData;

	@Override
	protected void getData() {
		// TODO Auto-generated method stub
		// mSearchData = (TankSearchData)
		mFilterData = (TankSearchFilterData) getIntent().getSerializableExtra(Intent.EXTRA_TANK_SEARCH_DATA);
		if(mFilterData == null){
			mFilterData = new TankSearchFilterData();
		}		
	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		setTitle(R.string.cap_tank_list_title);
		setHomeAction(false);
		gv_tank_list = (GridView) findViewById(R.id.gv_tank_list);
		tv_search = (TextView) findViewById(R.id.tv_search);
		//tv_search.setText(TankDataBase.TANK_DEFAULT_ALL.ALL_STRING);
		// mDialogSearchFilter = new DialogSearchTankFilter(this);
		// mCustomDialog = new CustomDialog(this, mDialogSearchFilter);

		mAdapter = new EntryAdapter(this);
		gv_tank_list.setAdapter(mAdapter);
		// mDialogSearchFilter.setSelectionListener(this);
		mAdapter.setSelectionListener(this);
		tv_search.setOnClickListener(this);
		setData(mFilterData);
	}

	private void setData(TankSearchFilterData data) {
		if(data.getType() == TankSearchFilterData.TANK_ALL &&
				data.getNationality() == TankSearchFilterData.TANK_ALL &&
				data.getStar() == TankSearchFilterData.TANK_ALL &&
				data.getStatistic() == TankSearchFilterData.TANK_ALL &&
				data.getTechData() == null){
			tv_search.setText(TankDataBase.TANK_DEFAULT_ALL.ALL_STRING);
		}else if(data.getTechData() != null){
			tv_search.setOnClickListener(null);
			tv_search.setText(data.getTechData().getTechName());
		}else{
			tv_search.setText(getString(R.string.cap_tank_list_search_value_custom));
		}
		mAdapter.clear();
		List<TankData> list = null;
		if(data.getTechData() != null){
			list = TankManager.conditionalQuery(data.getTechData());
		}else{
			list = TankManager.conditionalQuery(data);
		}
		if(list.size() == 0){
			Toast.makeText(this, getString(R.string.msg_is_nothing), Toast.LENGTH_SHORT).show();
			return;
		}
		for (TankData entry : list) {
			entry.setViewName(ItemTankList.class.getName());
			mAdapter.add(entry);
		}
		gv_tank_list.smoothScrollToPosition(0);
	}

	@Override
	public void onSelectionChanged(Entry item, boolean selected) {
		// TODO Auto-generated method stub
		if (item == null) {
			return;
		}
		String action = item.getIntent().getAction();
		if (action.equals(Intent.ACTION_TANK_DETAIL)) {
			KituriTankMMApplication.gotoTankDetail(this, (TankData) item);
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.tv_search:
			// mDialogSearchFilter.populate(TankManager.getTankFilterListForDialog(this,
			// mSearchData));
			// mCustomDialog.show();
			// KituriTankMMApplication.gotoTankFilter(this, mFilterData);
			gotoTankFilter();
			break;

		default:
			break;
		}
	}

	private void gotoTankFilter() {
		android.content.Intent intent = new android.content.Intent(this, TankFilterActivity.class);
		intent.putExtra(com.kituri.tankmmdatabase.model.Intent.EXTRA_TANK_SEARCH_DATA, mFilterData);
		startActivityForResult(intent, FILTER_RESULT_CODE);
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, android.content.Intent data){
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);	
		//Toast.makeText(this, "onActivityResult", Toast.LENGTH_SHORT).show();
		if (resultCode == RESULT_OK) {
			if(data == null){
				return;
			}
			if(data.getSerializableExtra(Intent.EXTRA_TANK_SEARCH_DATA) != null){
				mFilterData = (TankSearchFilterData) data.
						getSerializableExtra(Intent.EXTRA_TANK_SEARCH_DATA);
				setData(mFilterData);
			}
		}
	}

	
	
//	@Override
//	protected void onNewIntent(android.content.Intent intent) {
//	        super.onNewIntent(intent);
//	        if(intent.getSerializableExtra(Intent.EXTRA_TANK_SEARCH_DATA) != null){
//				mFilterData = (TankSearchFilterData) intent.
//						getSerializableExtra(Intent.EXTRA_TANK_SEARCH_DATA);
//				setData(mFilterData);
//			}
//	    }
	
}
