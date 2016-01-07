package com.kituri.tankmmdatabase.ui.tank;

import com.kituri.app.controller.EntryAdapter;
import com.kituri.app.data.Entry;
import com.kituri.app.widget.SelectionListener;
import com.kituri.tankmmdatabase.R;
import com.kituri.tankmmdatabase.data.tank.TankDataBase;
import com.kituri.tankmmdatabase.data.tank.TankSearchFilterData;
import com.kituri.tankmmdatabase.data.tank.TankSearchFilterItemData;
import com.kituri.tankmmdatabase.model.Intent;
import com.kituri.tankmmdatabase.ui.common.BaseActivity;
import com.kituri.tankmmdatabase.utils.TransformerUtils;
import com.kituri.tankmmdatabase.widget.tank.ItemFilterTank;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TankFilterActivity extends BaseActivity implements SelectionListener<Entry>, OnClickListener{

	private TankSearchFilterData mSearchData;
	
	
	public TankFilterActivity() {
		super(R.layout.activity_tank_filter);
		// TODO Auto-generated constructor stub
	}

	private TextView tv_ok;
	private TextView tv_default;
	
	private GridView gv_type;
	private GridView gv_nationality;
	private GridView gv_star;
	private GridView gv_statistic;

	private EntryAdapter mAdapterType;
	private EntryAdapter mAdapterNationality;
	private EntryAdapter mAdapterStar;
	private EntryAdapter mAdapterStatistic;
	
	private final TankDataBase typeBase = new TankDataBase(TankDataBase._TANK_TYPE);
	private final TankDataBase nationalityBase = new TankDataBase(TankDataBase._TANK_NATIONALITY);
	private final TankDataBase starBase = new TankDataBase(TankDataBase._TANK_STAR);
	private final TankDataBase statisticBase = new TankDataBase(TankDataBase._TANK_STATISTIC);
	
	@Override
	protected void getData() {
		// TODO Auto-generated method stub
		mSearchData = (TankSearchFilterData) getIntent().getSerializableExtra(Intent.EXTRA_TANK_SEARCH_DATA);
	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		setTitle(R.string.cap_dialog_choice_filter);
		setHomeAction(false);
		gv_type = (GridView) findViewById(R.id.gv_tank_type);
		gv_nationality = (GridView) findViewById(R.id.gv_tank_nationality);
		gv_star = (GridView) findViewById(R.id.gv_tank_star);
		gv_statistic = (GridView) findViewById(R.id.gv_tank_statistic);
		tv_ok = (TextView) findViewById(R.id.tv_ok);
		tv_default = (TextView) findViewById(R.id.tv_default);
		mAdapterType = new EntryAdapter(this);
		mAdapterNationality = new EntryAdapter(this);
		mAdapterStar = new EntryAdapter(this);
		mAdapterStatistic = new EntryAdapter(this);
		gv_type.setAdapter(mAdapterType);
		gv_nationality.setAdapter(mAdapterNationality);
		gv_star.setAdapter(mAdapterStar);
		gv_statistic.setAdapter(mAdapterStatistic);
		tv_ok.setOnClickListener(this);
		tv_default.setOnClickListener(this);
		mAdapterType.setSelectionListener(this);
		mAdapterNationality.setSelectionListener(this);
		mAdapterStar.setSelectionListener(this);
		mAdapterStatistic.setSelectionListener(this);
		setData(mSearchData);		
	}
	
	private void setData(TankSearchFilterData data){
		setAdapter(gv_type, mAdapterType, typeBase, mSearchData.getType());
		setAdapter(gv_nationality, mAdapterNationality, nationalityBase, mSearchData.getNationality());
		setAdapter(gv_star, mAdapterStar, starBase, mSearchData.getStar());
		setAdapter(gv_statistic, mAdapterStatistic, statisticBase, mSearchData.getStatistic());
	}

	private void setAdapter(EntryAdapter adapter, TankDataBase dataBase, int selectIndex){
		setAdapter(null, adapter, dataBase, selectIndex);		
	}
	
	private void setAdapter(GridView gv, EntryAdapter adapter, TankDataBase dataBase, int selectIndex){
		if(gv != null){
			int size = dataBase.getALL().size() + 1;//加的1是那个“全部”
			int length = 320;
			float density = 1;
			int gridviewWidth = (int) (size * (length + 4) * density);
			int itemWidth = (int) (length * density);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(gridviewWidth,
					LinearLayout.LayoutParams.MATCH_PARENT);
			gv.setLayoutParams(params); // 设置GirdView布局参数,横向布局的关键
			gv.setColumnWidth(itemWidth); // 设置列表项宽
			gv.setHorizontalSpacing(5); // 设置列表项水平间距
			gv.setStretchMode(GridView.NO_STRETCH);
			gv.setNumColumns(size); // 设置列数量=列表集合数
		}  
		adapter.clear();
		TankSearchFilterItemData all = new TankSearchFilterItemData(dataBase.getType(),
				getString(R.string.cap_btn_all), TankDataBase._ALL,
				TransformerUtils.getResId(this, "icon_empty")); 
		all.setViewName(ItemFilterTank.class.getName());
		all.setSelect(selectIndex);
		adapter.add(all);
		for(int i = 0; i < dataBase.getALL().size(); i++){
			TankSearchFilterItemData entry = new TankSearchFilterItemData(dataBase.getType(),
					dataBase.getALL_STRING().get(i), dataBase.getALL().get(i),
					TransformerUtils.getResId(this, dataBase.getALL_ICONS().get(i))); 
			entry.setViewName(ItemFilterTank.class.getName());
			entry.setSelect(selectIndex);
			adapter.add(entry);
		}
	}
	
	@Override
	public void onSelectionChanged(Entry item, boolean selected) {
		// TODO Auto-generated method stub
		if(item == null){
			return;
		}
		String action = item.getIntent().getAction();
		if(action.equals(Intent.ACTION_CONDITIONAL_QUERY)){
			TankSearchFilterItemData data = (TankSearchFilterItemData) item;
			switch (data.getFilterType()) {
			case TankDataBase._TANK_TYPE:
				setAdapter(mAdapterType, typeBase, data.getSelect());
				mSearchData.setType(data.getFilterValue());
				break;
			case TankDataBase._TANK_NATIONALITY:
				setAdapter(mAdapterNationality, nationalityBase, data.getSelect());
				mSearchData.setNationality(data.getFilterValue());
				break;
			case TankDataBase._TANK_STAR:
				setAdapter(mAdapterStar, starBase, data.getSelect());
				mSearchData.setStar(data.getFilterValue());
				break;
			case TankDataBase._TANK_STATISTIC:
				setAdapter(mAdapterStatistic, statisticBase, data.getSelect());
				mSearchData.setStatistic(data.getFilterValue());
				break;
			}
		}
	}

	private void setResult(TankSearchFilterData data){
		android.content.Intent intent = new android.content.Intent();
		intent.putExtra(com.kituri.tankmmdatabase.model.Intent.EXTRA_TANK_SEARCH_DATA, data);
		setResult(RESULT_OK, intent);
		finish();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.tv_ok:
			setResult(mSearchData);
			break;
		case R.id.tv_default:
			mSearchData.setType(TankDataBase._ALL);
			mSearchData.setNationality(TankDataBase._ALL);
			mSearchData.setStar(TankDataBase._ALL);
			mSearchData.setStatistic(TankDataBase._ALL);
			setData(mSearchData);
			break;
		}
	}

}
