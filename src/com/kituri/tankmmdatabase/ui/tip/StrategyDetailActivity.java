package com.kituri.tankmmdatabase.ui.tip;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.kituri.app.model.MyAsyncTask;
import com.kituri.app.ui.BaseFragmentActivity;
import com.kituri.app.widget.dialog.CustomDialog;
import com.kituri.net.http.HttpMethod;
import com.kituri.net.http.HttpUtility;
import com.kituri.net.http.error.KituriException;
import com.kituri.tankmmdatabase.R;
import com.kituri.tankmmdatabase.data.tip.Tip;
import com.kituri.tankmmdatabase.model.Intent;
import com.kituri.app.model.JsonModel;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.widget.TextView;

public class StrategyDetailActivity extends BaseFragmentActivity {



	private CustomDialog mLoadingDialog;
	private WebView wv_show;
	private TextView tv_show;
	private Tip tip;
	private TipsWorkerTask mTipsWorkerTask;

	@SuppressLint("NewApi")
	@Override
	public void initView() {
		// TODO Auto-generated method stub

		setHomeAction(false);
		wv_show = (WebView) findViewById(R.id.wv_show);
		tv_show = (TextView) findViewById(R.id.tv_show);

		WebSettings webSettings = wv_show.getSettings();
		// // 关闭缩放
		// webSettings.setBuiltInZoomControls(false);
		// webSettings.setSupportZoom(false);
		// webSettings.setDisplayZoomControls(false);
		webSettings.setDefaultTextEncodingName("UTF-8");
		wv_show.setBackgroundColor(0); // 设置背景色  
		wv_show.getBackground().setAlpha(0); // 设置填充透明度 范围：0-255 
		// 支持javascript
		webSettings.setJavaScriptEnabled(true);
		// 设置可以支持缩放
		webSettings.setSupportZoom(true);
		// 设置出现缩放工具
		webSettings.setBuiltInZoomControls(true);
		// 扩大比例的缩放
		webSettings.setUseWideViewPort(true);
		// 自适应屏幕
		webSettings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
		webSettings.setLoadWithOverviewMode(true);
		mLoadingDialog = new CustomDialog(this, LayoutInflater.from(this).inflate(R.layout.dialog_loading, null));

		mTipsWorkerTask = new TipsWorkerTask(wv_show, mLoadingDialog, tip);

		if (tip != null) {
			if (TextUtils.isEmpty(tip.getTipName())) {
				setTitle(R.string.cap_database_category_tips);
			} else {
				setTitle(tip.getTipName());
			}
			mTipsWorkerTask.execute(tip.getUrl());
			// wv_show.loadUrl(tip.getUrl());
		} else {
			finish();
		}
	}

	public void onDestroy() {
		super.onDestroy();
		if (mTipsWorkerTask != null) {
			mTipsWorkerTask.cancel(true);
		}
	}

	public class TipsWorkerTask extends MyAsyncTask<String, Integer, String> {

		private CustomDialog loadingView;
		private Tip tip;
		private WebView wv_show;

		public TipsWorkerTask(WebView wv_show, CustomDialog loadingView, Tip tip) {
			this.wv_show = wv_show;
			this.loadingView = loadingView;
			this.tip = tip;
		}

		// onPreExecute方法用于在执行后台任务前做一些UI操作
		@Override
		protected void onPreExecute() {
			loadingView.show();
		}

		// doInBackground方法内部执行后台任务,不可在此方法内修改UI
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

		// onProgressUpdate方法用于更新进度信息
		@Override
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);

		}

		// onCancelled方法用于在取消执行中的任务时更改UI
		@Override
		protected void onCancelled(String s) {
			super.onCancelled(s);
			loadingView.dismiss();
		}

		// onPostExecute方法用于在执行完后台任务后更新UI,显示结果
		@Override
		protected void onPostExecute(final String jsonResult) {
			if (!TextUtils.isEmpty(jsonResult)) {
				Document doc = Jsoup.parse(jsonResult);
				Element element = doc.getElementsByAttributeValue("class", "news_main").first();
				String str = element.html();
				StringBuilder buf = new StringBuilder(str.length());
				   for (char c : str.toCharArray()) {
				       switch (c) {
				         case '#':  buf.append("#"); break;
				         case '%':  buf.append("%"); break;
				         case '\'': buf.append("'"); break;
				         case '?':  buf.append("?"); break;               
				         default:
				           buf.append(c);
				           break;
				       }
				   } 				
				wv_show.loadDataWithBaseURL(null, "<font color=\"white\">" + buf.toString().replace("&nbsp;", "") +"</font>", "text/html", "UTF-8", null);
//				tv_show.setText(Html.fromHtml(element.html(), new URLImageParser(tv_show,
//						TipDetailActivity.this), null));
			} else {

			}
			loadingView.dismiss();
		}
	}

	@Override
	public int getLayoutID() {
		// TODO Auto-generated method stub
		return R.layout.activity_strategy_detail;
	}

	@Override
	public void initDataBundle(Bundle bundle) {
		// TODO Auto-generated method stub
		tip = (Tip) bundle.getSerializable(Intent.EXTRA_TIP);
	}

	@Override
	protected JsonModel initJsonModel() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
