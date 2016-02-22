package com.kituri.tankmmdatabase.ui.tech;

import java.util.List;

import com.kituri.app.data.Entry;
import com.kituri.app.ui.BaseFragmentActivity;
import com.kituri.app.widget.Selectable;
import com.kituri.app.widget.SelectionListener;
import com.kituri.app.widget.dialog.CustomDialog;
import com.kituri.tankmmdatabase.KituriTankMMApplication;
import com.kituri.tankmmdatabase.R;
import com.kituri.tankmmdatabase.controller.TechManager;
import com.kituri.tankmmdatabase.data.tank.TankSearchFilterData;
import com.kituri.tankmmdatabase.data.tech.TechData;
import com.kituri.tankmmdatabase.data.tech.TechPageData;
import com.kituri.tankmmdatabase.data.tech.TechTypeData;
import com.kituri.tankmmdatabase.model.Intent;
import com.kituri.tankmmdatabase.widget.dialog.DialogTechDetail;
import com.kituri.app.model.JsonModel;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

public class TechDetailActivity extends BaseFragmentActivity implements OnClickListener, OnPageChangeListener, SelectionListener<Entry> {

	private TechTypeData mTechTypeData;
	private ViewPager vp_tech;
	private ViewPagerAdapter vpAdapter;
	
	private ImageView iv_rank_lv_01;
	private ImageView iv_rank_lv_02;
	private ImageView iv_rank_lv_03;
	
	private View btn_tech_left_arrow;
	private View btn_tech_right_arrow;
	
	private CustomDialog mDetailDialog;
	private DialogTechDetail mDialogTechDetail;


	@Override
	public void initView() {
		// TODO Auto-generated method stub
		setTitle(mTechTypeData.getTypeName());
		iv_rank_lv_01 = (ImageView) findViewById(R.id.iv_rank_lv_01);
		iv_rank_lv_02 = (ImageView) findViewById(R.id.iv_rank_lv_02);
		iv_rank_lv_03 = (ImageView) findViewById(R.id.iv_rank_lv_03);
		btn_tech_left_arrow = findViewById(R.id.btn_tech_left_arrow);
		btn_tech_right_arrow = findViewById(R.id.btn_tech_right_arrow);
		
		
		vp_tech = (ViewPager) findViewById(R.id.vp_tech);
		vpAdapter = new ViewPagerAdapter(getSupportFragmentManager(), mTechTypeData);
		vp_tech.setAdapter(vpAdapter);
		//tv_name.setSelected(true);
		mDialogTechDetail = new DialogTechDetail(this);
		mDetailDialog = new CustomDialog(this, mDialogTechDetail);
		mDetailDialog.setSelectionListener(this);
		vpAdapter.setSelectionListener(this);
		iv_rank_lv_01.setOnClickListener(this);
		iv_rank_lv_02.setOnClickListener(this);
		iv_rank_lv_03.setOnClickListener(this);
		btn_tech_left_arrow.setOnClickListener(this);	
		btn_tech_right_arrow.setOnClickListener(this);	
		vp_tech.setOnPageChangeListener(this);
		setData(mTechTypeData);
	}

	private void setData(TechTypeData data) {		
		if(mTechTypeData instanceof TechData){
			TechData techData = (TechData)mTechTypeData;
			rankChange(techData.getRank());			
			return;
		}
		rankChange(1);
	}
	
