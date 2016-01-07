package com.kituri.tankmmdatabase.widget.dialog;

import com.kituri.app.data.Entry;
import com.kituri.app.widget.Populatable;
import com.kituri.app.widget.Selectable;
import com.kituri.app.widget.SelectionListener;
import com.kituri.tankmmdatabase.R;
import com.kituri.tankmmdatabase.data.equip.EquipCountData;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

public class DialogInputNumber extends RelativeLayout implements Selectable<Entry>, View.OnClickListener, Populatable<EquipCountData>{

	private SelectionListener<Entry> mListener;
	
	private EquipCountData mData;
	private EditText et_number;
	private TextView tv_name;
	
	public DialogInputNumber(Context context){
		this(context,null);
	}
	
	public DialogInputNumber(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	private void initView() {
		View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_input_number, null);
		et_number = (EditText) view.findViewById(R.id.et_number);
		tv_name = (TextView) view.findViewById(R.id.tv_name);
		view.findViewById(R.id.btn_ok).setOnClickListener(this);
		view.findViewById(R.id.btn_close).setOnClickListener(this);
		addView(view, new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));	
		et_number.setOnEditorActionListener(new OnEditorActionListener() {
			
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				// TODO Auto-generated method stub
				if (actionId == EditorInfo.IME_ACTION_DONE) {
					inputOk();
				}
				return false;
			}
		});
		
	}
	
	private void setData(EquipCountData data) {
		if(data.getCounts() != 0){
			et_number.setText(String.valueOf(data.getCounts()));
		}else{
			et_number.setText("");
		}		
		tv_name.setText(String.format(getContext().
				getString(R.string.cap_equip_input_number), data.getLv()));
		//cap_equip_input_number
	}
	
	private void inputOk(){
		if(TextUtils.isEmpty(et_number.getText().toString())){
			//Toast.makeText(getContext(), getContext().getString(R.string.msg_input_is_nothing), Toast.LENGTH_SHORT).show();
			//return;
			mData.setCounts(0);
		}else{
			mData.setCounts(Integer.parseInt(et_number.getText().toString()));
		}
		
		mData.setIntent(new com.kituri.tankmmdatabase.model.Intent(
				com.kituri.tankmmdatabase.model.Intent.ACTION_EQUIP_COUNTS_CHANGE));
		mListener.onSelectionChanged(mData, true);
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_ok:
			if(mListener != null){
				inputOk();				
			}
			break;
		case R.id.btn_close:
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

	@Override
	public void populate(EquipCountData data) {
		// TODO Auto-generated method stub
		if(data != null){
			mData = data;
			setData(mData);
		}
	}
	
}
