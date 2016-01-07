package com.kituri.tankmmdatabase.widget.dialog;

import com.kituri.app.data.Entry;
import com.kituri.app.widget.Populatable;
import com.kituri.app.widget.Selectable;
import com.kituri.app.widget.SelectionListener;
import com.kituri.tankmmdatabase.R;
import com.kituri.tankmmdatabase.controller.TipManager;
import com.kituri.tankmmdatabase.data.tip.TerrainData;
import com.kituri.tankmmdatabase.model.Intent;
import com.kituri.tankmmdatabase.utils.Utils;

import android.content.Context;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DialogTerrainDetail extends RelativeLayout implements Selectable<Entry>, View.OnClickListener, Populatable<TerrainData>{

	private SelectionListener<Entry> mListener;
	
	private TerrainData mData;
	private TextView tv_name;
	private TextView tv_effect01;
	private TextView tv_effect02;
	private TextView tv_effect03;
	private TextView tv_resistance01;
	private TextView tv_resistance02;
	private TextView tv_resistance03;
	private ImageView iv_terrain;
	
	public DialogTerrainDetail(Context context){
		this(context,null);
	}
	
	public DialogTerrainDetail(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	private void initView() {
		View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_terrain_detail, null);
		tv_name = (TextView) view.findViewById(R.id.tv_name);
		tv_effect01 = (TextView) view.findViewById(R.id.tv_effect01);
		tv_effect02 = (TextView) view.findViewById(R.id.tv_effect02);
		tv_effect03 = (TextView) view.findViewById(R.id.tv_effect03);
		tv_resistance01 = (TextView) view.findViewById(R.id.tv_resistance01);
		tv_resistance02 = (TextView) view.findViewById(R.id.tv_resistance02);
		tv_resistance03 = (TextView) view.findViewById(R.id.tv_resistance03);
		iv_terrain = (ImageView) view.findViewById(R.id.iv_ic_terrain);
		
		view.findViewById(R.id.btn_ok).setOnClickListener(this);
		this.addView(view);
	}
	
	private void setData(TerrainData data){
		tv_name.setText(data.getTerrainName());
		//textview.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG ); //中间横线
		//textview.getPaint().setAntiAlias(true);// 抗锯齿
		tv_effect01.setText("");	
		tv_effect02.setText("");
		tv_effect03.setText("");
		tv_effect01.setTextColor(getContext().getResources().getColor(R.color.td_highlight_text_direction));	
		tv_effect02.setTextColor(getContext().getResources().getColor(R.color.td_highlight_text_direction));	
		tv_effect03.setTextColor(getContext().getResources().getColor(R.color.td_highlight_text_direction));	
		tv_resistance01.setText("");
		tv_resistance02.setText("");
		tv_resistance03.setText("");
		tv_resistance01.setTextColor(getContext().getResources().getColor(R.color.td_highlight_text_direction));	
		tv_resistance02.setTextColor(getContext().getResources().getColor(R.color.td_highlight_text_direction));	
		tv_resistance03.setTextColor(getContext().getResources().getColor(R.color.td_highlight_text_direction));	
		
		Boolean[] resistances =  TipManager.getTerrainResistanceList(data);
		Utils.setAssetsImage(getContext(), data.getPngPath(), iv_terrain);
		for(int i = 0; i < data.getDebuffInfo().size(); i++){
			switch (i) {
			case 0:
				tv_effect01.setText(data.getDebuffInfo().get(i));
				if(resistances[0]){
					tv_effect01.setTextColor(getContext().getResources().getColor(R.color.td_highlight_text));	
				}
				break;
			case 1:
				tv_effect02.setText(data.getDebuffInfo().get(i));
				if(resistances[1]){
					tv_effect02.setTextColor(getContext().getResources().getColor(R.color.td_highlight_text));	
				}
				break;
			case 2:
				tv_effect03.setText(data.getDebuffInfo().get(i));
				if(resistances[2]){
					tv_effect03.setTextColor(getContext().getResources().getColor(R.color.td_highlight_text));	
				}
				break;
			}
		}
		
		for(int i = 0; i < data.getDebuff().size(); i++){	
			switch (i) {
			case 0:
				if(TextUtils.isEmpty(data.getDebuff().get(i))){
					continue;
				}
				tv_resistance01.setText(String.format(getContext().getString(R.string.cap_tech_sp_name), data.getDebuff().get(i)));
				if(resistances[0]){
					tv_resistance01.setTextColor(getContext().getResources().getColor(R.color.td_highlight_text));	
				}
				break;
			case 1:
				if(TextUtils.isEmpty(data.getDebuff().get(i))){
					continue;
				}
				tv_resistance02.setText(String.format(getContext().getString(R.string.cap_tech_sp_name), data.getDebuff().get(i)));
				if(resistances[1]){
					tv_resistance02.setTextColor(getContext().getResources().getColor(R.color.td_highlight_text));	
				}
				break;
			case 2:
				if(TextUtils.isEmpty(data.getDebuff().get(i))){
					continue;
				}
				tv_resistance03.setText(String.format(getContext().getString(R.string.cap_tech_sp_name), data.getDebuff().get(i)));
				if(resistances[2]){
					tv_resistance03.setTextColor(getContext().getResources().getColor(R.color.td_highlight_text));	
				}
				break;
			}
		}
		
//		StringBuffer sbDebuffInfo = new StringBuffer();
//		for(String debuff : data.getDebuff()){
//			if(TextUtils.isEmpty(debuff)){
//				continue;
//			}
//			sbDebuffInfo.append(String.format(getContext().getString(R.string.cap_tech_sp_name), debuff));
//			sbDebuffInfo.append("  ");
//		}
//		tv_resistance.setText(sbDebuffInfo.toString());
		
//		sb.append(String.format(getContext().getString(R.string.cap_tech_sp_name),
//		specialData.getName()));
//sb.append("  ");
//		if(data.getSpecialDatas() != null && data.getSpecialDatas().size() > 0){
//			StringBuffer sb = new StringBuffer();
//			for(TechSpecialData specialData : data.getSpecialDatas()){
//				sb.append(String.format(getContext().getString(R.string.cap_tech_sp_name),
//						specialData.getName()));
//				sb.append("  ");
//				sb.append(TechManager.getAbilityDescription(specialData.getName()));
//				sb.append(specialData.getDescription() + "\n");
//			}
//			tv_special.setText(sb.toString());
//		}
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(mListener == null){
			return;
		}
		switch (v.getId()) {
		default:
			mData.setIntent(new Intent(Intent.ACTION_DIALOG_DISMISS));
			mListener.onSelectionChanged(mData, true);
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
	public void populate(TerrainData data) {
		// TODO Auto-generated method stub
		if(data != null){
			mData = data;
			setData(mData);
		}
	}
	
}
