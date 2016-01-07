package com.kituri.tankmmdatabase.ui.tip;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Dialog;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.kituri.app.KituriApplication;
import com.kituri.app.controller.EntryAdapter;
import com.kituri.app.data.Entry;
import com.kituri.app.model.KituriToast;
import com.kituri.app.model.MyAsyncTask;
import com.kituri.app.model.asyncdrawable.TaskCache;
import com.kituri.app.ui.detailphotoview.PicSimpleBitmapWorkerTask;
import com.kituri.app.utils.FileDownloaderHttpHelper;
import com.kituri.app.utils.FileManager;
import com.kituri.app.utils.ImageUtility;
import com.kituri.app.utils.PhotoUtils;
import com.kituri.app.utils.FileManager.FileLocationMethod;
import com.kituri.app.utils.PhotoUtils.Photoable;
import com.kituri.app.utils.Utility;
import com.kituri.app.widget.CircleProgressView;
import com.kituri.app.widget.SelectionListener;
import com.kituri.app.widget.dialog.CustomDialog;
import com.kituri.net.http.HttpMethod;
import com.kituri.net.http.HttpUtility;
import com.kituri.net.http.error.KituriException;
import com.kituri.tankmmdatabase.KituriTankMMApplication;
import com.kituri.tankmmdatabase.R;
import com.kituri.tankmmdatabase.controller.TechManager;
import com.kituri.tankmmdatabase.data.tech.TechSearchData;
import com.kituri.tankmmdatabase.data.tech.TechTypeData;
import com.kituri.tankmmdatabase.data.tip.Tip;
import com.kituri.tankmmdatabase.model.Intent;
import com.kituri.tankmmdatabase.ui.common.BaseActivity;
import com.kituri.tankmmdatabase.utils.TipsUtils;
import com.kituri.tankmmdatabase.widget.dialog.DialogTechFilter;
import com.kituri.tankmmdatabase.widget.tech.ItemTypeTech;
import com.kituri.tankmmdatabase.widget.tip.ItemTip;

public class OfficialStrategyActivity extends BaseActivity implements SelectionListener<Entry> {

	private final String tipsUrl = "http://news.tankmm.com/html/mw/html/mw_info/";
	
	public OfficialStrategyActivity() {
		super(R.layout.activity_official_strategy);
		// TODO Auto-generated constructor stub
	}

	private ListView lv_tips;
	private EntryAdapter mAdapter;
	private CustomDialog mLoadingDialog;
	private TipsWorkerTask mTipsWorkerTask;

	@Override
	protected void getData() {
		// TODO Auto-generated method stub
	}

	@Override
	protected void initView() {
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
	
}
