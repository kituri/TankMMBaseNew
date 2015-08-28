/*
 * Copyright (C) 2010 Johan Nilsson <http://markupartist.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.kituri.tankmmdatabase.widget;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Notification.Action;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kituri.app.widget.doubleClick.DoubleClickRelativeLayout;
import com.kituri.tankmmdatabase.R;

/**
 * 标题事件栏<br>
 */
public class ActionBar extends DoubleClickRelativeLayout {

	private LayoutInflater mInflater;
	private RelativeLayout mBarView;
	private TextView mTitleView;
	private ImageView mLogoView;
	private LinearLayout mActionsView;
	private Button mHomeBtn;
	private RelativeLayout mHomeLayout;
	private ProgressBar mProgress;

	private RelativeLayout mActionbarContent;

	@SuppressLint("InflateParams")
	public ActionBar(Context context, AttributeSet attrs) {
		super(context, attrs);
//		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.action_bar);
//		a.recycle();

		if (isInEditMode()) {
			return;
		}
		
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		mBarView = (RelativeLayout) mInflater.inflate(R.layout.actionbar, null);
		addView(mBarView);

		mHomeLayout = (RelativeLayout) mBarView.findViewById(R.id.actionbar_home_bg);
		mHomeBtn = (Button) mBarView.findViewById(R.id.actionbar_btn_home);

		mTitleView = (TextView) mBarView.findViewById(R.id.actionbar_title);
		mLogoView = (ImageView) mBarView.findViewById(R.id.actionbar_logo);
		mActionsView = (LinearLayout) mBarView.findViewById(R.id.actionbar_actions);

		mProgress = (ProgressBar) mBarView.findViewById(R.id.actionbar_progress);

		mActionbarContent = (RelativeLayout) mBarView.findViewById(R.id.action_content);

	}

	private ArrayList<View> myContentLists = new ArrayList<View>();

	public void clearAll() {
		clearHomeAction();
		mTitleView.setText("");
		mTitleView.setOnClickListener(null);
		mLogoView.setImageDrawable(null);
		mLogoView.setVisibility(GONE);

		mActionsView.removeAllViews();

		clearActionbarContent();
	}
	
	public void hideActionBar() {
		mBarView.setVisibility(View.GONE);
	}
	
	public void setHomeAction(boolean isHome) {
		mHomeLayout.setVisibility(isHome ? GONE : VISIBLE);
	}
	
	public void setHomeAction(int drawable_id) {
		mHomeBtn.setBackgroundResource(drawable_id);
		mHomeLayout.setVisibility(View.VISIBLE);
	}

	@SuppressWarnings("deprecation")
	public void clearHomeAction() {
		mHomeBtn.setBackgroundDrawable(null);
		mHomeBtn.setOnClickListener(null);
		mHomeLayout.setVisibility(GONE);
	}

	public void setTitle(CharSequence title) {
		mTitleView.setText(title);
	}

	public void setTitle(int resid) {
		mTitleView.setText(resid);
	}

	public void setImageLogo(int resid) {
		if (mLogoView.getVisibility() != VISIBLE) {
			mLogoView.setVisibility(VISIBLE);
		}
		mLogoView.setImageResource(resid);
	}

	/**
	 * 添加我的View在action bar的前面， 且只能加一个
	 * */
	public View setMyView(int layoutid) {
		View view = mInflater.inflate(layoutid, mActionbarContent, false);
		if (view != null) {
			clearActionbarContent();
			myContentLists.add(view);
			mActionbarContent.addView(view);
		}

		return view;
	}

	private void clearActionbarContent() {
		if (!myContentLists.isEmpty()) {
			for (View v : myContentLists) {
				mActionbarContent.removeView(v);
			}
			myContentLists.clear();
		}
	}

	/**
	 * Set the enabled state of the progress bar.
	 * 
	 * @param One
	 *            of {@link View#VISIBLE}, {@link View#INVISIBLE}, or {@link View#GONE}.
	 */
	public void setProgressBarVisibility(int visibility) {
		int count = mActionsView.getChildCount();
//		Log.e("ProgressBar", "visibility is "+(visibility == VISIBLE ? "VISIBLE" : "GONE")+" count is "+count+" "+visibility);
		if (count <= 0) {
			mProgress.setVisibility(visibility);
			return;
		}
		mProgress.setVisibility(GONE);
		for (int i = count - 1; i >= 0; i--) {
			View view = mActionsView.getChildAt(i);
			ProgressBar progressbar = (ProgressBar) view.findViewById(R.id.actionbar_progress);
			if (progressbar != null) {
				View item = view.findViewById(R.id.actionbar_item);
				if (visibility == VISIBLE) {
					if (item != null)item.setVisibility(View.GONE);
					progressbar.setVisibility(VISIBLE);
				} else {
					if (item != null)item.setVisibility(View.VISIBLE);
					progressbar.setVisibility(GONE);
				}
				break;
			}
		}
	}

