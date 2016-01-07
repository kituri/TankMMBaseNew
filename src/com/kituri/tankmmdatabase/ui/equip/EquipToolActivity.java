package com.kituri.tankmmdatabase.ui.equip;

import java.util.ArrayList;

import com.kituri.app.controller.EntryAdapter;
import com.kituri.app.data.Entry;
import com.kituri.app.push.PsPushUserData;
import com.kituri.app.widget.SelectionListener;
import com.kituri.app.widget.dialog.CustomDialog;
import com.kituri.tankmmdatabase.R;
import com.kituri.tankmmdatabase.controller.EquipManager;
import com.kituri.tankmmdatabase.data.equip.EquipAllCostData;
import com.kituri.tankmmdatabase.data.equip.EquipCountData;
import com.kituri.tankmmdatabase.model.Intent;
import com.kituri.tankmmdatabase.ui.common.BaseActivity;
import com.kituri.tankmmdatabase.widget.TankToggleButton;
import com.kituri.tankmmdatabase.widget.dialog.DialogEquipTool;
import com.kituri.tankmmdatabase.widget.dialog.DialogInputNumber;
import com.kituri.tankmmdatabase.widget.equip.ItemEquipCount;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

//装备升级小工具
public class EquipToolActivity extends BaseActivity implements SelectionListener<Entry>, OnClickListener{
	
	static public String TAG_EQUIP_COUNT = "tag_equip_count";
	
	private TextView tv_create_lv;
	private ImageView iv_plus;
	private ImageView iv_subtraction;
	private TextView tv_create_count;
	private ImageView iv_plus_count;
	private ImageView iv_subtraction_count;
	private TankToggleButton tb_mode;
	private GridView gv_existing_equip;
	private EntryAdapter mAdapter;
	
	private ArrayList<EquipCountData> mEquipCountList;

	private CustomDialog mDetailDialog;
	private DialogInputNumber mDialogInputNumber;

	private CustomDialog mDialog;
	private DialogEquipTool mDialogEquip;
	
	private static final int INPUT_LIMIT_INIT_VALUE = 5;	
	private static final int INPUT_LIMIT_MIN_VALUE = 2;	
	private static final int INPUT_LIMIT_MAX_VALUE = 10;
	
	private static final int INPUT_COUNT_LIMIT_MIN_VALUE = 1;	
	
	private static final String TAG_TOGGLE_BUTTON_MODE = "TAG_TOGGLE_BUTTON_MODE";
	
	public EquipToolActivity() {
		super(R.layout.activity_tip_equip_tool);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void getData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		setTitle(R.string.cap_equip_tools);
		setHomeAction(false);
		

		tv_create_lv = (TextView) findViewById(R.id.tv_create_lv);
		iv_subtraction = (ImageView) findViewById(R.id.iv_subtraction);
		iv_plus = (ImageView) findViewById(R.id.iv_plus);
		
		tv_create_count = (TextView) findViewById(R.id.tv_create_count);
		iv_subtraction_count = (ImageView) findViewById(R.id.iv_subtraction_count);
		iv_plus_count = (ImageView) findViewById(R.id.iv_plus_count);
		
		gv_existing_equip = (GridView) findViewById(R.id.gv_existing_equip);
		tb_mode = (TankToggleButton) findViewById(R.id.tb_mode);

		mAdapter = new EntryAdapter(this);
		gv_existing_equip.setAdapter(mAdapter);
		gv_existing_equip.setVisibility(View.VISIBLE);
		
//		np_create_lv.setMaxValue(10);
//		np_create_lv.setMinValue(1);
		
		mDialogInputNumber = new DialogInputNumber(this);
		mDetailDialog = new CustomDialog(this, mDialogInputNumber);
		mDialogEquip = new DialogEquipTool(this);
		mDialog = new CustomDialog(this, mDialogEquip);
		
		findViewById(R.id.tv_default).setOnClickListener(this);
		findViewById(R.id.tv_ok).setOnClickListener(this);
		mAdapter.setSelectionListener(this);
		mDetailDialog.setSelectionListener(this);
		mDialog.setSelectionListener(this);

		iv_subtraction.setOnClickListener(this);
		iv_plus.setOnClickListener(this);
		iv_subtraction_count.setOnClickListener(this);
		iv_plus_count.setOnClickListener(this);
//		et_create_lv.setOnFocusChangeListener(this);
//		et_create_lv.addTextChangedListener(this);
		initData();
	}

