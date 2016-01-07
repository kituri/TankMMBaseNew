package com.kituri.tankmmdatabase.utils;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.kituri.tankmmdatabase.data.tip.Tip;

import android.util.Log;

public class TipsUtils {
	
	 static private final String keyTip = "http://news.tankmm.com/index.php?m=content&c=index&a=show&catid=";
	 
	static public ArrayList<Tip> parseTips(String result){
		ArrayList<Tip> tips = new ArrayList<Tip>();
		Document doc = Jsoup.parse(result);
		Elements es = doc.select("a[href]");
        
        for (Element element : es) {  
        	if(element.attr("href").indexOf(keyTip) >= 0){
        		Tip tip = new Tip();
        		tip.setTipName(element.html());
        		tip.setUrl(element.attr("href"));
        		tips.add(tip);
        	} 
        } 
		return tips;
	}
}
