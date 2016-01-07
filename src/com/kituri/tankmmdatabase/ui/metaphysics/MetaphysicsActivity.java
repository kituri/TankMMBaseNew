package com.kituri.tankmmdatabase.ui.metaphysics;

import com.kituri.app.data.Entry;
import com.kituri.app.push.PsPushUserData;
import com.kituri.app.widget.SelectionListener;
import com.kituri.app.widget.dialog.CustomDialog;
import com.kituri.tankmmdatabase.KituriTankMMApplication;
import com.kituri.tankmmdatabase.R;
import com.kituri.tankmmdatabase.data.tank.TankData;
import com.kituri.tankmmdatabase.data.tank.TankSearchFilterData;
import com.kituri.tankmmdatabase.data.tech.TechData;
import com.kituri.tankmmdatabase.model.Intent;
import com.kituri.tankmmdatabase.ui.common.BaseActivity;
import com.kituri.tankmmdatabase.utils.Utils;
import com.kituri.tankmmdatabase.widget.dialog.DialogMetaphysicsResult;

import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MetaphysicsActivity extends BaseActivity implements OnClickListener, SelectionListener<Entry> {

	static public final String METAPHYSICS_NAME = "metaphysics_name";
	
	public MetaphysicsActivity() {
		super(R.layout.activity_metaphysics);
		// TODO Auto-generated constructor stub
	}

	private CustomDialog mMresultDialog;
	private DialogMetaphysicsResult mDialogMetaphysicsResult;
	private EditText et_name;
	private TextView tv_get_tank;

	@Override
	protected void getData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		setTitle(R.string.cap_database_category_metaphysics);
		setHomeAction(false);		
		findViewById(R.id.tv_get_tank).setOnClickListener(this);
		et_name = (EditText) findViewById(R.id.et_name);
		tv_get_tank = (TextView) findViewById(R.id.tv_get_tank);
		mDialogMetaphysicsResult = new DialogMetaphysicsResult(this);
		mMresultDialog = new CustomDialog(this, mDialogMetaphysicsResult);		
		mMresultDialog.setSelectionListener(this);
		
		et_name.setText(PsPushUserData.getData(this, METAPHYSICS_NAME, ""));
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.tv_get_tank:
			if(TextUtils.isEmpty(et_name.getText().toString())){
				Toast.makeText(this, getString(R.string.msg_input_is_nothing),
						Toast.LENGTH_SHORT).show();
				return;
			}else{
				PsPushUserData.setData(this, METAPHYSICS_NAME, et_name.getText().toString());
				mDialogMetaphysicsResult.populate(Utils.getSksResult(et_name.getText().toString()));
				mMresultDialog.show();
			}
			
			break;

		default:
			break;
		}
	}

	@Override
	public void onSelectionChanged(Entry item, boolean selected) {
		// TODO Auto-generated method stub
		if (item == null) {
			return;
		}
		String action = item.getIntent().getAction();
		if(action.equals(Intent.ACTION_DIALOG_DISMISS)){
			mMresultDialog.dismiss();
		}else if (action.equals(Intent.ACTION_TANK_DETAIL)) {
			KituriTankMMApplication.gotoTankDetail(this, (TankData) item);
		}
	}


	
}
