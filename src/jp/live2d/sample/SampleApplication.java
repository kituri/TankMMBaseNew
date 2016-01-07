///**
// *  You can modify and use this source freely
// *  only for the development of application related Live2D.
// *
// *  (c) Live2D Inc. All rights reserved.
// */
//
//package jp.live2d.sample;
//
//import com.kituri.tankmmdatabase.R;
//import com.kituri.tankmmdatabase.ui.common.BaseActivity;
//
//import android.app.Activity;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.ViewGroup.LayoutParams;
//import android.widget.FrameLayout;
//import android.widget.LinearLayout;
//import android.widget.Toast;
//import jp.live2d.utils.android.FileManager;
//import jp.live2d.utils.android.SoundManager;
//
///*
// * SampleApplicationはActivityを継承し、サンプル・アプリケーションのエントリポイント（メインのActivityクラス）となります。
// */
//abstract public class SampleApplication extends BaseActivity
//{
//	//  Live2Dの管理
//	private LAppLive2DManager live2DMgr ;
//	static private Activity instance;
//
////	public SampleApplication(int resId)
////	{
////		super(resId);
////		instance=this;
////		if(LAppDefine.DEBUG_LOG)
////		{
////			Log.d( "", "==============================================\n" ) ;
////			Log.d( "", "   Live2D Sample  \n" ) ;
////			Log.d( "", "==============================================\n" ) ;
////		}
////
//////		SoundManager.init(this);
//////		live2DMgr = new LAppLive2DManager(path) ;
////	}
//
//	
//	public void init(String path){
//		instance=this;
//		FileManager.init(this.getApplicationContext());
//		SoundManager.init(this);
//		live2DMgr = new LAppLive2DManager(path) ;
//		setupGUI();
//	}
//
//	 static public void exit()
//    {
//		SoundManager.release();
//    	instance.finish();
//    }
//
//
////	/*
////	 * Activityが作成されたときのイベント
////	 */
////	@Override
////    public void onCreate(Bundle savedInstanceState)
////	{
////        super.onCreate(savedInstanceState);
////
////        //requestWindowFeature(Window.FEATURE_NO_TITLE);
////
////        // GUIを初期化
////      	
////      
////    }
//
//	//abstract public void initLive2D();
//	
//	/*
//	 * GUIの初期化
//	 * activity_main.xmlからViewを作成し、そこにLive2Dを配置する
//	 */
//	void setupGUI()
//	{
//		//initLive2D();
//    	//setContentView(R.layout.activity_main);
//
//        //  Viewの初期化
//        LAppView view = live2DMgr.createView(this) ;
//
//        // activity_main.xmlにLive2DのViewをレイアウトする
//        FrameLayout layout=(FrameLayout) findViewById(R.id.live2DLayout);
//		layout.addView(view, 0, new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
//
//
////		// モデル切り替えボタン
////		ImageButton iBtn = (ImageButton)findViewById(R.id.imageButton1);
////		ClickListener listener = new ClickListener();
////		iBtn.setOnClickListener(listener);
//	}
//
//
//	// ボタンを押した時のイベント
//	class ClickListener implements OnClickListener{
//
//		@Override
//		public void onClick(View v) {
//			Toast.makeText(getApplicationContext(), "change model", Toast.LENGTH_SHORT).show();
//			live2DMgr.changeModel();//Live2D Event
//		}
//	}
//
//
//	/*
//	 * Activityを再開したときのイベント。
//	 */
//	@Override
//	public void onResume()
//	{
//		if(live2DMgr != null){
//			live2DMgr.onResume() ;
//		}
//		
//		super.onResume();
//	}
//
//
//	/*
//	 * Activityを停止したときのイベント。
//	 */
//	@Override
//	public void onPause()
//	{
//		if(live2DMgr != null){
//			live2DMgr.onPause() ;
//		}
//		
//    	super.onPause();
//	}
//
//
//	@Override
//	protected void getData() {
//		// TODO Auto-generated method stub
//		
//	}
//
//
//	@Override
//	protected void initView() {
//		// TODO Auto-generated method stub
//		
//	}
//}
