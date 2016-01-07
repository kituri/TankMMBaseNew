package com.kituri.tankmmdatabase.widget.dialog;

import com.kituri.app.data.Entry;
import com.kituri.app.widget.Selectable;
import com.kituri.app.widget.SelectionListener;
import com.kituri.tankmmdatabase.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DialogVerUpdateHistory extends RelativeLayout implements Selectable<Entry>, View.OnClickListener{

	private SelectionListener<Entry> mListener;
	
	private TextView tv_update_history;
	private Entry mData = new Entry();
	
	public DialogVerUpdateHistory(Context context){
		this(context,null);
	}
	
	public DialogVerUpdateHistory(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	private void initView() {
		View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_ver_update_history, null);
		tv_update_history = (TextView) view.findViewById(R.id.tv_update_history);
		view.findViewById(R.id.iv_close).setOnClickListener(this);
		this.addView(view, new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
		setData();
	}
	
	private void setData(){
		String ver = getContext().getResources().getStringArray(R.array.ver_update_history)[0];
		tv_update_history.setText(ver);
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.iv_close:
			if(mListener != null){
				mData.setIntent(new com.kituri.tankmmdatabase.model.Intent(
						com.kituri.tankmmdatabase.model.Intent.ACTION_DIALOG_DISMISS));
				mListener.onSelectionChanged(mData, true);
			}
			break;

		default:
			break;
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
