package com.kituri.tankmmdatabase.ui.common;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kituri.tankmmdatabase.R;
import com.kituri.tankmmdatabase.widget.ActionBar;

/**
 * Created by guanzhe on 14-10-15.
 */
public abstract class BaseFragmentActivity extends com.kituri.app.ui.BaseFragmentActivity implements ActionBarInterface {
	// protected TextView titleTv,rightTv,leftTv;
	// protected ImageView leftIv,rightIv;
	// // protected AnimateFirstDisplayListener
	// mAnimateFirstDisplayListener;//imageLoader的监听事件，用来监听第一次加载图片
	protected int mLayoutId = 0;// 布局Id
	protected boolean mHasTitle = true;// 是否有Title Bar
	// // protected View rootView;
	 protected ImageView noDataIv;
	 protected TextView noDataTv;
	 protected Button noDataBtn;
	 protected LinearLayout noDataLy;

	 protected ActionBar actionBar;

	/**
	 * 构造方法，默认为有公用title
	 *
	 * @param resId
	 *            布局文件id
	 */
	public BaseFragmentActivity(int resId) {
		mLayoutId = resId;
		mHasTitle = true;
	}

	/**
	 * 构造方法，手动设置有无公用title
	 *
	 * @param resId
	 *            布局文件id
	 * @param hasTitle
	 *            是否有公用title
	 */
	public BaseFragmentActivity(int resId, boolean hasTitle) {
		mLayoutId = resId;
		mHasTitle = hasTitle;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(mLayoutId);
		// FinalActivity.initInjectedView(this);
		// rootView = findViewById(mLayoutId);
		getData();// 这个要放在前面
		initTitle();
		initView();
	}

	/**
	 * 获取从上个页面传递过来的数据，或者需要从本地读取的数据，如用户数据。
	 */
	protected abstract void getData();

	/**
	 * 初始化控件，并设置监听事件
	 */
	protected abstract void initView();

	/**
	 * 初始化标题栏上的左右按钮以及标题Text，同时添加左按钮的关闭事件
	 */
	protected void initTitle() {
		if (!mHasTitle) {
			return;
		}
		actionBar = (ActionBar) findViewById(R.id.action_bar);
		// leftIv = (ImageView) this.findViewById(R.id.iv_left);
		// leftIv.setOnClickListener(new View.OnClickListener() {
		// @Override
		// public void onClick(View v) {
		// finish();
		// overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
		// }
		// });
		// rightIv = (ImageView) this.findViewById(R.id.iv_right);
		// leftTv = (TextView) this.findViewById(R.id.tv_left);
		// rightTv = (TextView) this.findViewById(R.id.tv_right);
		// titleTv = (TextView) this.findViewById(R.id.tv_title);
		 noDataTv = (TextView) this.findViewById(R.id.mt_common_no_data_tv);
		 noDataIv = (ImageView) this.findViewById(R.id.mt_common_no_data_iv);
		 noDataBtn = (Button) this.findViewById(R.id.mt_common_no_data_btn);
		 noDataLy = (LinearLayout) this.findViewById(R.id.mt_no_data_ly);
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}

	/**
	 * 隐藏输入法面板
	 */

	public void hideKeyboard(View view) {
		InputMethodManager imm = ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE));
		if (imm != null && getCurrentFocus() != null) {
			imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		}
	}

	// /**
	// * 网络请求方法
	// * @param url
	// * @param params
	// * @param callback
	// */
	// public void getNetData(String url,AjaxParams params,ProjectAjaxCallBack
	// callback){
	// FinalHttpFactory.getFinalHttp().post(url,params,callback);
	// }

	public void onResume() {
		super.onResume();
		// 友盟统计
		//MobclickAgent.onResume(this);
	}

	public void onPause() {
		super.onPause();
		// 友盟统计
		//MobclickAgent.onPause(this);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void hideActionBar() {
		actionBar.hideActionBar();
	}

	@Override
	public void setHomeAction(boolean isHome) {
		actionBar.setHomeAction(isHome);
	}

	@Override
	public void setTitle(int titleId) {
		actionBar.setTitle(titleId);
	}

	@Override
	public void setTitle(String text) {
		actionBar.setTitle(text);
	}

	@Override
	public void setImageLogo(int resid) {
		actionBar.setImageLogo(resid);
	}

	@Override
	public View setMyView(int layoutid) {
		return actionBar.setMyView(layoutid);
	}
	
	@Override
	public void addAction(int view_id, View item) {
		actionBar.addAction(view_id, item);
	}

	@Override
	public void addAction(int view_id, int res_id) {
		actionBar.addAction(view_id, res_id);
	}

	@Override
	public void addButtonAction(int view_id, int res_id) {
		actionBar.addButtonAction(view_id, res_id, 0);
	}
	
	@Override
	public void addButtonAction(int view_id, int res_id, int bg_id) {
		actionBar.addButtonAction(view_id, res_id, bg_id);
	}

	@Override
	public void addRefreshAction(int view_id, int res_id) {
		actionBar.addRefreshAction(view_id, res_id);
	}

	@Override
	public void setProgressBarVisibility(int visibility) {
		actionBar.setProgressBarVisibility(visibility);
	}

	@Override
	public void removeActionAt(int index) {
		actionBar.removeActionAt(index);
	}

	@Override
	public void removeAllActions() {
		actionBar.removeAllActions();
	}

	@Override
	public void onActionBarItem(int itemid) {
		if (R.id.actionbar_btn_home == itemid) {
			finish();
			overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
		}
	}

	public void onActionbarClick(View view) {
		switch (view.getId()) {
		case R.id.actionbar_btn_home:
			onActionBarItem(R.id.actionbar_btn_home);
			break;
		case R.id.actionbar_item: {
			int id = (Integer) view.getTag();
			onActionBarItem(id);
			break;
		}
		}
	}
}
