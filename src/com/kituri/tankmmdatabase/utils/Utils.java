package com.kituri.tankmmdatabase.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Locale;

import com.kituri.tankmmdatabase.KituriTankMMApplication;
import com.kituri.tankmmdatabase.data.tank.TankData;
import com.spreada.utils.chinese.ZHConverter;
import com.umeng.update.UmengUpdateAgent;
import com.umeng.update.UmengUpdateListener;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.util.LruCache;
import android.text.TextUtils;
import android.util.Base64;
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

}
