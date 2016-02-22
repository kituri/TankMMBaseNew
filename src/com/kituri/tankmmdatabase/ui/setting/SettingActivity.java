package com.kituri.tankmmdatabase.ui.setting;

import com.kituri.app.data.Entry;
import com.kituri.app.ui.BaseFragmentActivity;
import com.kituri.app.widget.SelectionListener;
import com.kituri.app.widget.dialog.CustomDialog;
import com.kituri.tankmmdatabase.R;
import com.kituri.tankmmdatabase.model.Intent;
import com.kituri.tankmmdatabase.utils.Utils;
import com.kituri.tankmmdatabase.widget.TankToggleButton;
import com.kituri.tankmmdatabase.widget.dialog.DialogVerUpdateHistory;
import com.kituri.app.model.JsonModel;
import com.umeng.update.UmengUpdateListener;
import com.umeng.update.UpdateResponse;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;



public class SettingActivity extends BaseFragmentActivity implements OnClickListener, SelectionListener<Entry>{

	static public final String LIVE2D_SETUP_TOOL_URL = "http://pan.baidu.com/s/1sk2WguL";
	static public final String TAG_DECORATED_MODE = "DECORATED_MODE";
	
	private CustomDialog mDetailDialog;

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		setTitle(R.string.cap_database_category_setting);
		setHomeAction(false);
		findViewById(R.id.btn_live2d_setup_tool).setOnClickListener(this);
		findViewById(R.id.btn_ver_update_history).setOnClickListener(this);
		findViewById(R.id.btn_ver_update).setOnClickListener(this);
		TankToggleButton tb_decorated = (TankToggleButton) findViewById(R.id.tb_decorated);
		tb_decorated.setSelectText(R.string.cap_toggle_button_close,
				R.string.cap_toggle_button_open);
		tb_decorated.setToggleTag(TAG_DECORATED_MODE);
		mDetailDialog = new CustomDialog(this, new DialogVerUpdateHistory(this));
		mDetailDialog.setSelectionListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_live2d_setup_tool:
			live2DSetup();
			break;
		case R.id.btn_ver_update_history:
			openVerUpdateHistory();
			break;
		case R.id.btn_ver_update:
			openVerUpdate();
			break;

		}
	}
	
	private void live2DSetup(){
		startActivity(new android.content.Intent(android.content.Intent.ACTION_VIEW,
				Uri.parse(LIVE2D_SETUP_TOOL_URL)));
	}
	
	private void openVerUpdateHistory(){
		mDetailDialog.show();
	}
	
	private void openVerUpdate(){
		Utils.checkVersion(this, new UmengUpdateListener() {
			
			@Override
			public void onUpdateReturned(int updateStatus, UpdateResponse updateInfo) {
				// TODO Auto-generated method stub
				if (updateStatus == 0 && updateInfo != null) {
					//有版本更新
				showUpdateDialog(updateInfo.path, updateInfo.updateLog);
			}else{
				Toast.makeText(SettingActivity.this,
						SettingActivity.this.getString(R.string.msg_not_new_ver),
						Toast.LENGTH_SHORT).show();
			}
			}				
		});
	}
	

    private void showUpdateDialog(final String downloadUrl, final String message) {
        AlertDialog.Builder updateAlertDialog = new AlertDialog.Builder(this);
        updateAlertDialog.setIcon(R.drawable.ic_launcher);
        updateAlertDialog.setTitle(R.string.app_name);
        updateAlertDialog.setMessage(getString(R.string.UMUpdateTitle, message));
        updateAlertDialog.setNegativeButton(R.string.cap_btn_ok,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();                       
                        try {
                            startActivity(new android.content.Intent(android.content.Intent.ACTION_VIEW, Uri
                                    .parse(downloadUrl)));
                        } catch (Exception ex) {

                        }
                    }
                }).setPositiveButton(R.string.cap_btn_close, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						//updateAlertDialog.
					}
				});
        if (!isFinishing())
            updateAlertDialog.show();
    }

	@Override
	public void onSelectionChanged(Entry item, boolean selected) {
		// TODO Auto-generated method stub
		if(item == null){
			return;
		}
		String action = item.getIntent().getAction();
		if(action.equals(Intent.ACTION_DIALOG_DISMISS)){
			mDetailDialog.dismiss();
		}
	}

	@Override
	public int getLayoutID() {
		// TODO Auto-generated method stub
		return R.layout.activity_setting;
	}

	@Override
	public void initDataBundle(Bundle bundle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected JsonModel initJsonModel() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