	/**
	 * Function to set a click listener for Title TextView
	 * 
	 * @param listener
	 *            the onClickListener
	 */
	public void setOnTitleClickListener(OnClickListener listener) {
		mTitleView.setOnClickListener(listener);
	}
	
	public View addAction(int view_id, View item) {
		final int index = mActionsView.getChildCount();
		mActionsView.addView(item, index);
		return item;
	}

	/**
	 * Adds a new {@link Action}.
	 * 
	 * @param action
	 *            the action to add
	 */
	public View addAction(int view_id, int res_id) {
		final int index = mActionsView.getChildCount();
		return addAction(view_id, res_id, index);
	}

	public View addRefreshAction(int view_id, int res_id) {
		final int index = mActionsView.getChildCount();
		return addRefreshAction(view_id, res_id, index);
	}

//	public View addButtonAction(int view_id, int res_id) {
//		final int index = mActionsView.getChildCount();
//		return addButtonAction(view_id, res_id, index);
//	}
	public View addButtonAction(int view_id, int res_id, int bg_id) {
		final int index = mActionsView.getChildCount();
		return addButtonAction(view_id, res_id, bg_id, index);
	}

	/**
	 * Adds a new {@link Action} at the specified index.
	 * 
	 * @param action
	 *            the action to add
	 * @param index
	 *            the position at which to add the action
	 */
	public View addAction(int view_id, int res_id, int index) {
		View item = inflateAction(view_id, res_id);
		mActionsView.addView(item, index);
		return item;
	}

	public View addRefreshAction(int view_id, int res_id, int index) {
		View item = inflateRefreshAction(view_id, res_id);
		mActionsView.addView(item, index);
		return item;
	}

	public View addButtonAction(int view_id, int res_id, int bg_id, int index) {
		View item = inflateButtonAction(view_id, res_id, bg_id);
		mActionsView.addView(item, index);
		return item;
	}

	public void setItemContent(int index, int resid) {
		if (resid > 0 && index >= 0 && index < mActionsView.getChildCount()) {
			View view = mActionsView.getChildAt(index);
			if (view instanceof Button) {
				((Button) view).setText(resid);
			} else if (view instanceof ImageButton) {
				((ImageButton) view).setImageResource(resid);
			}
		}
	}

	public void setItemBackground(int index, int resid) {
		if (resid > 0 && index >= 0 && index < mActionsView.getChildCount()) {
			View view = mActionsView.getChildAt(index);
			view.setBackgroundResource(resid);
		}
	}

	/**
	 * Removes all action views from this action bar
	 */
	public void removeAllActions() {
		mActionsView.removeAllViews();
	}

	/**
	 * Remove a action from the action bar.
	 * 
	 * @param index
	 *            position of action to remove
	 */
	public void removeActionAt(int index) {
		mActionsView.removeViewAt(index);
	}

	/**
	 * Remove a action from the action bar.
	 * 
	 * @param action
	 *            The action to remove
	 */
	public void removeAction(int view_id) {
		int childCount = mActionsView.getChildCount();
		for (int i = 0; i < childCount; i++) {
			View view = mActionsView.getChildAt(i);
			if (view != null) {
				if (view.getId() == view_id) {
					mActionsView.removeView(view);
				}
			}
		}
	}

	/**
	 * Returns the number of actions currently registered with the action bar.
	 * 
	 * @return action count
	 */
	public int getActionCount() {
		return mActionsView.getChildCount();
	}

	/**
	 * Inflates a {@link View} with the given {@link Action}.
	 * 
	 * @param action
	 *            the action to inflate
	 * @return a view
	 */
	private View inflateAction(int view_id, int res_id) {
		View view = mInflater.inflate(R.layout.actionbar_right_item, mActionsView, false);

		ImageButton labelView = (ImageButton) view.findViewById(R.id.actionbar_item);
		labelView.setImageResource(res_id);
		labelView.setTag(view_id);
		return view;
	}

	// 有且只能有一个刷新按钮
	private View inflateRefreshAction(int view_id, int res_id) {
		View view = mInflater.inflate(R.layout.actionbar_right_refresh_item, mActionsView, false);

		view.findViewById(R.id.actionbar_progress).setVisibility(View.GONE);

		ImageButton labelView = (ImageButton) view.findViewById(R.id.actionbar_item);
		labelView.setImageResource(res_id);
		labelView.setTag(view_id);
		
		return view;
	}

	private View inflateButtonAction(int view_id, int res_id, int bg_id) {
		View view = mInflater.inflate(R.layout.actionbar_right_button_item, mActionsView, false);
		//mt_btn_white_stroke
		Button labelView = (Button) view.findViewById(R.id.actionbar_item);
		labelView.setText(res_id);
		labelView.setTag(view_id);
		if (bg_id > 0) {
			labelView.setBackgroundResource(bg_id);
		}
		return view;
	}

}