	private void initData() {
		// TODO Auto-generated method stub
		tb_mode.setSelectText(R.string.cap_btn_equip_normal_mode,
				R.string.cap_btn_equip_sp_mode);
		tb_mode.setToggleTag(TAG_TOGGLE_BUTTON_MODE);
		tv_create_lv.setText(String.valueOf(INPUT_LIMIT_INIT_VALUE));
		mAdapter.clear();
		mEquipCountList = EquipManager.getEquipCountList();
		loadEquipCounts();
	}

    @Override
	public void onResume() {  
        super.onResume();  
    }  
	
	@Override
	public void onSelectionChanged(Entry item, boolean selected) {
		// TODO Auto-generated method stub
		if(item == null){
			return;
		}		
		String action = item.getIntent().getAction();
		if(Intent.ACTION_EQUIP_DETAIL.equals(action)){
			mDialogInputNumber.populate((EquipCountData) item);
			mDetailDialog.show();		
		}else if (action.equals(Intent.ACTION_EQUIP_COUNTS_CHANGE)) {
			mDetailDialog.dismiss();
			mAdapter.clear();
			for (int i = 0; i < mEquipCountList.size(); i++) {
				EquipCountData data = (EquipCountData) item;
				EquipCountData entry = mEquipCountList.get(i);
				if(entry.getLv() == data.getLv() &&
						entry.getIsSp() == data.getIsSp()){
					entry.setCounts(data.getCounts());
				}
				entry.setViewName(ItemEquipCount.class.getName());
				mAdapter.add(entry);
			}
		} else if (action.equals(Intent.ACTION_DIALOG_DISMISS)) {
			mDetailDialog.dismiss();
			mDialog.dismiss();
		}
	}

	private void saveEquipCounts(){
		if(mEquipCountList == null){
			return;
		}
		for (int i = 0; i < mEquipCountList.size(); i++) {
			PsPushUserData.setData(this, TAG_EQUIP_COUNT + i + mEquipCountList.get(i).getIsSp(), 
					mEquipCountList.get(i).getCounts());
		}
	}
	
	private void loadEquipCounts(){
		if(mEquipCountList == null){
			return;
		}		
		for (int i = 0; i < mEquipCountList.size(); i++) {
			mEquipCountList.get(i).setCounts(PsPushUserData.
					getData(this, TAG_EQUIP_COUNT + i + mEquipCountList.get(i).getIsSp(), 0));
		}
		mAdapter.clear();
		for (EquipCountData entry : mEquipCountList) {
			entry.setViewName(ItemEquipCount.class.getName());
			mAdapter.add(entry);
		}
	}
	
	@Override
	public void onDestroy(){
		super.onDestroy();
		saveEquipCounts();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.tv_default: {
			mAdapter.clear();
			mEquipCountList = EquipManager.getEquipCountList();
			for (EquipCountData entry : mEquipCountList) {
				entry.setViewName(ItemEquipCount.class.getName());
				mAdapter.add(entry);
			}
			break;
		}		
		case R.id.iv_plus:
			int plus = Integer.parseInt(tv_create_lv.getText().toString());
			plus++;
			tv_create_lv.setText(String.valueOf(plus > INPUT_LIMIT_MAX_VALUE ?
					INPUT_LIMIT_MAX_VALUE : plus));
			break;
		case R.id.iv_subtraction:
			int sub = Integer.parseInt(tv_create_lv.getText().toString());
			sub--;
			tv_create_lv.setText(String.valueOf(sub < INPUT_LIMIT_MIN_VALUE ?
					INPUT_LIMIT_MIN_VALUE : sub));
			break;
		case R.id.iv_plus_count:
			int plusCount = Integer.parseInt(tv_create_count.getText().toString());
			plusCount++;
			tv_create_count.setText(String.valueOf(plusCount));
			break;
		case R.id.iv_subtraction_count:
			int subCount = Integer.parseInt(tv_create_count.getText().toString());
			subCount--;
			tv_create_count.setText(String.valueOf(subCount < INPUT_COUNT_LIMIT_MIN_VALUE ?
					INPUT_COUNT_LIMIT_MIN_VALUE : subCount));
			break;
		case R.id.tv_ok:
			Boolean select = tb_mode.getIsSelect();
			EquipAllCostData allCost = EquipManager.calculationCost(
					Integer.parseInt(tv_create_lv.getText().toString()),
					mEquipCountList, select, Integer.parseInt(tv_create_count.getText().toString()));
			mDialogEquip.populate(allCost);
			mDialog.show();
			break;
		default:
			break;
		}
	}

	
}
