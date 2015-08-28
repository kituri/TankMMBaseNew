package com.kituri.tankmmdatabase.ui.common;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.kituri.tankmmdatabase.R;
import com.kituri.tankmmdatabase.widget.ActionBar;

/**
 * Created by guanzhe on 14-10-11.
 */
public abstract class BaseFragment extends com.kituri.app.ui.BaseFragment implements ActionBarInterface {
	// protected TextView titleTv,rightTv;
	// protected ImageView leftIv,rightIv;
	protected View rootView;// 根视图
	protected int layoutId;
	protected boolean mHasTitle = true;// 是否有Title Bar
	protected int mStyle = 0;// 是否有Title Bar

	protected ActionBar actionBar;

	/**
	 * 构造方法，默认为有公用title
	 *
	 * @param resId
	 *            布局文件id
	 */
	public BaseFragment(int resId) {
		layoutId = resId;
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
	public BaseFragment(int resId, boolean hasTitle) {
		layoutId = resId;
		mHasTitle = hasTitle;
	}

	public BaseFragment(int resId, int style) {
		layoutId = resId;
		mHasTitle = true;
		this.mStyle = style;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (mStyle == 0) {
			rootView = inflater.inflate(layoutId, null, false);
		} else {
			// create ContextThemeWrapper from the original Activity Context
			// with the custom theme
			final Context contextThemeWrapper = new ContextThemeWrapper(getActivity(), mStyle);
			// clone the inflater using the ContextThemeWrapper
			LayoutInflater localInflater = inflater.cloneInContext(contextThemeWrapper);
			// inflate the layout using the cloned inflater, not default
			// inflater
			rootView = localInflater.inflate(layoutId, null, false);
		}

		// FinalActivity.initInjectedView(this, rootView);
		initTitle();
		return rootView;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initView();
	}

	/**
	 * 初始化标题栏上的左右按钮以及标题Text，同时添加左按钮的关闭事件
	 */
	protected void initTitle() {
		if (!mHasTitle) {
			return;
		}
		actionBar = (ActionBar) findViewById(R.id.action_bar);
		Button actionbar_home_btn = (Button) findViewById(R.id.actionbar_btn_home);
		if (actionbar_home_btn != null)
		actionbar_home_btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				onActionBarItem(R.id.actionbar_btn_home);
			}
		});
	}

	/**
	 * Look for a child view with the given id. If this view has the given id,
	 * return this view.
	 *
	 * @param id
	 *            The id to search for.
	 * @return The view that has the given id in the hierarchy or null
	 */
	public final View findViewById(int id) {
		if (id < 0) {
			return null;
		}
		return rootView.findViewById(id);
	}

//	/**
//	 * 隐藏输入法面板
//	 */
//
//	public void hideKeyboard(View view) {
//		InputMethodManager imm = ((InputMethodManager) getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE));
//		if (imm != null && getActivity().getCurrentFocus() != null) {
//			imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
//		}
//	}

	/**
	 * 初始化控件，并设置监听事件
	 */
	protected abstract void initView();

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
		setOnActionItemClick(item);
	}

	@Override
	public void addAction(int view_id, int res_id) {
		View item = actionBar.addAction(view_id, res_id);
		setOnActionItemClick(item);
	}

	@Override
	public void addButtonAction(int view_id, int res_id) {
		addButtonAction(view_id, res_id, 0);
	}
	@Override
	public void addButtonAction(int view_id, int res_id, int bg_id) {
		View item = actionBar.addButtonAction(view_id, res_id, bg_id);
		setOnActionItemClick(item);
	}

	@Override
	public void addRefreshAction(int view_id, int res_id) {
		View item = actionBar.addRefreshAction(view_id, res_id);
		setOnActionItemClick(item);
	}

	private void setOnActionItemClick(View item) {
		item.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				int id = (Integer) v.getTag();
				onActionBarItem(id);
			}
		});
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
			getActivity().finish();
			getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
		}
	}

}
