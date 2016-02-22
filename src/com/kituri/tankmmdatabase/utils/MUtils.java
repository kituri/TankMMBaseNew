package com.kituri.tankmmdatabase.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.kituri.tankmmdatabase.KituriTankMMApplication;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;

public class MUtils {
//	@SuppressLint("NewApi")
//	public static void save(String content, String name) {
//		// 要存储的内容
//	    
//	    Log.i("", "检验sdcard是否可用?");
//	    //判断sdcard是否存在?
//	    if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
//	      Log.i("", "sdcard不可用!");
//	      //Toast.makeText(context, "没有找到SDCard!", Toast.LENGTH_LONG);
//	      return ;
//	    };
//	    String name1 = "";
//	    try {
//	      // File file = new File("/sdcard/qqpassword.txt");
//	      // File file = new File(path + "/qqpassword2.txt");
//	    	
//	    	if(name.equals("head_pic_test.csv")){
//	    		name1 = "hp.krw";
//	    	}else if(name.equals("tank_bullet_test.csv")){
//	    		name1 = "tm.krw";
//	    	}else if(name.equals("tank_list_test.csv")){
//	    		name1 = "tl.krw";
//	    	}else if(name.equals("tech_ability_test.csv")){
//	    		name1 = "ta.krw";
//	    	}else if(name.equals("tech_bodywork_test.csv")){
//	    		name1 = "tb.krw";
//	    	}else if(name.equals("tech_engine_test.csv")){
//	    		name1 = "te.krw";
//	    	}else if(name.equals("tech_shield_test.csv")){
//	    		name1 = "ts.krw";
//	    	}else if(name.equals("map_sp_strategy_terrain.krw")){
//	    		name1 = "msst.krw";
//	    	}else if(name.equals("map_strategy_terrain.krw")){
//	    		name1 = "mst.krw";
//	    	}else if(name.equals("terrain_test.csv")){
//	    		name1 = "mttn.krw";
//	    	}else if(name.equals("msta.csv")){
//	    		name1 = "msta.krw";
//	    	}else if(name.equals("mstr.csv")){
//	    		name1 = "mstr.krw";
//	    	}else{
//	    		name1 = name;
//	    	}
//	    	
//	      File file = new File(Environment.getExternalStorageDirectory() + "/000wrz", "/" + name1);
//	      file.setReadable(true);
//	      file.setWritable(true);
//	      FileOutputStream fos = new FileOutputStream(file);
//	      fos.write(content.getBytes());
//	      fos.flush();
//	      fos.close();
//	    } catch (Exception e) {
//	      //e.printStackTrace();
//	      Log.i("", e.getMessage());
//	      return;
//	    }
//	    Log.i("", Environment.getExternalStorageDirectory() + "/000wrz/" + name1 + " -->写入成功");
//	}
//
//	public static String getStringFromAssert(Context context, String fileName){
//		InputStream in;
//		try {
//			in = context.getAssets().open(fileName);
//
//		int size = in.available();
//		byte[] buffer= new byte[size];
//		in.read(buffer);
//		in.close();
//		return new  String (buffer, "utf-8"); 
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//	}
//	
//	public static void change(Context context, String fileName){
//		String str = "";
//		str = getStringFromAssert(context, fileName);
//		//String aes = AESUtils.decrypt("ahahahah", str);
//		String base64_01 = Base64.encodeToString(str.getBytes(), Base64.NO_WRAP);
//		String base64_02 = Base64.encodeToString(base64_01.getBytes(), Base64.NO_WRAP);
//		save(base64_02, fileName);
//		//FileUtils
//		//Log.i("", "str长度:" + str.length());
//		//save(base64, fileName);
//	}
//	
//	static public void reSave(Context context, String fileName){
//		String str = "";
//		str = getStringFromAssert(context, fileName);
//		str = new String(Base64.decode(str.getBytes(), Base64.NO_WRAP));
//		str = new String(Base64.decode(str.getBytes(), Base64.NO_WRAP));
//		save(str, fileName);
//	}
	
	static public BufferedReader getAsReader(String str){
		str = new String(Base64.decode(str.getBytes(), Base64.NO_WRAP));
		str = new String(Base64.decode(str.getBytes(), Base64.NO_WRAP));
		byte[] by = str.getBytes();
		InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(by));
		return new BufferedReader(isr);
	}
	
}
