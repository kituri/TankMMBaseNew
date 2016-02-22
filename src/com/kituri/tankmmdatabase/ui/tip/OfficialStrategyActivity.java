package com.kituri.tankmmdatabase.ui.tip;

import java.util.ArrayList;

import com.kituri.app.controller.EntryAdapter;
import com.kituri.app.data.Entry;
import com.kituri.app.model.MyAsyncTask;
import com.kituri.app.ui.BaseFragmentActivity;
import com.kituri.app.widget.SelectionListener;
import com.kituri.app.widget.dialog.CustomDialog;
import com.kituri.net.http.HttpMethod;
import com.kituri.net.http.HttpUtility;
import com.kituri.net.http.error.KituriException;
import com.kituri.tankmmdatabase.KituriTankMMApplication;
import com.kituri.tankmmdatabase.R;
import com.kituri.tankmmdatabase.data.tip.Tip;
import com.kituri.tankmmdatabase.model.Intent;
import com.kituri.tankmmdatabase.utils.TipsUtils;
import com.kituri.tankmmdatabase.widget.tip.ItemTip;
import com.kituri.app.model.JsonModel;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.widget.ListView;
import android.widget.Toast;

public class OfficialStrategyActivity extends BaseFragmentActivity implements SelectionListener<Entry> {

	private final String tipsUrl = "http://news.tankmm.com/html/mw/html/mw_info/";

	private ListView lv_tips;
	private EntryAdapter mAdapter;
	private CustomDialog mLoadingDialog;
	private TipsWorkerTask mTipsWorkerTask;

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		setTitle(R.string.cap_official_strategy_title);
		setHomeAction(false);
		lv_tips = (ListView) findViewById(R.id.lv_tips);
		mAdapter = new EntryAdapter(this);
		mLoadingDialog = new CustomDialog(this, LayoutInflater.from(this).
				inflate(R.layout.dialog_loading, null));
		lv_tips.setAdapter(mAdapter);
		mAdapter.setSelectionListener(this);
		mTipsWorkerTask = new TipsWorkerTask(mLoadingDialog, mAdapter);
		mTipsWorkerTask.execute(tipsUrl);
	}
	
	public void onDestroy(){
		super.onDestroy();
		if(mTipsWorkerTask != null){
			mTipsWorkerTask.cancel(true);
		}		
	}
	
	@Override
	public void onSelectionChanged(Entry item, boolean selected) {
		// TODO Auto-generated method stub
		if(item == null){
			return;
		}
		String action = item.getIntent().getAction();
		if(action.equals(Intent.ACTION_WEB)){
			//KituriTankMMApplication.gotoTechTypeDetail(this, (TechTypeData) item);
			KituriTankMMApplication.gotoOfficialStrategyDetail(this, (Tip) item);
		}
	}

	public class TipsWorkerTask extends MyAsyncTask<String, Integer, String> {

		
		
		private EntryAdapter mAdapter;
		private CustomDialog loadingView;
		
	    public TipsWorkerTask(CustomDialog loadingView, EntryAdapter adapter) {
	    	this.loadingView = loadingView;
	    	this.mAdapter = adapter;
	    }

	  //onPreExecute方法用于在执行后台任务前做一些UI操作  
        @Override  
        protected void onPreExecute() {  
        	loadingView.show();
        }  
	    
	    //doInBackground方法内部执行后台任务,不可在此方法内修改UI 
	    @Override
	    protected String doInBackground(String... arg) {
	        if (isCancelled()) {
	            return null;
	        }
	        try {
				return HttpUtility.getInstance().executeNormalTask(HttpMethod.Get, arg[0], null, "GBK");
			} catch (KituriException e) {
				// TODO Auto-generated catch block
				return null;
			}
	    }
	    
	    //onProgressUpdate方法用于更新进度信息 
	    @Override
	    protected void onProgressUpdate(Integer... values) {
	        super.onProgressUpdate(values);
	       
	    }

	    //onCancelled方法用于在取消执行中的任务时更改UI  
	    @Override
	    protected void onCancelled(String s) {
	        super.onCancelled(s);
	        loadingView.dismiss();
	    }

	  //onPostExecute方法用于在执行完后台任务后更新UI,显示结果  
	    @Override
	    protected void onPostExecute(final String jsonResult) {
	    	mAdapter.clear();
	    	if(!TextUtils.isEmpty(jsonResult)){
	    		ArrayList<Tip> tips = TipsUtils.parseTips(jsonResult);
	    		for(Tip tip : tips){
	    			tip.setViewName(ItemTip.class.getName());
	    			mAdapter.add(tip);
	    		}
	    	}else{
	    		if(!OfficialStrategyActivity.this.isFinishing()){
	    			Toast.makeText(OfficialStrategyActivity.this, 
	    					OfficialStrategyActivity.this.getString(R.string.msg_net_error),
	    					Toast.LENGTH_SHORT).show();
	    		}
	    	}
	    	loadingView.dismiss();
	    }
	}

	@Override
	public int getLayoutID() {
		// TODO Auto-generated method stub
		return R.layout.activity_official_strategy;
	}

	@Override
	public void initDataBundle(Bundle bundle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected JsonModel initJsonModel() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
