package com.kituri.tankmmdatabase.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Locale;

import com.kituri.app.utils.FileManager;
import com.kituri.app.utils.ImageUtils;
import com.kituri.tankmmdatabase.KituriTankMMApplication;
import com.kituri.tankmmdatabase.data.tank.TankData;
import com.spreada.utils.chinese.ZHConverter;
import com.umeng.update.UmengUpdateAgent;
import com.umeng.update.UmengUpdateListener;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.util.LruCache;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.ImageView;

public class Utils {

	static public void setAssetsImage(Context context, String path, ImageView view) {
		if (TextUtils.isEmpty(path)) {
			view.setImageBitmap(null);
			return;
		}
		LruCache<String, Bitmap> appBitmapCache = KituriTankMMApplication.getInstance().getBitmapCache();
		if (appBitmapCache == null) {
			return;
		}
		Bitmap bit = appBitmapCache.get(path);

		if (bit == null) {
			InputStream in = null;
			try {
				in = context.getAssets().open(path);
				bit = BitmapFactory.decodeStream(in);
				appBitmapCache.put(path, bit);
				view.setImageBitmap(bit);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (in != null) {
					try {
						in.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		} else {
			view.setImageBitmap(bit);
		}

	}

	/***
	 *大建运势，玄学，死水姬务段公式 
	 **/
	static public TankData getSksResult(String name) {
		Calendar cal = Calendar.getInstance();
		String base64 = Base64.encodeToString((name + cal.get(Calendar.MONTH) + cal.get(Calendar.DATE)).getBytes(),
				Base64.NO_WRAP);
		long longResult = 0l;
		char[] chars = base64.toCharArray();
		for (int i = 0; i < chars.length; i++) {// 输出结果
			longResult += (int) chars[i];
		}
		return KituriTankMMApplication.tanks.get((int) longResult % KituriTankMMApplication.tanks.size());
	}

	static public String getLanguageString(String str) {
		String locale = Locale.getDefault().toString();
		if (TextUtils.isEmpty(locale)) {
			return str;
		}
		if (locale.equals("zh_TW") || locale.equals("zh_HK")) {
			return SimToTra(str);
		}
		return str;
	}

	public static String SimToTra(String simpStr) {
		ZHConverter converter = ZHConverter.getInstance(ZHConverter.TRADITIONAL);
		String traditionalStr = converter.convert(simpStr);
		return traditionalStr;
	}

	static public void checkVersion(Context context, UmengUpdateListener umengUpdateListener) {
		UmengUpdateAgent.setDeltaUpdate(false);
		UmengUpdateAgent.setUpdateOnlyWifi(true);
		UmengUpdateAgent.setUpdateAutoPopup(false);
		UmengUpdateAgent.setUpdateListener(umengUpdateListener);
		UmengUpdateAgent.update(context);
	}

	/**
	 * 获取版本号
	 * 
	 * @return 当前应用的版本号
	 */
	static public String getVersion(Context context) {
		try {
			PackageManager manager = context.getPackageManager();
			PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
			return info.versionName;
		} catch (Exception e) {
			return "";
		}
	}

	private static Bitmap takeScreenShot(Activity activity, View view) {
		// View是你需要截图的View
		// View view = activity.getWindow().getDecorView();
		view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
//		view.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
//				MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));		
		view.buildDrawingCache();
		Bitmap bitmap = view.getDrawingCache();
		return bitmap;
	}

//	public static void savePic(Context context, File f, Bitmap mBitmap) {
//		//File f = new File("/sdcard/" + bitName + ".png");
//	      f.setReadable(true);
//	      f.setWritable(true);
////		try {
////			f.createNewFile();
////		} catch (IOException e) {
////			// TODO Auto-generated catch block
////		}
//		FileOutputStream fOut = null;
//		try {
//			fOut = new FileOutputStream(f);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//		mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
//		try {
//			fOut.flush();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		try {
//			fOut.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

	public static void shoot(Activity a, View view, File filePath) {
		if (filePath == null) {
			return;
		}
		//takeScreenShot(a, view);
		ImageUtils.compressImage(takeScreenShot(a, view), filePath.getPath());
		//savePic(a, filePath, takeScreenShot(a, view));
	}

}