	private void rankChange(int rank){
		if(vp_tech.getCurrentItem() != rank - 1){
			vp_tech.setCurrentItem(rank - 1);
		}
		iv_rank_lv_01.setSelected(false);
		iv_rank_lv_02.setSelected(false);
		iv_rank_lv_03.setSelected(false);
		switch (rank) {
		case 1:
			iv_rank_lv_01.setSelected(true);
			break;

		case 2:
			iv_rank_lv_02.setSelected(true);
			break;
			
		case 3:
			iv_rank_lv_03.setSelected(true);
			break;
		}
		
		if(vp_tech.getCurrentItem() > 0){
			btn_tech_left_arrow.setVisibility(View.VISIBLE);
		}else{
			btn_tech_left_arrow.setVisibility(View.GONE);
		}

		if(vp_tech.getCurrentItem() < vpAdapter.getCount() - 1){
			btn_tech_right_arrow.setVisibility(View.VISIBLE);
		}else{
			btn_tech_right_arrow.setVisibility(View.GONE);
		}
		
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.iv_rank_lv_01:
			rankChange(1);
			break;
		case R.id.iv_rank_lv_02:
			rankChange(2);
			break;
		case R.id.iv_rank_lv_03:
			rankChange(3);
			break;
		case R.id.btn_tech_left_arrow:
			if(vp_tech.getCurrentItem() > 0){
				vp_tech.setCurrentItem(vp_tech.getCurrentItem() - 1);
			}
			break;
		case R.id.btn_tech_right_arrow:
			if(vp_tech.getCurrentItem() < vp_tech.getChildCount() - 1){
				vp_tech.setCurrentItem(vp_tech.getCurrentItem() + 1);
			}
			break;
		}
	}
	
	
	/**
	 * ViewPager适配器
	 * 
	 * @author kituri
	 * 
	 */
	public class ViewPagerAdapter extends FragmentStatePagerAdapter implements 
	SelectionListener<Entry>, Selectable<Entry>{

		//private FragmentManager mFm;

		private SelectionListener<Entry> mListener;
		
		private List<TechPageData> lists;
		
		public ViewPagerAdapter(FragmentManager fm, TechTypeData techTypeData) {
			super(fm);
			//this.mFm = fm;
			lists = initList(techTypeData);
		}
			

		private List<TechPageData> initList(TechTypeData data){
			return TechManager.conditionalQuery(TechDetailActivity.this, data);
		}

		@Override
		public Fragment getItem(int position) {
			TechFragment fragment = new TechFragment();
			Bundle args = new Bundle();
			args.putSerializable(Intent.EXTRA_TECH_PAGE_DATA, lists.get(position));
			fragment.setArguments(args);
			if (fragment instanceof Selectable) {
				((Selectable<Entry>)fragment).setSelectionListener(this);
			}
			return fragment;
		}


		@Override
		public int getCount() {
			if (lists == null) {
				return 0;
			}
			return lists.size();
		}

		/**
		 * 在每次 ViewPager 需要一个用以显示的 Object 的时候，该函数都会被 ViewPager.addNewItem() 调用。
		 **/
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			return super.instantiateItem(container, position);
		}

		/**
		 * 该函数被调用后，会对 Fragment 进行 FragmentTransaction.detach()。 这里不是 remove()，只是
		 * detach()，因此 Fragment 还在 FragmentManager 管理中， Fragment 所占用的资源不会被释放。
		 **/
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			//fragments.remove(position);
			super.destroyItem(container, position, object);
			//((ViewPager) container).removeView((View) object);   
		}

		/**
		 * 字面意思
		 **/
		@Override
		public boolean isViewFromObject(View view, Object object) {
			return super.isViewFromObject(view, object);
		}

	    
		@Override  
	    public int getItemPosition(Object object) {  
	        return POSITION_NONE;  
	    }  
		// instantiateItem(), destroyItem(), getCount() 以及 isViewFromObject()。

		@Override
		public void onSelectionChanged(Entry item, boolean selected) {
			// TODO Auto-generated method stub
			if(mListener != null){
				mListener.onSelectionChanged(item, selected);
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
		
	}


	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub
		rankChange(arg0 + 1);
	}


	@Override
	public void onSelectionChanged(Entry item, boolean selected) {
		// TODO Auto-generated method stub

		if(item == null){
			return;
		}
		String action = item.getIntent().getAction();
		if(action.equals(Intent.ACTION_TECH_DETAIL)){
			mDialogTechDetail.populate((TechData)item);
			mDetailDialog.show();
		}else if(action.equals(Intent.ACTION_DIALOG_DISMISS)){
			mDetailDialog.dismiss();
		}else if(action.equals(Intent.ACTION_TANK_LIST)){
			mDetailDialog.dismiss();
			TankSearchFilterData searchData = new TankSearchFilterData();
			searchData.setTechData((TechData) item);
			KituriTankMMApplication.gotoTankList(this, searchData);
		}
	}


	@Override
	public int getLayoutID() {
		// TODO Auto-generated method stub
		return R.layout.activity_tech_detail;
	}


	@Override
	public void initDataBundle(Bundle bundle) {
		// TODO Auto-generated method stub
		mTechTypeData = (TechTypeData) bundle.getSerializable(Intent.EXTRA_TECH_TYPE_DATA);

	}


	@Override
	protected JsonModel initJsonModel() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
