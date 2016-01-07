package com.kituri.tankmmdatabase.widget.dialog;

import com.kituri.app.data.Entry;
import com.kituri.app.push.PsPushUserData;
import com.kituri.app.widget.Populatable;
import com.kituri.app.widget.Selectable;
import com.kituri.app.widget.SelectionListener;
import com.kituri.tankmmdatabase.R;
import com.kituri.tankmmdatabase.data.tank.TankData;
import com.kituri.tankmmdatabase.ui.metaphysics.MetaphysicsActivity;
import com.kituri.tankmmdatabase.utils.TransformerUtils;
import com.kituri.tankmmdatabase.utils.Utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DialogMetaphysicsResult extends RelativeLayout implements Selectable<Entry>, View.OnClickListener, SelectionListener<Entry>, Populatable<TankData>{

	private SelectionListener<Entry> mListener;
	
	private TankData mTankData;
	
	private TextView tv_player_name;
	
	private ImageView iv_nationality;
	private ImageView iv_type;
	private TextView tv_name;
	private TextView tv_class;
	private ImageView iv_head;
	
	private ImageView iv_star_lv02;
	private ImageView iv_star_lv03;
	
	public DialogMetaphysicsResult(Context context){
		this(context,null);
	}
	
	public DialogMetaphysicsResult(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	private void initView() {
		View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_get_tank, null);
		tv_player_name = (TextView) view.findViewById(R.id.tv_player_name);
		iv_nationality = (ImageView) view.findViewById(R.id.iv_nationality);
		iv_type = (ImageView) view.findViewById(R.id.iv_type);
		tv_name = (TextView) view.findViewById(R.id.tv_name);
		tv_class = (TextView) view.findViewById(R.id.tv_class);		
		iv_head = (ImageView) view.findViewById(R.id.iv_head);		
		iv_star_lv02 = (ImageView) view.findViewById(R.id.iv_star_lv02);
		iv_star_lv03 = (ImageView) view.findViewById(R.id.iv_star_lv03);
		view.findViewById(R.id.btn_ok).setOnClickListener(this);
		view.findViewById(R.id.btn_tank_detail).setOnClickListener(this);
		addView(view, new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));		
	}
	
	private void setData(TankData data) {
		//PsPushUserData.setData(this, METAPHYSICS_NAME, et_name.getText().toString());
		tv_player_name.setText(String.format(getContext().getString
				(R.string.cap_metaphysics_dialog_title), PsPushUserData.getData(getContext(),
						MetaphysicsActivity.METAPHYSICS_NAME, "")));
		Utils.setAssetsImage(getContext(), data.getHeadPic(), iv_head);
		iv_nationality.setImageResource(TransformerUtils.getTankNationalityIconResId(getContext(), data));
		iv_type.setImageResource(TransformerUtils.getTankTypeIconResId(getContext(), data));
		tv_name.setText(data.getTankName());
		tv_class.setText(data.getTankClass());	
		switch (data.getTankStar()) {
		case 1:
			iv_star_lv02.setVisibility(View.INVISIBLE);
			iv_star_lv03.setVisibility(View.INVISIBLE);			
			break;
		case 2:
			iv_star_lv02.setVisibility(View.VISIBLE);
			iv_star_lv03.setVisibility(View.INVISIBLE);	
			break;
		case 3:
			iv_star_lv02.setVisibility(View.VISIBLE);
			iv_star_lv03.setVisibility(View.VISIBLE);	
			break;
		default:
			iv_star_lv02.setVisibility(View.INVISIBLE);
			iv_star_lv03.setVisibility(View.INVISIBLE);	
			break;
		}
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_ok:
			if(mListener != null){
				mTankData.setIntent(new com.kituri.tankmmdatabase.model.Intent(
						com.kituri.tankmmdatabase.model.Intent.ACTION_DIALOG_DISMISS));
				mListener.onSelectionChanged(mTankData, true);
			}
			break;
		case R.id.btn_tank_detail:
			if(mListener != null){
				mTankData.setIntent(new com.kituri.tankmmdatabase.model.Intent(
						com.kituri.tankmmdatabase.model.Intent.ACTION_TANK_DETAIL));
				mListener.onSelectionChanged(mTankData, true);
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
	public void onSelectionChanged(Entry item, boolean selected) {
		// TODO Auto-generated method stub
		if(item == null){
			return;
		}
		//if(item.getIntent().getAction().equals(Intent.ACTION_CONDITIONAL_QUERY)){
			if(mListener != null){
				mListener.onSelectionChanged(item, true);
			}
		//}
	}

	@Override
	public void populate(TankData data) {
		// TODO Auto-generated method stub
		if(data != null){
			mTankData = data;
			setData(mTankData);
		}
	}
	
}
