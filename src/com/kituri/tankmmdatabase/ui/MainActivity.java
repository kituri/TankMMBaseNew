package com.kituri.tankmmdatabase.ui;

import java.util.List;

import com.kituri.app.controller.EntryAdapter;
import com.kituri.app.data.Entry;
import com.kituri.app.push.PsPushUserData;

import com.kituri.app.widget.SelectionListener;
import com.kituri.app.widget.dialog.CustomDialog;
import com.kituri.app.widget.doubleClick.DoubleClickImageView;
import com.kituri.app.widget.doubleClick.OnDoubleClickListener;
import com.kituri.tankmmdatabase.KituriTankMMApplication;
import com.kituri.tankmmdatabase.R;
import com.kituri.tankmmdatabase.controller.DataBaseCategoryManager;
import com.kituri.tankmmdatabase.data.DataBaseCategoryData;
import com.kituri.tankmmdatabase.data.tech.TechData;
import com.kituri.tankmmdatabase.data.tech.TechSearchData;
import com.kituri.tankmmdatabase.model.Intent;
import com.kituri.tankmmdatabase.ui.common.BaseActivity;
import com.kituri.tankmmdatabase.utils.Utils;
import com.kituri.tankmmdatabase.widget.dbcategory.ItemGvDataBaseCategory;
import com.kituri.tankmmdatabase.widget.dialog.DialogVerUpdateHistory;
import com.umeng.update.UmengUpdateAgent;
import com.umeng.update.UmengUpdateListener;
import com.umeng.update.UpdateResponse;

import android.app.AlertDialog;
import android.content.DialogInterface;

import android.net.Uri;
import android.view.View;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends BaseActivity implements SelectionListener<Entry>{

	static public final String TAG_SHOW_VER_UPDATE_HISTORY = "TAG_SHOW_VER_UPDATE_HISTORY";

	private GridView gv_category;
	private EntryAdapter mAdapter;

	private CustomDialog mDetailDialog;
	
	
	public MainActivity() {
		super(R.layout.activity_main);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void getData() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		KituriTankMMApplication.getInstance().setLoft(MainActivity.this);
		setTitle(getString(R.string.app_name));
		setHomeAction(true);
		showRightBtn(false);
		gv_category = (GridView) findViewById(R.id.gv_category);
		mAdapter = new EntryAdapter(this);
		gv_category.setAdapter(mAdapter);
		mAdapter.setSelectionListener(this);
		mDetailDialog = new CustomDialog(this, new DialogVerUpdateHistory(this));
		mDetailDialog.setSelectionListener(this);
		initData();
	}

	private void openVerUpdateHistory(){
		mDetailDialog.show();
	}
	
	private void initData() {		
		if(PsPushUserData.getData(this, TAG_SHOW_VER_UPDATE_HISTORY, true)){
			PsPushUserData.setData(this, TAG_SHOW_VER_UPDATE_HISTORY, false);
			openVerUpdateHistory();
		}
		initCategoryGridView();
		Utils.checkVersion(this, new UmengUpdateListener() {
			
			@Override
			public void onUpdateReturned(int updateStatus, UpdateResponse updateInfo) {
				// TODO Auto-generated method stub
				if (updateStatus == 0 && updateInfo != null) {
					//有版本更新
					PsPushUserData.setData(MainActivity.this, KituriTankMMApplication.MODE_NEED_UPDATE, true);					
				}else{
					PsPushUserData.setData(MainActivity.this, KituriTankMMApplication.MODE_NEED_UPDATE, false);
				}
				initCategoryGridView();
			}
		});
	}

	private void initCategoryGridView() {
		mAdapter.clear();
		List<DataBaseCategoryData> list = DataBaseCategoryManager.getDataBaseCategoryList(this);
		for (DataBaseCategoryData entry : list) {
			entry.setViewName(ItemGvDataBaseCategory.class.getName());
			mAdapter.add(entry);
		}
	}
	
	public void onResume() {
		super.onResume();
		if(mAdapter != null){
			if(mAdapter.getCount() > 0){
				initCategoryGridView();
			}
		}		
	};
	

	
	@Override
	public void onSelectionChanged(Entry item, boolean selected) {
		// TODO Auto-generated method stub
		if (item == null) {
			return;
		}
		String action = item.getIntent().getAction();
		if (action.equals(com.kituri.tankmmdatabase.model.Intent.ACTION_DATABASE_CATEGORY)) {
			gotoCategoryActivity((DataBaseCategoryData) item);
		}else if(action.equals(Intent.ACTION_DIALOG_DISMISS)){
			mDetailDialog.dismiss();
		}
	}

	private void gotoCategoryActivity(DataBaseCategoryData data) {
		switch (data.getType()) {
		case DataBaseCategoryData.TYPE_TANK:
			KituriTankMMApplication.gotoTankList(this);
			break;
		case DataBaseCategoryData.TYPE_EQUIP:
			// KituriTankMMApplication.gotoEquipList(this, new
			// EquipSearchData(EquipData.TYPE_GUN_CARRIAGE));
			KituriTankMMApplication.gotoEquipTools(this);
			break;
		case DataBaseCategoryData.TYPE_TECHNOLOGY:
			KituriTankMMApplication.gotoTechList(this, new TechSearchData(TechData.TYPE_MAIN_GUN));
			break;
		case DataBaseCategoryData.TYPE_TIPS:
			KituriTankMMApplication.gotoTips(this);
			break;
		case DataBaseCategoryData.TYPE_METAPHYSICS:
			KituriTankMMApplication.gotoMetaphysics(this);
			break;
		case DataBaseCategoryData.TYPE_SETTING:
			KituriTankMMApplication.gotoSetting(this);
			break;
		default:
			break;
		}
	}

}
