package com.kituri.tankmmdatabase.widget;

import com.kituri.app.push.PsPushUserData;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;

public class TankToggleButton extends com.kituri.app.widget.XTextView implements OnClickListener{
	
//    <com.kituri.tankmmdatabase.widget.TankToggleButton
//    android:id="@+id/tv_mode"
//    android:layout_width="64dp"
//    android:layout_height="match_parent"
//    android:layout_marginLeft="10dp"
//    android:background="@drawable/btn_green_red_high_light_selector"
//    android:gravity="center"
//    android:text="@string/cap_btn_equip_normal_mode"
//    android:textColor="@android:color/white"
//    android:textSize="@dimen/tv_size_medium_big" />
	
	private String mTag = "";
	private Boolean isSelect = false;
	private String selectedText = "";
	private String defaultSelectText = "";
	
	public TankToggleButton(Context context) {
		this(context, null);
	}

	public TankToggleButton(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public TankToggleButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);		
	}
	
	public Boolean getIsSelect() {
		return isSelect;
	}

	public void setToggleTag(final String tag) {
		this.mTag = tag;		
		this.isSelect = PsPushUserData.getData(getContext(), mTag, false);
		setSelected(isSelect);
		updateStyle();
		super.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setSelected(!isSelect);
				PsPushUserData.setData(getContext(), mTag, !isSelect);
				isSelect = PsPushUserData.getData(getContext(), mTag, false);
				updateStyle();
			}
		});
	}

	private void updateStyle(){
		if(isSelect){
			if(TextUtils.isEmpty(selectedText)){
				setText("");
			}else{
				setText(selectedText);
			}
		}else{
			if(TextUtils.isEmpty(defaultSelectText)){
				setText("");
			}else{
				setText(defaultSelectText);
			}
		}
	}
	
	public void setSelectText(String defaultSelectText, String selectedText) {
		this.defaultSelectText = defaultSelectText;
		this.selectedText = selectedText;
		updateStyle();
	}
	
	public void setSelectText(int defaultSelectTextResId, int selectedTextResId) {
		this.defaultSelectText = getContext().getString(defaultSelectTextResId);
		this.selectedText = getContext().getString(selectedTextResId);
		updateStyle();
	}
	
	//			tv_mode.setSelected(!select);
	//tv_mode.setTag(!select);

	@Override
	public void setOnClickListener(OnClickListener l){
		
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
	
}
