package com.kituri.tankmmdatabase.ui.tank;

import com.kituri.app.ui.BaseFragmentActivity;
import com.kituri.tankmmdatabase.R;
import com.kituri.tankmmdatabase.data.tank.TankData;
import com.kituri.tankmmdatabase.model.Intent;
import com.kituri.app.model.JsonModel;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;
import jp.live2d.sample.LAppLive2DManager;
import jp.live2d.sample.LAppLive2DManager.Live2DOnExitListener;
import jp.live2d.sample.LAppView;
import jp.live2d.utils.android.FileManager;
import jp.live2d.utils.android.SoundManager;

public class TankLive2DActivity extends BaseFragmentActivity implements Live2DOnExitListener{
	 
	private TankData mTankData;
	//  Live2Dの管理
	private LAppLive2DManager live2DMgr ;
	//static private Activity instance;
	private Handler mHandler = new Handler();
	
	void setupGUI()
	{
		//initLive2D();
    	//setContentView(R.layout.activity_main);

        //  Viewの初期化
        LAppView view = live2DMgr.createView(this, this) ;

        // activity_main.xmlにLive2DのViewをレイアウトする
        FrameLayout layout=(FrameLayout) findViewById(R.id.live2DLayout);
		layout.addView(view, 0, new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));


//		// モデル切り替えボタン
//		ImageButton iBtn = (ImageButton)findViewById(R.id.imageButton1);
//		ClickListener listener = new ClickListener();
//		iBtn.setOnClickListener(listener);
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		if(mTankData == null){
			finish();
			return;
		}
		setTitle(mTankData.getTankName() + "(" + mTankData.getTankClass() + ")");
		//init("live2d/su5_a/su5_a.json");
		//"live2d/su5_a/su5_a.json"
		//init("live2d/" + mTankData.getLive2d() + "/" + mTankData.getLive2d() + ".json");
		//instance=this;
		FileManager.init(this.getApplicationContext());
		SoundManager.init(this);
		live2DMgr = new LAppLive2DManager(Environment.getExternalStorageDirectory().getPath() + "/live2d/" + mTankData.getLive2d() +
				"/" + mTankData.getLive2d() + ".json") ;
		setupGUI();
	}
	/*
	 * Activityを再開したときのイベント。
	 */
	@Override
	public void onResume()
	{
		if(live2DMgr != null){
			live2DMgr.onResume() ;
		}
		
		super.onResume();
	}


	/*
	 * Activityを停止したときのイベント。
	 */
	@Override
	public void onPause()
	{
		if(live2DMgr != null){
			live2DMgr.onPause() ;
		}
		
    	super.onPause();
	}

	@Override
	public void exit() {//msg_failed_to_load
		mHandler.post(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Toast.makeText(TankLive2DActivity.this, getString(R.string.msg_failed_to_load),
						Toast.LENGTH_SHORT).show();
			}
		});

		SoundManager.release();
		finish();
	}

	@Override
	public int getLayoutID() {
		// TODO Auto-generated method stub
		return R.layout.activity_tank_live2d;
	}

	@Override
	public void initDataBundle(Bundle bundle) {
		// TODO Auto-generated method stub
		mTankData = (TankData) ((TankData) bundle.getSerializable(Intent.EXTRA_TANK_DATA)).clone();
	}

	@Override
	protected JsonModel initJsonModel() {
		// TODO Auto-generated method stub
		return null;
	}
		
}
