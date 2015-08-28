package com.kituri.tankmmdatabase.ui;

import java.util.List;

import android.widget.GridView;

import com.kituri.app.controller.EntryAdapter;
import com.kituri.app.data.Entry;
import com.kituri.app.widget.SelectionListener;
import com.kituri.tankmmdatabase.KituriTankMMApplication;
import com.kituri.tankmmdatabase.R;
import com.kituri.tankmmdatabase.controller.DataBaseCategoryManager;
import com.kituri.tankmmdatabase.data.DataBaseCategoryData;
import com.kituri.tankmmdatabase.data.equip.EquipData;
import com.kituri.tankmmdatabase.data.equip.EquipSearchData;
import com.kituri.tankmmdatabase.data.tank.TankSearchData;
import com.kituri.tankmmdatabase.data.tech.TechData;
import com.kituri.tankmmdatabase.data.tech.TechSearchData;
import com.kituri.tankmmdatabase.ui.common.BaseActivity;
import com.kituri.tankmmdatabase.widget.dbcategory.ItemGvDataBaseCategory;
public class MainActivity extends BaseActivity implements SelectionListener<Entry> {

	public MainActivity() {
		super(R.layout.activity_main);
		// TODO Auto-generated constructor stub
	}

	private GridView gv_category;
	private EntryAdapter mAdapter;

	@Override
	protected void getData() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		setTitle(getString(R.string.app_name));
		setHomeAction(true);
		gv_category = (GridView) findViewById(R.id.gv_category);
		mAdapter = new EntryAdapter(this);
		gv_category.setAdapter(mAdapter);
		mAdapter.setSelectionListener(this);
		initData();
	}

	private void initData() {
		initCategoryGridView();
	}

	private void initCategoryGridView() {
		List<DataBaseCategoryData> list = DataBaseCategoryManager.getDataBaseCategoryList(this);
		for (DataBaseCategoryData entry : list) {
			entry.setViewName(ItemGvDataBaseCategory.class.getName());
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
		if(action.equals(com.kituri.tankmmdatabase.model.Intent.ACTION_DATABASE_CATEGORY)){
			gotoCategoryActivity((DataBaseCategoryData) item);
		}
	}

	private void gotoCategoryActivity(DataBaseCategoryData data){
		switch (data.getType()) {
		case DataBaseCategoryData.TYPE_TANK:
			KituriTankMMApplication.gotoTankList(this, new TankSearchData(TankSearchData.TANK_ALL));
			break;
		case DataBaseCategoryData.TYPE_EQUIP:
			KituriTankMMApplication.gotoEquipList(this, new EquipSearchData(EquipData.TYPE_GUN_CARRIAGE));
			break;
		case DataBaseCategoryData.TYPE_TECHNOLOGY:
			KituriTankMMApplication.gotoTechList(this, new TechSearchData(TechData.TYPE_MAIN_GUN));
			break;
		default:
			break;
		}
	}
	
}
