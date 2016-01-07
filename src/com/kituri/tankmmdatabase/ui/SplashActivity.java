package com.kituri.tankmmdatabase.ui;

import com.kituri.app.push.PsPushUserData;
import com.kituri.app.utils.Utility;
import com.kituri.tankmmdatabase.R;
import com.umeng.update.UmengUpdateAgent;
import com.umeng.update.UmengUpdateListener;
import com.umeng.update.UpdateResponse;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.widget.ImageView;



public class SplashActivity extends Activity{
	
	private Handler mHandler = new Handler();
	static public final String NEXT_LOGO_TIME_TAG = "NEXT_LOGO_TIME";
	static public final Long NEXT_LOGO_TIME = 36000000l;
	static public final int TIME_LIMIT = 3500;
	//static public final Long NEXT_LOGO_TIME = 10000l;
	private ImageView iv_logo_royal;
	private ImageView iv_logo_whatsup;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_splash);
		iv_logo_royal = (ImageView) findViewById(R.id.iv_logo_royal);
		iv_logo_whatsup = (ImageView) findViewById(R.id.iv_logo_whatsup);
		
		Long nextTime = PsPushUserData.getData(this, NEXT_LOGO_TIME_TAG, 0l);
		if(System.currentTimeMillis() > nextTime){
			PsPushUserData.setData(this, NEXT_LOGO_TIME_TAG, System.currentTimeMillis() + NEXT_LOGO_TIME);			
			gotoMainIf();
		}else{
			gotoMain();
		}
		
	}
	
	public void onDestroy(){
		Utility.recycleView(iv_logo_royal, true);
		Utility.recycleView(iv_logo_whatsup, true);
		super.onDestroy();
	}
	
	private void gotoMainIf(){
		mHandler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				gotoMain();
			}
		}, TIME_LIMIT);
	}
	
	private void gotoMain(){
		if(isFinishing()){
			return;
		}
		
		//System.currentTimeMillis()
		Intent intent = new Intent(this, MainActivity.class);			
		startActivity(intent);
		finish();
		overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
	}
	
	
}
