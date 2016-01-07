package com.kituri.tankmmdatabase.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;

public class ColorNumberPicker  extends NumberPicker {
	
	 public ColorNumberPicker(Context context) {
	        super(context);
	    }
	 
	    public ColorNumberPicker(Context context, AttributeSet attrs) {
	        super(context, attrs);
	    }
	 
	    public ColorNumberPicker(Context context, AttributeSet attrs, int defStyleAttr) {
	        super(context, attrs, defStyleAttr);
	    }
	 
	    @Override
	    public void addView(View child) {
	        super.addView(child);
	        updateView(child);
	    }
	 
	    @Override
	    public void addView(View child, int index,android.view.ViewGroup.LayoutParams params) {
	        super.addView(child, index, params);
	        updateView(child);
	    }
	 
	    @Override
	    public void addView(View child, android.view.ViewGroup.LayoutParams params) {
	        super.addView(child, params);
	        updateView(child);
	    }
	 
	    public void updateView(View view) {
	        if (view instanceof EditText) {
	            //这里修改字体的属性
	            ((EditText) view).setTextColor(getResources().getColor(android.R.color.white));
//	            ((EditText) view).setTextSize();
	        }
	    }
}
